
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.LinkedList;

/********************* aproksymacja funkcji PgK  ************************
 * 						WSTECZNA PROPAGACJA cz2. 
 * 
 * 
 * Program tworzy siec o dowolnej liczbie warstw i dowolnej liczbie neuronów,
 * z tym ze w zerowej wartswie sieci musi byæ n neuronow o 1 wejsciu. Kazdy neuron
 * w warstwie zerowej odpowiada kolejnym kolumna z wiersza uczacego. Propagacja wsteczna
 * korzysta ze wzoru z pochodna.
 * 
 * 								Funkcje:
 * 
 *	
 *
 */


public class main 
{

	public static void main(String[] args) 
	{	
		
		int [] topologiaSieci  = {1,5,1};		// po kolei liczba neuronow w w warstwach: wejscie 1, warstwa ukrywa 3, wyjscie 1
		
		double [] MSE = new double[2];
		
		
		Siec s1 = new Siec (topologiaSieci, MSE );		// liczba neuronow w warstwie 1, liczba neuronow w warstwie 2, liczba neuronow w warstwie 3
		
		
		for (int i = 0 ; i < 2000 ; i++)
		{
			MSE[0] = 0;
			for(int j = 0 ; j < 7000 ; j++)
			{
				s1.start(j);
			}
			MSE [0]= MSE[0] /7000;
			System.out.println(MSE[0]);	
			
		}


	}
	

}
