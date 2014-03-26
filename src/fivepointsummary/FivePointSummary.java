package fivepointsummary;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class FivePointSummary {

	public static void main(String[] args) {
		double min,first_quartile,third_quartile,median,mean,max,first_sum=0,second_sum=0;
		int median_index,first_quartile_size=0,third_quartile_size=0;
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String input=br.readLine();
			String elements[]=input.split(" ");
			int size=elements.length;
			int data[]=new int[size];
			for(int i=0;i<elements.length;i++){
				data[i]=Integer.parseInt(elements[i]);
			}
			
			Arrays.sort(data);
			min=data[0];
			max=data[elements.length-1];
			if(size%2==0){
				median=(data[size/2-1]+data[(size/2)])/2.0;
				median_index=size/2-1;
			}
			else{
				median=data[size/2];
				median_index=size/2;
			}
			
			int first_quartile_arr[]=new int[size/2+1];
			for(int i=0;i<=median_index;i++){
				first_quartile_arr[i]=data[i];
				first_quartile_size++;
				
			}
			
			if(first_quartile_size%2==0)
				first_quartile=(first_quartile_arr[first_quartile_size/2-1]+first_quartile_arr[first_quartile_size/2])/2.0;
			
			else
				first_quartile=(first_quartile_arr[first_quartile_size/2]);



			int third_quartile_arr[]=new int[size/2+1];
				median_index=size/2;

            for(int i=median_index;i<=size-1;i++){
                third_quartile_arr[i-median_index]=data[i];
                third_quartile_size++;
            }
			
			if(third_quartile_size%2==0)
				third_quartile=(third_quartile_arr[third_quartile_size/2-1]+third_quartile_arr[third_quartile_size/2])/2.0;
			
			else
				third_quartile=(third_quartile_arr[third_quartile_size/2]);
			
			System.out.println("5-Point Summary:");
			System.out.println("Min:"+" "+min);
			System.out.println("First Quartile:"+" "+first_quartile);
			System.out.println("Median:"+" "+median);
			System.out.println("Third Quartile:"+" "+third_quartile);
			System.out.println("Max:"+" "+max);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
