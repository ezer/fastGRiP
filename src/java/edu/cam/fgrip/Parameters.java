package edu.cam.fgrip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parameters {

	private Map<String, Double> params=new HashMap<String, Double>();
	
	public Parameters(String filename){
		parseParams(filename);
	}
	
	public Parameters updateParameters(String k_a, String nonCognateTF, String TFlength, String f, String beta, String s_l, String e_star){
		/*
		if(params.containsKey("k_a") && k_a!=null && k_a.length()>0){
			params.put("k_a", Double.parseDouble(k_a));
		}else if(params.containsKey("k_assoc") && k_a!=null && k_a.length()>0){
			params.put("k_assoc", Double.parseDouble(k_a));
		}
		
		if(params.containsKey("nonCognateTF") && nonCognateTF!=null && nonCognateTF.length()>0){
			params.put("nonCognateTF", Double.parseDouble(nonCognateTF));
		}
		
		if(params.containsKey("TFlength") && TFlength!=null && TFlength.length()>0){
			params.put("TFlength", Double.parseDouble(TFlength));
		}
		
		if(params.containsKey("f") && f!=null && f.length()>0){
			params.put("f", Double.parseDouble(f));
		}
		
		if(params.containsKey("beta") && beta!=null && beta.length()>0){
			params.put("beta", Double.parseDouble(beta));
		}
		
		if(s_l!=null && s_l.length()>0){
			params.put("s_l_obs", Double.parseDouble(s_l));
			params.put("s_l_real", Double.parseDouble(s_l));
		}
		
		if(params.containsKey("e_star") && e_star!=null && e_star.length()>0){
			params.put("e_star", Double.parseDouble(e_star));
		}else if(params.containsKey("t_r") && e_star!=null && e_star.length()>0){
			params.put("t_r", Double.parseDouble(e_star));
		}
		*/
		return this;
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
