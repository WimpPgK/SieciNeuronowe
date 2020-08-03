import java.util.Scanner;

public class ObslogaNeuron 
{	
	private double [][] tab;	// tablica z rekordami uczacymi
	private int liczbaWejsc;
	private int n;	// liczba rekordow uczacych
	private Neuron n1;	
	private int numerEpoki;
	private double waga;
	private double krok;
	private double sumaKwadratow;	// suma (x - xn)^2   bledow 
	double mape;
	public Zapis z1,z2;	// zapis do pliku bledu mes i mape
	public ObslogaNeuron() 
		{
			 OdczytZPliku o1 = new OdczytZPliku();
			 tab = o1.zapisDoTab();
			 liczbaWejsc = tab[0].length-1;
			 n = tab.length;
			 n1 = new Neuron(liczbaWejsc);
			 z1 = new Zapis("MSE.txt");
			 z2 = new Zapis("MAPE.txt");
			 

		}
		
		
		public void epoka()
		{
			sumaKwadratow = 0;
			mape = 0;
			//System.out.println("Nowa epoka rozpoczeta!");
			for (int i = 0 ; i < n ; i ++)
			{
				n1.ustawWejscia(tab[i]);
				korektaWag(tab[i][liczbaWejsc], odpowiedzSieci());
			}
			//System.out.println("SumaBledow = " + sumaBledow);
			System.out.println("SEM = " + (sumaKwadratow/n));
			z1.zapiszTo(Double.toString(sumaKwadratow/n));
			z2.zapiszTo(Double.toString(mape/n));
			
		}
		
		
		public void uczenie(int iteracje, double k)
		{
						
			krok  = k;
			
			for (numerEpoki = 0 ; numerEpoki < iteracje ; numerEpoki++)	
			{
				epoka();
			}
			

		}
		
		
		//**************************************************
		double odpowiedzSieci()
		{	
			double pom1, pom2;
			pom1 = n1.blokSumujacy();
			pom2 = n1.funckaAktywacji(pom1);
			//System.out.print(") " + "f(" + pom1 + ") = "+ pom2);

			return pom2;
		}
		
		//**************************************************
		void korektaWag(double y_oczekiwana, double y_sieci)
		{	
			for (int i = 0 ; i < liczbaWejsc ; i++)
			{
				waga = n1.waga.get(i);
				//System.out.println("i = " + i);
				double pom = y_oczekiwana - y_sieci;
				sumaKwadratow += Math.pow(pom, 2);
				
				if (y_oczekiwana > y_sieci)
				{
					mape += y_oczekiwana - y_sieci;
				}
				else
				{
					mape += y_sieci - y_oczekiwana;
				}
				//System.out.print("Waga poprzednia: " + waga);
				waga = waga + krok*(y_oczekiwana - y_sieci)*n1.wejscie.get(i);
				//System.out.print("Waga nowa: " + waga + "\n");
				n1.waga.set(i, waga);
				//System.out.println("W = " + n1.waga.get(i));
				//System.out.println("\nw" + i + " = " + waga);
			}
		}
		
		void testNeuronu()
		{
		
			System.out.println("Koniec Uczenia czas na testy");
			Scanner scan = new Scanner(System.in);
			//***********************************************************************
			// TestSieci
			while (true)
			{
				//**** Do uzupelnienia przez ubslugujacego ********
				
				int[] tab = new int [liczbaWejsc];
				for (int i = 0 ; i < liczbaWejsc ; i++)
				{
						
					n1.wejscie.set(i, scan.nextDouble());

				}

				
				System.out.println(odpowiedzSieci());

				
			}
			
		
		}
		
}
