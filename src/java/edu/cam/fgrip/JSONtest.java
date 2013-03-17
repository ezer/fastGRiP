package edu.cam.fgrip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONtest {

	public void test(){
		Gene g1=new Gene("MarA", true, 2, 22);
		Gene g2=new Gene("Lrp", false, 20, 40);
		ArrayList<Gene> config= new ArrayList<Gene>();
		config.add(g1);
		config.add(g2);
		JSONArray jsonObj = new JSONArray( config );
        System.out.println( jsonObj);
	}
	
	public ArrayList<String> generateGraphJSON(int[][][] edges, List<TranscriptionFactor> tfs){
		ArrayList<String> jsons=new ArrayList<String>(); //first JSON is the network, the second JSON is the configs
		
		/**1. find which states/edges need to be included
		 *     a) turn into Pair<State>: sum map (from the last 50% of the time points)
		 *     b) get rid of key/value pairs <threshold (such that the average number of transitions>5)
		 * 2. for each pair, generate object
		 */
		 
		Map<Pair, Integer> m=new HashMap<Pair, Integer>();
		
		for(int i=edges.length/2; i<edges.length; i++){
			
			for(int j=0; j<edges[0].length; j++){
				for(int k=0; k<edges[0][0].length; k++){
					Pair p=new Pair(j, k);
						if(edges[i][j][k]!=0){
						if(!m.containsKey(p)){
							m.put(p, 0);
						}
							m.put(p, m.get(p)+edges[i][j][k]);
						}
						//timeCourse.write(""+j+","+k+"\t"+test1[i][j][k]+"\t");
				}
			}
		}
		
		//generate network for not rare ones
		
		ArrayList<Edge> edgeList=new ArrayList<Edge>();
		Map<Node, ArrayList<Gene>> configs=new HashMap<Node, ArrayList<Gene>>();
		for (Pair p: m.keySet()){
			System.out.println(p.first+", "+p.second+", "+m.get(p)+", "+(edges.length/2));
			//System.out.println(m.get(p));
			if(m.get(p)>((2*edges.length/2))){
				edgeList.add(makeEdge(p, m.get(p)));
				Node f=new Node(p.first);
				Node s=new Node(p.second);
				if(!configs.containsKey(f)){
					configs.put(f, makeConfig(p.first, tfs));
				}
				if(!configs.containsKey(s)){
					configs.put(s, makeConfig(p.second, tfs));
				}
				
			}
		}
		
		ArrayList<Node> nodeList=new ArrayList<Node>();
		ArrayList<ArrayList<Gene>> geneList=new ArrayList<ArrayList<Gene>>(); 
		//jsons[0]=;
		for(Node n: configs.keySet()){
			n.config=nodeCounter;
			nodeCounter++;
			nodeList.add(n);
			geneList.add(configs.get(n));
		}
		
		jsons.add((new JSONArray(geneList)).toString());
		jsons.add((new JSONObject(new NetworkJSON(nodeList, edgeList))).toString());
		
		
		return jsons;
	}
	
	private ArrayList<Gene> makeConfig(Integer state, List<TranscriptionFactor> tfs) {
		ArrayList<Gene> genes=new ArrayList<Gene>();
		for(TranscriptionFactor tf: tfs){
			boolean present=(state & 1)==1;
			String name=tf.getName();
			int start=tf.getStart();
			int end=tf.getEnd();
			state=state >> 1;
			genes.add(new Gene(name, present, start, end));
		}
		
		return genes;
	}

	private Edge makeEdge(Pair p, int weight) {
		return new Edge(""+p.first+"to"+p.second, ""+p.second, ""+p.first, ""+p.first+"to"+p.second, weight);
	}

	
	class NetworkJSON{
		DataSchema dataSchema;
		Data data;
		public DataSchema getDataSchema(){
			return dataSchema;
		}
		
		public Data getData(){
			return data;
		}
		
		public NetworkJSON(ArrayList<Node> nodes, ArrayList<Edge> edges){
			dataSchema=new DataSchema();
			data=new Data(nodes, edges);
		}
	}
	
	class Data{
		ArrayList<Node> nodes;
		ArrayList<Edge> edges;
		
		public ArrayList<Node> getNodes(){
			return nodes;
		}
		
		public ArrayList<Edge> getEdges(){
			return edges;
		}
		
		public Data(ArrayList<Node> n, ArrayList<Edge> e){
			nodes=n;
			edges=e;
		}
	}
	
	class DataSchema{
		ArrayList<Label> nodes;
		ArrayList<Label> edges;
		
		public ArrayList<Label> getNodes(){
			return nodes;
		}
		
		public ArrayList<Label> getEdges(){
			return edges;
		}
		
		public DataSchema(){
			nodes=new ArrayList<Label>();
			edges=new ArrayList<Label>();
			nodes.add(new Label("label", "string"));
			nodes.add(new Label("config", "int"));
			edges.add(new Label("label", "string"));
			edges.add(new Label("weight", "float"));
		}
	}
	class Label{
		String name;
		String type;
		
		public Label(String s, String t){
			name=s;
			type=t;
		}
		
		public String getName(){
			return name;
		}
		
		public String getType(){
			return type;
		}
	}
	
	class Pair implements Comparable<Pair>{
		Integer first;
		Integer second;
		
		public Pair(Integer fir, Integer sec){
			
			first=fir;
			second=sec;
			
		}

		@Override
		public int compareTo(Pair o) {
			if(first!=o.first)
				return first.compareTo(o.first);
			else 
				return second.compareTo(o.second);
		}
		@Override
		public int hashCode(){
			return first*107+second*7;
		}
		
		@Override
		public boolean equals(Object obj){
			Pair p=(Pair) obj;
			return p.first==first && p.second==second; 
		}
	
	}
	
	
	class Edge{
		public String getId() {
			return id;
		}

		public String getTarget() {
			return target;
		}

		public String getSource() {
			return source;
		}

		public String getLabel() {
			return label;
		}

		public float getWeight() {
			return weight;
		}

		String id;
		String target;
		String source;
		String label;
		float weight; 
		
		public Edge(String i, String t, String s, String l, float w){
			id=i;
			target=t;
			source=s;
			label=l;
			weight=w;
		}
		
		
		
	}
	public static int nodeCounter=1;
	class Node{
		String id;
		String label;
		int config;
		
		public Node(Integer first) {
			config=0;
			label=""+first;
			id=label;
		}
		
		@Override
		public boolean equals(Object o){
			return label.equals(((Node) (o)).label);
		}
		
		public String getId() {
			return id;
		}

		public String getLabel() {
			return label;
		}

		public int getConfig() {
			return config;
		}

		@Override
		public int hashCode(){
			return label.hashCode();
		}
		
	}
	
	class Gene{
		String name;
		boolean present;
		int start;
		int end;
		
		public String getName(){
			return name;
		}
		
		public boolean getPresent(){
			return present;
		}
		
		public int getStart(){
			return start;
		}
		
		public int getEnd(){
			return end;
		}
		public Gene(String n, boolean p, int s, int e){
			name=n;
			present=p;
			start=s;
			end=e;
		}
	}
	
	public String generateAreaJSON(int[][] test1, int delta) {
		ArrayList<Map<String,Integer>> elements=new ArrayList<Map<String,Integer>>();
		
		int time=0;
		for(int i=0; i<test1[0].length; i++){
			Map<String, Integer> timeSlice=new HashMap<String, Integer>();
			timeSlice.put("time", time);
			time+=delta;
			for(int j=0; j<test1.length; j++){
				timeSlice.put(""+j, test1[j][i]);
			}
			elements.add(timeSlice);
		}
		return (new JSONArray(elements)).toString();
	}
	
}
