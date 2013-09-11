package edu.cam.fgrip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

 
public class Simulator {

	public static void main(String[] args){
		Simulator s=new Simulator();
		MarkovChainGenerator mcg=new MarkovChainGenerator();
		//mcg.buildMarkovChain("mimickingGRiP1", 1, 1, new SwitchSlideModel(new Parameters("params2")), "params2").getJSONs(300, 10, "mimickingGRiP1"); 
		
		mcg.buildMarkovChain("doublebarriercluster_op_d_0_cn_5.0_swt_33.0", 3, 1, new SwitchSlideModel(new Parameters("params2")), "params2").getJSONs(300, 10, "doublebarriercluster_op_d_0_cn_5.0_swt_33.0"); 
		mcg.buildMarkovChain("doublebarriercluster_op_d_100_cn_5.0_swt_33.0", 3, 1, new SwitchSlideModel(new Parameters("params2")), "params2").getJSONs(300, 10, "doublebarriercluster_op_d_0_cn_5.0_swt_33.0"); 
		
		
		System.out.println("far");
		//System.out.println(mcg.buildMarkovChain("mimickingGRiP3", 2, 100, new SwitchSlideModel(new Parameters("params2")), "params2")); 
		System.out.println("close");
		//System.out.println(mcg.buildMarkovChain("mimickingGRiP4", 2, 100, new SwitchSlideModel(new Parameters("params2")), "params2")); 
		
		//public MarkovChain buildMarkovChain(String file, int numStates, int numTokens, ProbabilityModel m, String paramFile) 
		//s.testJSONlinking();
		
		//JSONtest t=new JSONtest();
		//t.test();
		//testMarA();
	//	testTimeCourseTransitions();
		int[] a={0, 100};
		//int[] a={100};
		//double[] b={2, 5, 10, 22, 46, 100};
		double[] b={5, 10, 20, 50, 100, 200, 500, 1000};
		double[] c={0.0033, 0.0104, 0.033, 0.104, 0.33, 1.04, 3.3, 10.4, 33.0, 104.0};
		//double[] b={5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000};
		//double[] c={0.00033, 0.00104, 0.0033, 0.0104, 0.033, 0.104, 0.33, 1.04, 3.3, 10.4, 33.0, 104.0, 330.0};
		int[] a_1={-5, 0};
		double[] c_1={33.0};
		double[] b_1={46, 100};
		//generateClusterFiles("clusters", a_1, b_1, c_1);
		//generateClusterFiles("clusters", a_1, b, c);
		//generateSingleBarrierFiles("singlebarriers", a, 10, 0.33, b, c);
		//generateDoubleBarrierFiles("doublebarriers", a, 10, 0.33, b,c);
		//generateDoubleBarrierFilesOpposite("doublebarriers_op", a, 10, 0.33, b,c);
		generateDoubleBarrierClusterFilesOpposite("doublebarriercluster_op", a, 10, 0.33, b,c);
		generateMatrixDoubleBarrier("doublebarriercluster_op", 0, b, c);
		generateMatrixDoubleBarrier("doublebarriercluster_op", 100, b, c);
		//generateComplexPairFiles("complex", a, 10, 0.33, b, c);
		
		//generateDoubleBarrierFiles("singlebarriers", a_1, 10, 0.33, b_1, c_1);
		
		//generateSingleBarrierFiles("singlebarriers_eq", a, b, c);
		
		
		
		/*
		 * Stuff to do: 
		 * a) have the average arrival time on the Z-axis: -5bp, 0bp, 100bp
		 * b) have equal values for BOTH TFs (like we do in clusters) and do first expected arrival and second expected arrival: cluster 0bp, barrier 0bp, faraway
		 * c) have double barrier changing the middle, expected arrival time of outer OR and center and AND and overall OR
		 * d) changing the barrier, expected arrival time of outer OR and center and AND and overall OR
		 * e) get timings as you change different conditions.
		 */
		
		
		/*
		
		//A
		
		//generateMatrixCluster("singlebarriers", 100, b, c);
		//generateMatrixCluster("singlebarriers", 0, b, c);
		generateMatrixTwoTFs("singlebarriers", -5, b, c);
		generateMatrixTwoTFs("singlebarriers", 0, b, c);
		generateMatrixTwoTFs("singlebarriers", 100, b, c);
		
		
		//B
		
		generateMatrixCluster("clusters", 0, b, c);
		generateMatrixCluster("singlebarriers_eq", 0, b, c);
		generateMatrixCluster("singlebarriers_eq", -5, b, c);
		generateMatrixCluster("singlebarriers_eq", 100, b, c);
		
		
		//C and D
		 generateMatrixDoubleBarrier("doublebarriers", 0, b, c);
		 generateMatrixDoubleBarrier("doublebarriers", 100, b, c);
		 generateMatrixDoubleBarrier("doublebarriers_op", 0, b, c);
		 generateMatrixDoubleBarrier("doublebarriers_op", 100, b, c);
		
		*/
		//runtimeTests();
		
	//MarkovChainGenerator sm=new MarkovChainGenerator();
		
//	ProbabilityModel pm=new SwitchSlideModel(new Parameters("params"));
		//runMultipleFiles("mimickingGRiP", 6);
	//sm.buildMarkovChain("Cluster", 2, 10, pm, "params").runSimulationUntilStateConfigReached("3", "cluster");
		
		/*
		 *   (let [sm     (new MarkovChainGenerator)
          pm     (new SwitchSlideModel (new Parameters "params"))
         ; new-id (Integer/toString (rand-int (* 10000 10000)) 16)
          data   ( if (.equals runTime "time")
                      (.runSimulationUntil 
                        (.buildMarkovChain sm "Cluster" 2 10 pm "params")
                         time "cluster")
                      (.runSimulationUntilStateReached
                        (.buildMarkovChain sm "Cluster" (int 2) (int 10) pm "params")
                          config "cluster"))]
     ;(save-result-data new-id data)
     ;(response/redirect (str "/results/" new-id))
     )
		 */
		
		//MarkovChainGenerator sm=new MarkovChainGenerator();
		
		//ProbabilityModel pm=new SwitchSlideModel(new Parameters("params"));
		//sm.buildMarkovChain("Cluster", 2, 1000, pm, "params").runSimulationUntil(1800, "cluster");
		//sm.buildMarkovChain("mimickingGRiP", 8, 10, pm, "params").runSimulationUntil(1800, "mimickingGRiP");
		//sm.buildMarkovChain("Barrier", 2, 1000, pm, "params").runSimulationUntil(1800, "barrier");
		//sm.buildMarkovChain("barrier5", 2, 1000, pm, "params").runSimulationUntil(1800, "barrier5");
		//sm.buildMarkovChain("barrier10", 2, 1000, pm, "params").runSimulationUntil(1800, "barrier10");
		//sm.buildMarkovChain("barrier20", 2, 1000, pm, "params").runSimulationUntil(1800, "barrier20");
		//sm.buildMarkovChain("FarAway", 2, 1000, pm, "params").runSimulationUntil(1800, "faraway");
		//sm.buildMarkovChain("Switch", 2, 1000, pm, "params").runSimulationUntil(18000, "switch");
		///sm.buildMarkovChain("hill", 3, 1000, pm, "params").runSimulationUntil(1800, "hill");
		//sm.buildMarkovChain("valley", 3, 1000, pm, "params").runSimulationUntil(1800, "valley");
	}
	
	private static void runtimeTests(){
		try{
			System.out.println("Number of sites");
			/*
			for(int i=1; i<=15; i++){
				String filename="site"+i;
				MarkovChainGenerator sm=new MarkovChainGenerator();
				ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
				Date d=new Date();
				long t=d.getTime();
				sm.buildMarkovChain(filename, i, 100, pm, "params2").runSimulationUntil(100000, filename);
				Date d2=new Date();
				long t2=d2.getTime();
				System.out.println((t2-t));
			}
			*/
			System.out.println("concentration (speed of reaction)");
			for(int i=1; i<=9; i++){
				String filename="speed"+i;
				MarkovChainGenerator sm=new MarkovChainGenerator();
				ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
				Date d=new Date();
				long t=d.getTime();
				sm.buildMarkovChain(filename, 1, 100, pm, "params2").runSimulationUntil(100000, filename);
				Date d2=new Date();
				long t2=d2.getTime();
				System.out.println((t2-t));
			}
			
		}catch(Throwable t){
			System.out.println(t);
			t.printStackTrace();
		}
	}
	/*
	private static void testTimeCourseTransitions(){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		int[][][] test1=sm.buildMarkovChain("hdeABtest", 8, 400, pm, "params2").getTimeCourseTransitions(3000, 50, "hdeABtest1");
		try{
			FileWriter timeCourse=new FileWriter("timecoursetransition_even");
			
			for(int i=0; i<test1.length; i++){
				timeCourse.write("\n");
				for(int j=0; j<test1[0].length; j++){
					for(int k=0; k<test1[0][0].length; k++){
							if(test1[i][j][k]>10)
							timeCourse.write(""+j+","+k+"\t"+test1[i][j][k]+"\t");
					}
				}
			}
			
			timeCourse.close();
		}catch(Throwable t){
			
		}
		
		
		test1=sm.buildMarkovChain("hdeABtestLrpSticky", 8, 400, pm, "params2").getTimeCourseTransitions(3000, 50, "hdeABtestLrpSticky1");
		try{
			FileWriter timeCourse=new FileWriter("timecoursetransition_sticky");
			
			for(int i=0; i<test1.length; i++){
				timeCourse.write("\n");
				for(int j=0; j<test1[0].length; j++){
					for(int k=0; k<test1[0][0].length; k++){
							if(test1[i][j][k]>10)
							timeCourse.write(""+j+","+k+"\t"+test1[i][j][k]+"\t");
					}
				}
			}
			
			timeCourse.close();
		}catch(Throwable t){
			
		}
		
		
		test1=sm.buildMarkovChain("hdeABtestLrpConc", 8, 400, pm, "params2").getTimeCourseTransitions(3000, 50, "hdeABtestLrpConc1");
		try{
			FileWriter timeCourse=new FileWriter("timecoursetransition_conc");
			
			for(int i=0; i<test1.length; i++){
				timeCourse.write("\n");
				for(int j=0; j<test1[0].length; j++){
					for(int k=0; k<test1[0][0].length; k++){
							if(test1[i][j][k]>10)
							timeCourse.write(""+j+","+k+"\t"+test1[i][j][k]+"\t");
					}
				}
			}
			
			timeCourse.close();
		}catch(Throwable t){
			
		}
		
		
	}
	
	
	
	private void testJSONlinking(){
		/*
		 *  [sm     (new MarkovChainGenerator)
          pm     (new SwitchSlideModel (new Parameters "params2"))
          new-id (Integer/toString (rand-int (* 10000 10000)) 16)
          data   ( if (.equals runTime "time")
                      (.getJSONs
                        (.buildMarkovChain sm "mimickingGRiP1" 2 10 pm "params2")
                        (new Double time) 50 "cluster")
		 */
		
		//MarkovChainGenerator sm=new MarkovChainGenerator();
		//SwitchSlideModel pm=new SwitchSlideModel(new Parameters("params"));
		//sm.buildMarkovChain("mimickingGRiP1", 2, 400, pm, "params2").getJSONs(30000, 10, "cluster");
	//}
	
	private static void generateMatrixTwoTFs(String basename, int dist, double[] cn, double[] t_0){
		//TODO
		
		double[][] meansA=new double[cn.length][t_0.length];
		double[][] meansB=new double[cn.length][t_0.length];
		double[][] sd=new double[cn.length][t_0.length];
		
		for(int i=0; i<cn.length; i++){
			for(int j=0; j<t_0.length; j++){
				String name=basename+"_d_"+dist+"_cn_"+cn[i]+"_swt_"+t_0[j]+"_firstoccurance";
				try {
					Scanner in=new Scanner(new File(name));
					List<Double> as=new ArrayList<Double>();
					List<Double> bs=new ArrayList<Double>();
					while(in.hasNext()){
						String[] line=in.nextLine().split("\\s+");
						
					
						if(line.length>=2 && line[0].length()>0 && line[1].length()>0){
							//System.out.println(line[0]+", "+line[1]);
							double a=Double.parseDouble(line[0]);
							double b=Double.parseDouble(line[1]);
							as.add(Math.log(a));
							bs.add(Math.log(b));
						}else{
							System.out.println(name+" not run long enough");
						}
						
						
					}
					meansA[i][j]=getMean(as);
					meansB[i][j]=getMean(bs);
					//sd[i][j]=getStdDev(ratios);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					System.out.println(name);
					e.printStackTrace();
				}
				
			}
		}
		
		try{
			FileWriter out=new FileWriter(basename+"_d_"+dist+"_meansA");
			FileWriter out2=new FileWriter(basename+"_d_"+dist+"_meansB");
			
			for(int i=0; i<cn.length; i++){
				for(int j=0; j<t_0.length; j++){
					out.write(meansA[i][j]+"\t");
					out2.write(meansB[i][j]+"\t");
				}
				out.write("\n");
				out2.write("\n");
			}
			
			
			out2.close();
			out.close();
		}catch(Throwable t){
			System.out.println(t);
			t.printStackTrace();
		}
	}
	
	private static void generateMatrixDoubleBarrier(String basename, int dist, double[] cn, double[] t_0){
		//TODO
		
		double[][] meansA=new double[cn.length][t_0.length];
		double[][] meansB=new double[cn.length][t_0.length];
		double[][] meansAND=new double[cn.length][t_0.length];
		
		
		for(int i=0; i<cn.length; i++){
			for(int j=0; j<t_0.length; j++){
				String name=basename+"_d_"+dist+"_cn_"+cn[i]+"_swt_"+t_0[j]+"_firstoccurance";
				String name2=basename+"_d_"+dist+"_cn_"+cn[i]+"_swt_"+t_0[j]+"_firstStateOccurance";
				try {
					Scanner in=new Scanner(new File(name));
					Scanner in2=new Scanner(new File(name2));
					List<Double> as=new ArrayList<Double>();
					List<Double> bs=new ArrayList<Double>();
					List<Double> ands=new ArrayList<Double>();
					while(in.hasNext()){
						String[] line=in.nextLine().split("\\s+");
						String[] line2=in2.nextLine().split("\t");
					
						if(line.length>=3 && line[0].length()>0 && line[1].length()>0){
							//System.out.println(line[0]+", "+line[1]);
							double a=Double.parseDouble(line[0]);
							double b=Double.parseDouble(line[1]);
							double c=Double.parseDouble(line[2]);
							if(line2.length==9){
							double and=Double.parseDouble(line2[8]);
							ands.add(Math.log(and));
							}
							as.add(Math.min(Math.log(a), Math.log(c)));
							bs.add(Math.log(b));
							
						}else{
							System.out.println(name+" not run long enough");
						}
						
						
					}
					meansA[i][j]=getMean(as);
					meansB[i][j]=getMean(bs);
					meansAND[i][j]=getMean(ands);
					//sd[i][j]=getStdDev(ratios);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					System.out.println(name);
					e.printStackTrace();
				}
				
			}
		}
		
		try{
			FileWriter out=new FileWriter(basename+"_d_"+dist+"_meansA");
			FileWriter out2=new FileWriter(basename+"_d_"+dist+"_meansB");
			FileWriter out3=new FileWriter(basename+"_d_"+dist+"_meansAND");
			for(int i=0; i<cn.length; i++){
				for(int j=0; j<t_0.length; j++){
					out.write(meansA[i][j]+"\t");
					out2.write(meansB[i][j]+"\t");
					out3.write(meansAND[i][j]+"\t");
				}
				out.write("\n");
				out2.write("\n");
				out3.write("\n");
			}
			
			out3.close();
			out2.close();
			out.close();
		}catch(Throwable t){
			System.out.println(t);
			t.printStackTrace();
		}
	}
	
	
	
	private static void generateMatrixCluster(String basename, int dist, double[] cn, double[] t_0){
		//TODO
		
		double[][] meansA=new double[cn.length][t_0.length];
		double[][] meansB=new double[cn.length][t_0.length];
		double[][] sd=new double[cn.length][t_0.length];
		
		for(int i=0; i<cn.length; i++){
			for(int j=0; j<t_0.length; j++){
				String name=basename+"_d_"+dist+"_cn_"+cn[i]+"_swt_"+t_0[j]+"_firstoccurance";
				try {
					Scanner in=new Scanner(new File(name));
					List<Double> as=new ArrayList<Double>();
					List<Double> bs=new ArrayList<Double>();
					while(in.hasNext()){
						String[] line=in.nextLine().split("\\s+");
						
					
						if(line.length>=2 && line[0].length()>0 && line[1].length()>0){
							//System.out.println(line[0]+", "+line[1]);
							double a=Double.parseDouble(line[0]);
							double b=Double.parseDouble(line[1]);
							as.add(Math.log(Math.abs(a-b)));
							//as.add(Math.min(Math.log(a),Math.log(b)));
							bs.add(Math.max(Math.log(a),Math.log(b)));
						}else{
							System.out.println(name+" not run long enough");
						}
						
						
					}
					meansA[i][j]=getMean(as);
					meansB[i][j]=getMean(bs);
					//sd[i][j]=getStdDev(ratios);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					System.out.println(name);
					e.printStackTrace();
				}
				
			}
		}
		
		try{
			FileWriter out=new FileWriter(basename+"_d_"+dist+"_meansDifference");
			FileWriter out2=new FileWriter(basename+"_d_"+dist+"_meansB");
			
			for(int i=0; i<cn.length; i++){
				for(int j=0; j<t_0.length; j++){
					out.write(meansA[i][j]+"\t");
					out2.write(meansB[i][j]+"\t");
				}
				out.write("\n");
				out2.write("\n");
			}
			
			
			out2.close();
			out.close();
		}catch(Throwable t){
			System.out.println(t);
			t.printStackTrace();
		}
	}
	
	
	
	
	
	
	private static double getMean(List<Double> data)
    {
        double sum = 0.0;
        for(double a : data)
            sum += a;
            return sum/data.size();
    }

        private static double getVariance(List<Double> data)
        {
            double mean = getMean(data);
            double temp = 0;
            for(double a :data)
                temp += (mean-a)*(mean-a);
                return temp/data.size();
        }

        private static double getStdDev(List<Double> data)
        {
            return Math.sqrt(getVariance(data));
        }

   
	
	private static void testMarA(){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params"));
		sm.buildMarkovChain("hdeAB_MarA", 1, 400, pm, "params").runSimulationUntil(30000, "marA_");
		sm.buildMarkovChain("hdeAB_PhoP", 1, 400, pm, "params").runSimulationUntil(30000, "phoP_");
		sm.buildMarkovChain("hdeAB_MarA_PhoP", 2, 400, pm, "params").runSimulationUntil(30000, "marAphoP_");
		sm.buildMarkovChain("hdeAB_TorR", 2, 400, pm, "params").runSimulationUntil(30000, "torR_");
	}
	/*
	private static void testTimeCourse() {
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		int[][] test1=sm.buildMarkovChain("hdeABtest", 8, 400, pm, "params2").runTimeCourse(3000, 10, "hdeABtest1");
		try{
			FileWriter timeCourse=new FileWriter("timecourse_even");
			
			for(int i=0; i<test1.length; i++){
				timeCourse.write("\n");
				for(int j=0; j<test1[0].length; j++){
					timeCourse.write(test1[i][j]+"\t");
				}
			}
			
			timeCourse.close();
		}catch(Throwable t){
			
		}
		
		test1=sm.buildMarkovChain("hdeABtestLrpSticky", 8, 400, pm, "params2").runTimeCourse(3000, 10, "hdeABtestLrpSticky1");
		try{
			FileWriter timeCourse=new FileWriter("timecourse_LrpSticky");
			
			for(int i=0; i<test1.length; i++){
				timeCourse.write("\n");
				for(int j=0; j<test1[0].length; j++){
					timeCourse.write(test1[i][j]+"\t");
				}
			}
			
			timeCourse.close();
		}catch(Throwable t){
			
		}
		
		test1=sm.buildMarkovChain("hdeABtestLrpConc", 8, 400, pm, "params2").runTimeCourse(3000, 10, "hdeABtestLrpConc1");
		try{
			FileWriter timeCourse=new FileWriter("timecourse_LrpConc");
			
			for(int i=0; i<test1.length; i++){
				timeCourse.write("\n");
				for(int j=0; j<test1[0].length; j++){
					timeCourse.write(test1[i][j]+"\t");
				}
			}
			
			timeCourse.close();
		}catch(Throwable t){
			
		}
		
		
		
		
	}

	public static void runMultipleFiles(String basename, int num){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		for(int i=1; i<=num-1; i++){
			sm.buildMarkovChain(basename+i, 2, 400, pm, "params2").runSimulationUntil(30000, basename+"k_aSet"+i);
		}
		
		sm.buildMarkovChain(basename+num, 3, 400, pm, "params2").runSimulationUntil(30000, basename+"k_aSet6");
		
		
	
	}
	
	public static void generateDoubleBarrierFiles(String basename, int[] dist, double cn, double swt, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					String filename=basename+"_d_"+d+"_cn_"+c+"_swt_"+s;
					try{
						FileWriter out=new FileWriter(filename);
						int pos=1;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
						pos=pos+21+d;
						out.write("B\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						pos=pos+21+d;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
						out.close();
						
						sm.buildMarkovChain(filename, 3, 400, pm, "params2").runSimulationUntil(100000, filename);
						
						
					}catch(Throwable t){
						System.out.println(t);
					}
				}
			}
		}
	}
	*/
	public static void generateDoubleBarrierFilesOpposite(String basename, int[] dist, double cn, double swt, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					String filename=basename+"_d_"+d+"_cn_"+c+"_swt_"+s;
					try{
						FileWriter out=new FileWriter(filename);
						int pos=1;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						pos=pos+21+d;
						out.write("B\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
						pos=pos+21+d;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						out.close();
						
						sm.buildMarkovChain(filename, 3, 400, pm, "params2").runSimulationUntil(50000, filename);
						
						
					}catch(Throwable t){
						System.out.println(t);
					}
				}
			}
		}
	}
	
	public static void generateDoubleBarrierClusterFilesOpposite(String basename, int[] dist, double cn, double swt, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					String filename=basename+"_d_"+d+"_cn_"+c+"_swt_"+s;
					try{
						FileWriter out=new FileWriter(filename);
						int pos=1;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						pos=pos+21+d;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+c+"\n");
						pos=pos+21+d;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						out.close();
						
						sm.buildMarkovChain(filename, 3, 400, pm, "params2").runSimulationUntil(3000, filename);
						
						
					}catch(Throwable t){
						System.out.println(t);
					}
				}
			}
		}
	}
	
	
	public static void generateSingleBarrierFiles(String basename, int[] dist, double cn, double swt, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					String filename=basename+"_d_"+d+"_cn_"+c+"_swt_"+s;
					try{
						FileWriter out=new FileWriter(filename);
						int pos=1;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
						pos=pos+21+d;
						out.write("B\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						
						out.close();
						
						sm.buildMarkovChain(filename, 2, 400, pm, "params2").runSimulationUntil(30000, filename);
						
						
					}catch(Throwable t){
						System.out.println(t);
					}
				}
			}
		}
	}
	

	public static void generateClusterFiles(String basename, int[] dist, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					String filename=basename+"_d_"+d+"_cn_"+c+"_swt_"+s;
					try{
						FileWriter out=new FileWriter(filename);
						int pos=1;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						pos=pos+21+d;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						
						out.close();
						
						sm.buildMarkovChain(filename, 2, 400, pm, "params2").runSimulationUntil(30000, filename);
						
						
					}catch(Throwable t){
						
					}
				}
			}
		}
	}
	
	
	public static void generateSingleBarrierFiles(String basename, int[] dist, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					String filename=basename+"_d_"+d+"_cn_"+c+"_swt_"+s;
					try{
						FileWriter out=new FileWriter(filename);
						int pos=1;
						out.write("A\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						pos=pos+21+d;
						out.write("B\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
						
						out.close();
						
						sm.buildMarkovChain(filename, 2, 400, pm, "params2").runSimulationUntil(30000, filename);
						
						
					}catch(Throwable t){
						
					}
				}
			}
		}
	}
	
	/*
	public static void generateComplexPairFiles(String basename, int[] dist, double cn, double swt, double[] conc, double[] t_0){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params2"));
		
		//cluster + switch experiments
		//The point of the experiments: does increasing the number of cluster-switches influence the stability of the switch?
		
		JSONtest json=new JSONtest();
		String exp="_cs_";
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					for(int i=2; i<7; i++){
						String filename=basename+exp+"d_"+d+"_cn_"+c+"_swt_"+s+"_type_"+i;
						try{
							FileWriter out=new FileWriter(filename);
							int pos=1;
							
							for(int j=0; j<i; j++){
								if(j%2==0){
									out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
								}else{
									out.write("B\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
								}
								pos=pos+21+d;
							}
							
							out.close();
						
							
							int[][] test1=sm.buildMarkovChain(filename, i, 400, pm, "params2").runTimeCourse(3000, 50, filename);
							
							try{
								FileWriter timeCourse=new FileWriter(filename+"_timecourse");
								
								for(int k=0; k<test1.length; k++){
									timeCourse.write("\n");
									for(int j=0; j<test1[0].length; j++){
										timeCourse.write(test1[k][j]+"\t");
									}
								}
								test1=null;
								timeCourse.close();
							}catch(Throwable t){
								
							}
							test1=null;
							
							int[][][] test2=sm.buildMarkovChain(filename, i, 400, pm, "params2").getTimeCourseTransitions(3000, 50, filename);
							
							//System.out.println(json.generateGraphJSON(test2, sm.getTFs())[0]);
						
							try{
								FileWriter timeCourse=new FileWriter(filename+"_transitions");
								
								for(int m=0; m<test2.length; m++){
									timeCourse.write("\n");
									for(int j=0; j<test2[0].length; j++){
										for(int k=0; k<test2[0][0].length; k++){
												if(test2[m][j][k]>5)
												timeCourse.write(""+j+","+k+"\t"+test2[m][j][k]+"\t");
										}
									}
								}
								test2=null;
								timeCourse.close();
							}catch(Throwable t){
								
							}
							test2=null;
							
						
						}catch(Throwable t){
						
						}
					}
				}
			}
		}
		
		//cluster + barrier experiments
		exp="_cb_";
		
		
		for(int d: dist){
			for(double c: conc){
				for(double s: t_0){
					for(int i=1; i<4; i++){
						String filename=basename+exp+"d_"+d+"_cn_"+c+"_swt_"+s+"_type_"+i;
						try{
							FileWriter out=new FileWriter(filename);
							int pos=1;
							
							for(int j=0; j<i; j++){
									out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
									pos=pos+21+d;
								}
							
							for(int j=0; j<i; j++){
									out.write("B\t"+pos+"\t"+(pos+20)+"\t"+s+"\t"+c+"\n");
									pos=pos+21+d;
								}
							
							for(int j=0; j<i; j++){
								out.write("A\t"+pos+"\t"+(pos+20)+"\t"+swt+"\t"+cn+"\n");
								pos=pos+21+d;
							}
							
							out.close();
						
							
							int[][] test1=sm.buildMarkovChain(filename, 3*i, 400, pm, "params2").runTimeCourse(3000, 50, filename);
							try{
								FileWriter timeCourse=new FileWriter(filename+"_timecourse");
								
								for(int k=0; k<test1.length; k++){
									timeCourse.write("\n");
									for(int j=0; j<test1[0].length; j++){
										timeCourse.write(test1[k][j]+"\t");
									}
								}
								test1=null;
								timeCourse.close();
							}catch(Throwable t){
								
							}
						
							
                              test1=null;
							
							int[][][] test2=sm.buildMarkovChain(filename, 3*i, 400, pm, "params2").getTimeCourseTransitions(3000, 50, filename);
							try{
								FileWriter timeCourse=new FileWriter(filename+"_transitions");
								
								for(int m=0; m<test2.length; m++){
									timeCourse.write("\n");
									for(int j=0; j<test2[0].length; j++){
										for(int k=0; k<test2[0][0].length; k++){
												if(test2[m][j][k]>5)
												timeCourse.write(""+j+","+k+"\t"+test2[m][j][k]+"\t");
										}
									}
								}
								test2=null;
								timeCourse.close();
							}catch(Throwable t){
								
							}
							test2=null;
						}catch(Throwable t){
						
						}
					}
				}
			}
		}
		
		//switch + barrier experiments
		exp="_sb_";
	}
	*/
	
	
	
}
