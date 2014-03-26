package apriori;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Apriori {

	static HashMap<String, String> map=new HashMap<String,String>();
    static HashMap<Character,Integer> freq=new HashMap<Character,Integer>();
    static HashMap<String,Integer> pairsmap=new HashMap<String, Integer>();
    
    public static void main(String[] args) {
    	int j,k,l;
        String tid;
        String str;
        
       
        try{
            BufferedReader br=new BufferedReader(new FileReader("apriori.txt"));
            
            while((str=br.readLine())!=null){
                String elements[]=str.split("\t");
                tid=elements[0];
                String itemstr=elements[1];
                itemstr=itemstr.replaceAll(",","");
                map.put(tid, itemstr);
            }
           
            String values="";
            for(Object val:map.values()){
                values+=(String)val;
            }
            
            values=values.replaceAll("\\s+","");
            char[] itemsArr=values.toCharArray();
            int occurence;
            
            for(j=0;j<itemsArr.length;j++){
                if(freq.containsKey(itemsArr[j])){
                    occurence=freq.get(itemsArr[j]);
                    freq.put(itemsArr[j], occurence+1);
                }
                else{
                    freq.put(itemsArr[j], 1);
                }
            }
            
            System.out.println("Step-1:");
            
            for(Character chars:freq.keySet()){
                int occur=freq.get(chars);
                char key=chars;
                System.out.println(key +"\t"+occur);
            }
            
            System.out.println();
            
            for(Iterator<Map.Entry<Character,Integer>>it=freq.entrySet().iterator();it.hasNext();){
                Map.Entry<Character, Integer> entry = it.next();
                if (entry.getValue() <3) {
                     it.remove();
                }
            }
            
            char keyArr[]=new char[freq.size()];
            System.out.println("Step-2:");
            j=0;
            
            for(Character chars:freq.keySet()){
                int occur=freq.get(chars);
                char key=chars;
                System.out.println(key +"\t"+occur);
                keyArr[j]=key;
                j++;
                
            }
            
            System.out.println();
            
            int pairOccurence=0;
            int n=freq.size();
            int pairs_size=n*(n-1)/2;
            
            String[] pairs=new String[pairs_size];
            l=0;
            for(j=0;j<freq.size();j++){
            	for(k=j+1;k<freq.size();k++){
            		pairs[l]=keyArr[j]+""+keyArr[k];
            		pairOccurence=getOccurence(pairs[l]);
            		pairsmap.put(pairs[l],pairOccurence);
            		l++;
            	}
            }
            
            System.out.println("Step-3:");
            for(String pairkeys:pairsmap.keySet()){
            	int pairoccur=pairsmap.get(pairkeys);
            	String key=pairkeys;
            	System.out.println(key+"\t"+pairoccur);
            }
            System.out.println();
            
            for(Iterator<Map.Entry<String,Integer>>it=pairsmap.entrySet().iterator();it.hasNext();){
                Map.Entry<String, Integer> entry = it.next();
                if (entry.getValue() <3) {
                     it.remove();
                }
            }
            
            System.out.println("Step-4:");
            for(String pairkeys:pairsmap.keySet()){
            	int pairoccur=pairsmap.get(pairkeys);
            	String key=pairkeys;
            	System.out.println(key+"\t"+pairoccur);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    protected static int getOccurence(String pair){
    	
    	int occurence=0;
    	String key;
    	int check=0;
    	char pairarr[]=pair.toCharArray();
    	for(String str:map.values()){
    		key=str;
    		char keyarr[]=key.toCharArray();
    		for(int i=0;i<keyarr.length;i++){
    			if(pairarr[0]==keyarr[i])
    				check++;
    			if(pairarr[1]==keyarr[i])
    				check++;
    			if(check==2){
    				occurence++;
    				break;
    			}
    			
    		}
    		
    		check=0;
    	}
    	return occurence;
    }
    
}