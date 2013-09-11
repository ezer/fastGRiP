package edu.cam.fgrip;

import java.util.ArrayList;
import java.util.List;


public class SwitchSlideModel extends ProbabilityModel{
	
	public SwitchSlideModel(Parameters p) {
		super(p);
	}
	

	/*@Override
	 * 
	 * Calculates propensity of a TF binding 
	 * 
	 * @see edu.cam.fgrip.ProbabilityModel#getForwardPropensity(int, int, java.util.List, double)
	 */
	public double getForwardPropensity(int oldID, int tfID,
			List<TranscriptionFactor> tfs, double totalTF) {
	
		int usedUp = numTFsBasedOnState(oldID, tfID, tfs);
		double rightmostBound=0;
		double leftmostBound=0;
		double s_l_real=params.get("s_l_real");
		
		rightmostBound = Math.min((int)(s_l_real/2), getRightBound(oldID, tfID, tfs));
		leftmostBound = Math.min((int)(s_l_real/2), getLeftBound(oldID, tfID, tfs));
		//System.out.println("Forward params: "+rightmostBound+", "+leftmostBound);
		double modelLength=tfs.get(0).getCoveringTo(tfs.get(tfs.size()-1))+2*params.get("s_l_obs");
		return propensityBindingUniform(tfs.get(tfID), leftmostBound+rightmostBound, usedUp, totalTF, (int)modelLength, tfs);
	}
	
	/*@Override
	 * 
	 * Calculates propensity of TF unbinding
	 * 
	 * @see edu.cam.fgrip.ProbabilityModel#getBackwardPropensity(int, int, java.util.List)
	 */
	public double getBackwardPropensity(int oldID, int tfID,
			List<TranscriptionFactor> tfs) {
	
		int rightmostBound = getRightBoundWithoutCluster(oldID, tfID, tfs);
		int leftmostBound = getLeftBoundWithoutCluster(oldID, tfID, tfs);
		List<Integer> clusters=countClusters(rightmostBound, leftmostBound, tfID, tfs);
		double expectedVisitsOrigin=tfs.get(tfID).visitsToOrigin(rightmostBound+leftmostBound);
		double expectedVisitsNotOrigin = tfs.get(tfID).slides()-expectedVisitsOrigin*(1+clusters.size());	
		double expectedTimeInOrigin=tfs.get(tfID).timeInBindingSite()*expectedVisitsOrigin;
		for(Integer i: clusters){
			expectedTimeInOrigin+=tfs.get(i).timeInBindingSite()*expectedVisitsOrigin;
		}
		
		double expectedTime=expectedTimeInOrigin + tfs.get(tfID).timeInBackground() * expectedVisitsNotOrigin;
		double k=(1.0/(expectedTime));
		return k;
	}

	private double getLeftBound(int oldID, int tfID, List<TranscriptionFactor> tfs) {
		double leftmostBound;
		int id;
		int i;
		i=tfID+1;
		id=oldID>>>i;
		//System.out.println("Starts with: oldID: "+oldID+" tfID "+tfID+"iter i "+i+" id "+id);
		while(i<tfs.size() && ((id&1)==0) && !tfs.get(i).hasSameNameAs(tfs.get(tfID))){
			i++;
			id=id>>>1;
			//System.out.println("iter i "+i+" id "+id);
		}
		
		double maxSlide=params.get("s_l_obs")/2;
		if(i>=tfs.size())
			leftmostBound=(int)maxSlide;
		else if((id&1)==0 && tfs.get(i).hasSameNameAs(tfs.get(tfID))){ //cluster
			//System.out.println("distanceTo: "+tfs.get(tfID).getDistanceTo(tfs.get(i))/2.0);
			leftmostBound= Math.min(maxSlide, tfs.get(tfID).getDistanceTo(tfs.get(i))/2.0);
		}
		else{ //barrier
			leftmostBound= Math.min(maxSlide, tfs.get(i).getDistanceBetween(tfs.get(tfID)));
		}
		return leftmostBound;
	}

	private double getRightBound(int oldID, int tfID, List<TranscriptionFactor> tfs) {
	
		double maxSlide=params.get("s_l_obs")/2;
			
		double rightmostBound;
		int id=oldID;
		int i=tfID-1;
		while(i>=0 && (((id>>>i)&1)==0 && !tfs.get(i).hasSameNameAs(tfs.get(tfID))) ){
			i--;
			id=oldID;
		}
		
		if(i<0)
			rightmostBound=maxSlide;
		else if(((id>>>i)&1)==0 && tfs.get(i).hasSameNameAs(tfs.get(tfID))){ //cluster
			rightmostBound= Math.min(maxSlide, tfs.get(tfID).getDistanceTo(tfs.get(i))/2.0);
		}
		else{ //barrier
			rightmostBound= Math.min(maxSlide,tfs.get(tfID).getDistanceBetween(tfs.get(i)));
		}
		return rightmostBound;
	}

	private int getLeftBoundWithoutCluster(int oldID, int tfID, List<TranscriptionFactor> tfs) {
		int leftmostBound;
		int id;
		int i;
		i=tfID+1;
		id=oldID>>>i;
		
		while(i<tfs.size() && ((id&1)==0) ){
			i++;
			id=id>>>1;
		}
		
		double maxSlide=params.get("s_l_obs")/2;
		if(i>=tfs.size())
			leftmostBound=(int)maxSlide;
		else{
			leftmostBound=(int) Math.min(maxSlide, tfs.get(i).getDistanceBetween(tfs.get(tfID)));
		}
		return leftmostBound;
	}

	private int getRightBoundWithoutCluster(int oldID, int tfID, List<TranscriptionFactor> tfs) {
		int rightmostBound;
		int id=oldID;
		int i=tfID-1;
		while(i>=0 && (((id>>>i)&1)==0 ) ){
			i--;
			id=oldID;
		}
		
		double maxSlide=params.get("s_l_obs")/2;
		
		if(i<0)
			rightmostBound=(int)maxSlide;
		else{
			rightmostBound=(int) Math.min(maxSlide,tfs.get(tfID).getDistanceBetween(tfs.get(i)));
		}
		return rightmostBound;
	}

	
	private List<Integer> countClusters(int rightmostBound, int leftmostBound, int tfID,
			List<TranscriptionFactor> tfs) {
		
		List<Integer> clusters=new ArrayList<Integer>();
		int i=tfID-1;
		while(i>=0 && tfs.get(i).getDistanceBetween(tfs.get(tfID))<=rightmostBound){
			if(tfs.get(i).hasSameNameAs(tfs.get(tfID))){
				clusters.add(i);
			}
			i--;
		}
		i=tfID+1;
		while(i<tfs.size() && tfs.get(i).getDistanceBetween(tfs.get(tfID))<=leftmostBound){
			if(tfs.get(i).hasSameNameAs(tfs.get(tfID))){
				clusters.add(i);
			}
			i++;
		}
		return clusters;
	}

	//TODO 
	/*
	private double slideProb(int tfIDstart, int tfIDstop, int oldID,
			List<TranscriptionFactor> tfs){
		
		int distance=tfs.get(tfIDstart).getDistanceTo(tfs.get(tfIDstop));
		double N=2*(distance)*(distance);
		return (N+0.0)/tfs.get(tfIDstart).slides();
	}
	*/
	
	@Override
	public double getRelocationPropensity(int tfIDstart, int tfIDstop, int oldID,
			List<TranscriptionFactor> tfs) {	
		
		double k=0;
		double distance=tfs.get(tfIDstart).getDistanceTo(tfs.get(tfIDstop));
		double N=2*(distance)*(distance);
		double passesThroughOrigin=N/(distance);
		double t_bp=tfs.get(tfIDstart).timeInBackground();
		k=1.0/((N-passesThroughOrigin)*t_bp+passesThroughOrigin*tfs.get(tfIDstart).timeInBindingSite());
		return k;
		
	}
	
	
	private double timeOfBinding3D(TranscriptionFactor tf, int usedUpTFs, double totalTF, int modelLength, List<TranscriptionFactor> tfs){	
		if(tf.getConcentration()==0)
			return 0;
		
		/*
		 * propensity of binding 3D summary:
		 * either calculate k_a or just use it
		 * 
		 */
		
		if(params.contains("k_a")){
			return 1.0/params.get("k_a");
		}
	
		double f=tf.f();
		double k_a=params.get("k_assoc");
		double lambda=tf.getLambda(tfs, modelLength);
		double k_a_adj=k_a*(lambda-f*lambda)/(1-f*lambda);
		return 1/k_a_adj;
	}
	
	public double propensityBindingUniform(TranscriptionFactor tf, double d, int usedUpTfs, double totalTF, int modelLength, List<TranscriptionFactor> tfs){
		
		double DNAlength=params.get("DNAlength");
		double M=DNAlength;
		double t_3D=timeOfBinding3D(tf, usedUpTfs, totalTF, modelLength, tfs);
		double t_1D=tf.slides()*tf.timeInBackground();
		double t_binding=((M/(d))*(t_1D+t_3D));	
	    return (1.0/t_binding)*(tf.getConcentration()-usedUpTfs);	
	}
	
}
