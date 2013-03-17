package edu.cam.fgrip;
import java.util.*;

import edu.cam.fgrip.State.Token;

public class Test1 {

	public static void main(String[] args){
		
		Test1 t=new Test1();
		t.run();
	}
	
	public void run(){
		State s0=new State();
		State s1=new State();
		State s2=new State();
		
		s1.myTransitions.put(s0, 1.0);
		s1.myTransitions.put(s2, 1/10.0);
		s2.myTransitions.put(s0, 1.0);
		s2.myTransitions.put(s1, 1/10.0);
		
		for(int i=0; i<10000; i++){
		    s1.myTokens.add(new Token());
		}
		
		for(int i=0; i<500; i++){
			s1.updateState();
			s2.updateState();
		}
		
		double count=0.0;
		double sum=0.0;
		
		for(Token t: s0.myTokens){
			System.out.println(t.time);
			count++;
			sum+=t.time;
		}
		
		System.out.println("average time: "+(sum/count));
		
	}
	
	
	class State{
		List<Token> myTokens=new ArrayList<Token>();
	    Map<State, Double> myTransitions=new HashMap<State, Double>(); 
	    double  myPropensitySum=0;
	  
	    
		Random r=new Random();
        public void updateState(){
			
        	if(myPropensitySum==0){
        		for(Double d: myTransitions.values()){
        			myPropensitySum+=d;
        		}
        	}
        	
        	
			for(int i=myTokens.size()-1; i>=0; i--){
				
				double selection=r.nextDouble();
			
				if(!myTokens.get(i).recentlyMoved){
					//call Gillespie
					
				
					
					State nextState = Gillespie.getNextReaction(selection*myPropensitySum, myTransitions); 
					Token t=myTokens.remove(i);
					
					//update time in state
					//if(!t.timesAtEachConfig.containsKey(myID)){
					//	t.timesAtEachConfig.put(myID, 0.0);
					//}
					
					//update time at this config
					//t.timesAtEachConfig.put(myID, t.timesAtEachConfig.get(myID)+(t.time-t.prevTime)); 
					
					
					//update time
					t.time+=Gillespie.computeNextReactionTime(myPropensitySum, r);
					
					//add to Path:
					
					
					//add to next state
					
						nextState.addToken(t);
					
				
				}else{
					myTokens.get(i).recentlyMoved=false;
				}
			}
			
		}
		private void addToken(Token t) {
			// TODO Auto-generated method stub
		    t.recentlyMoved=true;
            myTokens.add(t);
		}
		
	    
	    
	    
	}
	class Token{
		double time=0;
		boolean recentlyMoved=false;
	}
	
	
}
