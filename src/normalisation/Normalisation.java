package normalisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Normalisation {
	static double cpi,new_cpi_minmax,mean,variance,sd,sum=0.0,
			sum_for_variance=0.0,new_cpi_zscore,new_cpi_scaling;
	public static void main(String[] args) {
		int min=0,max=10,new_min,new_max,count=0,i;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter CPI");
			cpi=Double.parseDouble(br.readLine());
			System.out.println("Min-Max Normalisation:");
			System.out.println("Enter new min & max:");
			new_min=Integer.parseInt(br.readLine());
			new_max=Integer.parseInt(br.readLine());
			new_cpi_minmax=((cpi-min)*(new_max-new_min)/(max-min))+new_min;
			System.out.println("New CPI:"+" "+new_cpi_minmax);
			
			System.out.println("Z-Score Normalisation");
			
			for(i=min;i<max;i++){
				sum+=i;
				count++;
			}
			mean=sum/count;
			
			for(i=min;i<max;i++){
				sum_for_variance+=Math.pow((i-mean), 2);
			}
			variance=sum_for_variance/(max-min+1);
			sd=Math.sqrt(variance);
			new_cpi_zscore=(cpi-mean)/sd;
			System.out.println("New CPI:"+" "+new_cpi_zscore);
			
			System.out.println("Decimal Scaling Normalisation:");
			System.out.println("Enter value of j:");
			int j=Integer.parseInt(br.readLine());
			new_cpi_scaling=cpi/Math.pow(10, j);
			System.out.println("New CPI:"+" "+new_cpi_scaling);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
