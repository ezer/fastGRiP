package edu.cam.fgrip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parameters {

	private Map<String, Double> params=new HashMap<String, Double>();
	
	public Parameters(String filename){
		parseParams(filename);
	}
	
	public void parseParams(String filename){
		File f=new File(filename);
		try {
			Scanner in=new Scanner(f);
			while (in.hasNext()){
				String heading=in.next().toUpperCase();
				Double d=in.nextDouble();
				params.put(heading,  d);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Double get(String s){
		return params.get(s.toUpperCase());
	}

	public boolean contains(String string) {
		return params.containsKey(string.toUpperCase());
	}
}
