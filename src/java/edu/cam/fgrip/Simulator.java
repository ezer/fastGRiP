package edu.cam.fgrip;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

 
public class Simulator {

	public static void main(String[] args){
		Simulator s=new Simulator();
		s.testJSONlinking();
		
		//JSONtest t=new JSONtest();
		//t.test();
		//testMarA();
	//	testTimeCourseTransitions();
		int[] a={-5, 0, 5, 100};
		double[] b={5, 10, 100};
		double[] c={0.033, 0.33, 3.3};
		int[] a_1={0};
		double[] b_1={10, 100};
		//generateClusterFiles("clusters", a_1, b_1, c);
		//generateSingleBarrierFiles("singlebarriers", a, 10, 0.33, b, c);
		//generateDoubleBarrierFiles("doublebarriers", a, 10, 0.33, b,c);
		//generateComplexPairFiles("complex", a, 10, 0.33, b, c);
		
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
		
		MarkovChainGenerator sm=new MarkovChainGenerator();
		SwitchSlideModel pm=new SwitchSlideModel(new Parameters("params"));
		sm.buildMarkovChain("mimickingGRiP1", 2, 400, pm, "params2").getJSONs(30000, 10, "cluster");
	}
	
	private static void testMarA(){
		MarkovChainGenerator sm=new MarkovChainGenerator();
		ProbabilityModel pm=new SwitchSlideModel(new Parameters("params"));
		sm.buildMarkovChain("hdeAB_MarA", 1, 400, pm, "params").runSimulationUntil(30000, "marA_");
		sm.buildMarkovChain("hdeAB_PhoP", 1, 400, pm, "params").runSimulationUntil(30000, "phoP_");
		sm.buildMarkovChain("hdeAB_MarA_PhoP", 2, 400, pm, "params").runSimulationUntil(30000, "marAphoP_");
		sm.buildMarkovChain("hdeAB_TorR", 2, 400, pm, "params").runSimulationUntil(30000, "torR_");
	}
	
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
						
						sm.buildMarkovChain(filename, 3, 400, pm, "params2").runSimulationUntil(30000, filename);
						
						
					}catch(Throwable t){
						
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
	
	
	
	
}
