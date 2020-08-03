
public class ObslogaNeuron 
{
	
		int iloscWejsc = 2;	
		String [] rekordUczacy = null;
		OdczytZPliku odczyt = new OdczytZPliku();
		Neuron n1 = new Neuron(iloscWejsc);	// ile neuron ma miec wejsc	
		double wyjscie = 0;
		double bladSieci = 0;
		double krok = 0.1;
		double nowaWaga = 0;
		double sumaBledowSieciWEpoce = 0;
		int index;
		int  epoka;
	
		public ObslogaNeuron() 
		{
			
		}
		
		
		
		public void nauka()
		{
			
			// petla której 1 iteracja to przelecenie
			//	po wszystkich rekordach uczacych
			
			
			
			
			for (epoka = 0 ; epoka < 10000000 ; epoka++)	
			{
				index = 0;
				odczyt.otworzPlik();

				while (true)
				{	
					index++;
					// tablica stringów w której ka¿dy element oddzielony spacja
					// z pliku ucz¹cego jest w oddzielnej zmiennej
					rekordUczacy = odczyt.zwrocLinie();	
					
					
					// je¿eli w pliku pobierzemy linie w której nie ma ¿adnych danych
					// to znaczy, ¿e doszliœmy do koñca rekordów ucz¹cych
					// i powinniœmy zakoñczyæ petle while
					if (rekordUczacy[0] == "Koniec")
					{
						break;
					}
					// nasz neuton przyjmuje tablice stringów
					n1.ustawWejscia(rekordUczacy);
					//n1.wypiszDaneNeuronu();
					
					
					double pom;
					pom = odpowiedzSieci();
					korektaWag(pom);
				}
			}
			

		}
		//**************************************************
		double odpowiedzSieci()
		{	
			double pom1, pom2;
			pom1 = n1.blokSumujacy();
			pom2 = n1.funckaAktywacji(pom1);
			if (epoka > 10000000 - 200)
			System.out.print(index + ") " + "f(" + pom1 + ") = "+ pom2);

			return pom2;
		}
		
		//**************************************************
		void korektaWag(double y_sieci)
		{	
			double nowaWaga;
			double staraWaga;
			double wartoscOczekiwana= Double.valueOf(rekordUczacy[iloscWejsc]);
			
			if (y_sieci == wartoscOczekiwana)
			{	
				if (epoka > 10000000 - 200)
				System.out.println("    bez zmian");
			}
			else
			{
				
				if (y_sieci == 0)
				{
					for (int i = 0 ; i < iloscWejsc ; i++)
					{
						staraWaga = n1.waga.get(i);
						nowaWaga = staraWaga + Double.valueOf(rekordUczacy[i]);
						n1.waga.set(i, nowaWaga);
						if (epoka > 10000000 - 200)
						if (epoka > 10000000 - 200)
						System.out.println("   w" + i +" = "+ nowaWaga);


					}
				}
				else
				{
					for (int i = 0 ; i < iloscWejsc ; i++)
					{
						staraWaga = n1.waga.get(i);
						nowaWaga = staraWaga - Double.valueOf(rekordUczacy[i]);
						n1.waga.set(i, nowaWaga);
						if (epoka > 10000000 - 200)
						System.out.println("   w" + i +" = "+ nowaWaga);


					}
					
				}
			}
	
		}
			
			

			
			//n1.zmienWagi(tab);

		
}
