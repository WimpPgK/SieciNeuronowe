import java.util.Scanner;

public class Main 
{
	
	public static void main(String[] args)
	{
		Main program = new Main();
	}
	
	
	Main()
	{	
		int iloscWejsc = 2;	
		String [] rekordUczacy = null;
		OdczytZPliku odczyt = new OdczytZPliku();
		Neuron n1 = new Neuron(iloscWejsc);	// ile neuron ma miec wejsc	
		double wyjscie = 0;
		double bladSieci = 0;
		double krok = 0.01;
		double nowaWaga = 0;
		double sumaBledowSieciWEpoce = 0;
		
		
		
		
		
		
		
		for (int epoka = 0 ; epoka < 10000 ; epoka++)
		{

			odczyt.otworzPlik();
			sumaBledowSieciWEpoce = 0;
			//***********  POCZ¥TEK EPOKI*****************
			while (true)
			{
				rekordUczacy = odczyt.zwrocLinie();
				
				
				
				if (rekordUczacy[0] == "Koniec")
				{
					break;
				}
				
				n1.ustawWejscia(rekordUczacy);
				
				wyjscie = n1.funckaAktywacji(n1.blokSumujacy());
				System.out.println("F(Pm) = " + wyjscie);
				
				
				
				bladSieci = Double.valueOf(rekordUczacy[iloscWejsc]);
				System.out.println("Wartosc oczekiwana: " + bladSieci);
				bladSieci = bladSieci - wyjscie;
				System.out.println("bladSieci:" + bladSieci);
				
				//System.out.println("Blad sieci = " + bladSieci);
				
				sumaBledowSieciWEpoce += bladSieci;
				
				// WAZNE!!!! wrzuæ to do funkcji a ca³osc z maina do klasy obslugaNeuronu
				//korektaWagPerceptronu();
				/*
				 * 
				 */
				for (int i = 0 ; i < iloscWejsc; i++)
				{
					System.out.println("Stara waga = " + n1.waga.get(i));
					nowaWaga = (n1.waga.get(i)) + (krok*bladSieci*n1.wejscie.get(i));
					System.out.println("Nowa waga = " + nowaWaga);
					n1.waga.set(i, nowaWaga);
				}
				// Koniec korekty
				
				//System.out.println("\n");

			}
			//************************** KONIEC epoki **********************************
			System.out.println("");
			
		
		}
		
		System.out.println("Koniec Uczenia");
		Scanner scan = new Scanner(System.in);
		//***********************************************************************
		// TestSieci
		while (true)
		{
			//**** Do uzupelnienia przez ubslugujacego ********
			
			String[] tab = new String[10];
			tab[0] = scan.nextLine();
			tab[1] = scan.nextLine();


			
			
			
			// obsluga neuronu
			n1.ustawWejscia(tab);
			wyjscie = n1.funckaAktywacji(n1.blokSumujacy());
			System.out.println("Na wyjsciu dostalismy: " + wyjscie);
			
		}
		
	}
	

}
