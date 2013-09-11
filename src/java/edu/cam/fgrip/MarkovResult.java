package edu.cam.fgrip;
import java.util.*;
public class MarkovResult {

	List<State> sink;
	List<String> jsons;
	public int[][] timeNet;
	
	public MarkovResult(List<State> s, List<String> j, int[][] n){
		sink=s;
		jsons=j;
		timeNet=n;
	}
	
	public String getFirstOccupancy(){
		return State.getFirstOccupancyString(sink);
	}
	
	public String getTimeCourse(){
		String out="";
		for(int[] is: timeNet){
			for(int i: is){
				out=out+i+" ";
			}
			out=out+"<br>";
		}
		return out;
	}
	
	public List<String> getJsons(){
		return jsons;
	}
}
