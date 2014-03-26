package binning;

import java.io.*; 
import java.util.*;

public class Binning { 
	public static void main(String[] args) {
		int k=0,sum=0,min;
		double mean;
		String dstr="",bstr="",boundstr="";
		 try { 
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
			BufferedReader file=new BufferedReader(new FileReader("binning.txt"));
			String str=file.readLine(); 
			String elements[]=str.split(",");
			
			int[] data=new int[elements.length];
			for(int i=0;i<elements.length;i++){ 
				data[i]=Integer.parseInt(elements[i]);
			 } 
			
			Arrays.sort(data);
			
			System.out.println("Enter Interval:");
			int interval=Integer.parseInt(br.readLine()); 
			int bins=(int)Math.ceil((double)elements.length/interval); 
			String datastr[]=new String[bins];
			for(int i=0;i<bins;i++){ 
				datastr[i]=""; 
			} 
			
			for(int i=0;i<bins;i++){ 
				for(int j=0;j<interval;j++){ 
					dstr+=(data[k]+","); k++;
				} 
			dstr=dstr.replaceAll(",$", ""); 
			datastr[i]=dstr; dstr=""; 
			} 
			System.out.println("Equi-Depth Bins"); 
			for(int i=0;i<bins;i++){ 
				System.out.println("Bin "+(i+1)+":"+datastr[i]); 
			} 
			int bindata[]=new int[interval];
			int binmeans[]=new int[interval];
			String binstr[]=new String[bins];
			for(int i=0;i<bins;i++){
				String[] binelements=datastr[i].split(",");
				for(int j=0;j<binelements.length;j++){
					bindata[j]=Integer.parseInt(binelements[j]);
					sum+=bindata[j];
				}
				
				mean=(double)(sum/interval);
				for(int j=0;j<binelements.length;j++){
					binmeans[j]=(int)mean;
					bstr+=binmeans[j]+",";
				}
				bstr=bstr.replaceAll(",$","");
				binstr[i]=bstr;
				sum=0;bstr="";
			}
			
			System.out.println("Smoothing by bin means:");
			for(int i=0;i<bins;i++){
				System.out.println("Bin "+(i+1)+":"+binstr[i]);
			}
			String binboundaries[]=new String[bins];
			int boundarydata[]=new int[interval];
			for(int i=0;i<bins;i++){
				String[] binelements=datastr[i].split(",");
				bindata[0]=Integer.parseInt(binelements[0]);
				bindata[binelements.length-1]=Integer.parseInt(binelements[binelements.length-1]);
				for(int j=0;j<binelements.length;j++){
					bindata[j]=Integer.parseInt(binelements[j]);
					int lowerminbound=Math.abs(bindata[0]-bindata[j]);
					int higherminbound=Math.abs(bindata[binelements.length-1]-bindata[j]);
					if(lowerminbound<higherminbound){
						boundarydata[j]=bindata[j]-lowerminbound;
					}
					else
						boundarydata[j]=bindata[j]+higherminbound;
					
					boundstr+=boundarydata[j]+",";
				}
				boundstr=boundstr.replaceAll(",$", "");
				binboundaries[i]=boundstr;
				boundstr="";
			}
			
			System.out.println("Smooothing by bin boundaries");
			for(int i=0;i<bins;i++){
				System.out.println("Bin "+(i+1)+":"+binboundaries[i]);
			}
			
		} catch (Exception e) { e.printStackTrace(); }
	 }
 }