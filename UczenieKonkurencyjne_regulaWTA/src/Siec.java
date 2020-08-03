import java.io.FileNotFoundException;
import java.io.PrintWriter;

// *******************************************************************
//  void WspUczenia()- oblicza wspolczynnik uczenia
//  void pobierzRekord () - pobiera kolejny rekord uczacy
//  void dzialaj(int n) - sprawdza ktory neuron wygrywa dla danego wektora wejsciowego
//                        n jest numerem rekordu uczacego. Dla zwycieskiego rekordu
//						  wywoluje funkcje korekty wag
//  double wspUczenia(int n) - oblicza wspolczynnik uczenia, ktory powinien malec
//							   wraz z postepem uczenia
//
/***********************************************************************/


public class Siec 
{
	
	Neuron [][] n;
	int promienSasiedstwa;
	int wymiarSieci;
	Odleglosc []odleglosc;
	
	
	double [][] tab;
	int [] zwyciezcy;
	Zapis z1;
	int a,b;  // a: liczba rekordow uczacych , b:  liczba wartosci w rekordzie uczacym
	PrintWriter zapis;
	public Siec()
	{
		
		wymiarSieci = 10;
		odleglosc = new Odleglosc[wymiarSieci*wymiarSieci];
		for (int i = 0; i < wymiarSieci*wymiarSieci; i++) 
		{
			odleglosc[i] = new Odleglosc();
		}
		System.out.println("Obiekt klasy siec utworzony");
		
		 OdczytZPliku o1 = new OdczytZPliku();
		 tab = o1.zapisDoTab();
		 a = tab.length;
		 System.out.println(a);
		 b = 2;
		 zwyciezcy = new int[a];


		// tworzymy neurony i inicjujemy losowe dane (w neuronach)
		n = new Neuron[wymiarSieci][wymiarSieci];
		for (int i = 0 ; i < wymiarSieci; i++)
		{
			for(int j = 0 ; j < wymiarSieci ; j++)
			{
				n[i][j] = new Neuron(b, tab[0]);
			}
			
		}

		
		// petla w której 1 iteracja to 1 epoka
		for (int i = 0 ; i < 100 ; i++)
		{
			
			
			// petla po wszystkich rekordach wejsciowych
			for (int j = 0 ; j < a ; j++)
			{	
				pobierzRekord(j);
				dzialaj(j);
				
				
			}
			//System.out.println("//*************************** \n\n");
			//n1.wypiszWagi();
		}

		
		

		zapiszOdpowiedz();
		
	}
	

	
//***********************************************
	void dzialaj(int a)		// arg to numer iteracji
	{
		double wsp = wspUczenia(a);
		int licznik = 0;
		for (int i = 0 ; i < wymiarSieci; i++)
		{
			for(int j = 0 ; j < wymiarSieci ; j++, licznik++)
			{
				odleglosc[licznik].kolumna = i;
				odleglosc[licznik].wiersz = j;
				odleglosc[licznik].wartosc = n[i][j].obliczOdleglosc();
			}
		}

		/*
		for(int i = 0 ; i < wymiarSieci*wymiarSieci ; i++)
		{
			System.out.println("Neuron " + odleglosc[i].kolumna + "," +odleglosc[i].wiersz + "  :"  + odleglosc[i].wartosc);
		}
		System.out.println("//*********************************");
		*/
		
		Sortowanie sortowanie = new Sortowanie(odleglosc);
		sortowanie.sortuj();
		
		/*for(int i = 0 ; i < wymiarSieci*wymiarSieci ; i++)
		{
			System.out.println("Neuron " + odleglosc[i].kolumna + "," +odleglosc[i].wiersz + "  :" +  odleglosc[i].wartosc);
		}
		System.out.println("//*********************************");
		*/
		n[odleglosc[0].kolumna][odleglosc[0].wiersz].korektaWag(wsp);
		zwyciezcy[a] = odleglosc[0].kolumna*wymiarSieci + odleglosc[0].wiersz;
		//System.out.println(zwyciezcy[a]);
		
		
	}
	
	
//***********************************************	
	double wspUczenia(int n)
	{
		int wsp = 0;
		
		if (n < 5)
		{
			return 0.6;
		}
		else if (n < 9)
		{
			return 0.6*0.5;
		}
		
		return 0.3*0.5;
		
	}

//***********************************************
	void pobierzRekord (int a)
	{
		// ustawiamy na wejscie kazdego neuronun ten sam rekord wejsciowy
		for (int i = 0 ; i < wymiarSieci; i++)
		{
			for(int j = 0 ; j < wymiarSieci ; j++)
			{
				n[i][j].ustawWejscia(tab[a]);
			}
		}
	}
	

	
//***********************************************
	void zapiszOdpowiedz()
	{
		
/*
		for (int i= 0 ; i < zwyciezcy.length ; i++)
		{
			System.out.println("Wygrywa: " + zwyciezcy[i]);
		}

		
		
		try {
			zapis = new PrintWriter("nazwa_pliku.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    		try {
			zapis = new PrintWriter("wynikSieci.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    		
	    	zapis.println("Argumenty x: ");
			for (int i = 0 ; i < a ; i++)
			{
				zapis.println(Double.toString(tab[i][0]));
			}
			zapis.println();
			zapis.println();
			zapis.println("Argumenty y: ");
			for (int i = 0 ; i < a ; i++)
			{
				zapis.println(Double.toString(tab[i][1]));
			}
			
			
			
			zapis.println();
			zapis.println();
			zapis.println("Wynik sieci y: ");
			for (int i = 0 ; i < a ; i++)
			{
				zapis.println(Integer.toString(zwyciezcy[i]));
			}
			
			zapis.println("Wagi neuronu 1: " +  n1.waga[0] + " " + n1.waga[1]);
			zapis.println("Wagi neuronu 2: " +  n2.waga[0] + " " + n2.waga[1]);
			zapis.println("Wagi neuronu 3: " +  n3.waga[0] + " " + n3.waga[1]);
	      zapis.close();
		*/
	}
	
	public void wypiszWagi()
	{
		System.out.println("//*************************  waga X  ***************************/n//*************************    ***************************/n");
		for (int i = 0 ; i < wymiarSieci; i++)
		{
			for(int j = 0 ; j < wymiarSieci ; j++)
			{
				System.out.println(n[i][j].waga[0]);
			}
			
		}
		
		
		System.out.println("//************************* waga Y   ***************************/n//*************************    ***************************/n");

		for (int i = 0 ; i < wymiarSieci; i++)
		{
			for(int j = 0 ; j < wymiarSieci ; j++)
			{
				System.out.println(n[i][j].waga[1]);
			}
			
		}
		
		
	}
	
	
	
	
	public void wypiszZwyciezcow()
	{
		for (int i = 0; i < zwyciezcy.length; i++) 
		{
			System.out.println(zwyciezcy[i]);
		}
	}
}


