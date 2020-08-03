
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.LinkedList;

/********************* aproksymacja funkcji PgK  ************************
 * 						WSTECZNA PROPAGACJA cz2. 
 * 
 
 *	
 *
 */


public class main 
{

	public static void main(String[] args) 
	{	
		

		Siec s1 = new Siec();		// liczba neuronow w warstwie 1, liczba neuronow w warstwie 2, liczba neuronow w warstwie 3
		double bladSredniokwadratowy = 0;
		for(int i = 0 ; i < 400 ; i++)
		{
			s1.dzialaj();
			System.out.println();
			System.out.println();
			System.out.println("/*************************************");
			System.out.println();
			System.out.println();
			
		}
	}
	

}
