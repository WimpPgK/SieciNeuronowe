/*******************************************************
 * 		void start(int numerRekorduUczacego) - oblicza odpowiedz sieci dla podanego w arg rekordu uczacego
 * 						Na poczatku ustawia wejscia do neuronow warstwy 0. Kazdy z nich moze miec tylko 1 wejscie
 * 						i kazdy po kolei dostaje kolejne elementy danego wektora uczacego. Nastêpnie w petli po kolei
 * 						kazdy neuron oblicza swoja odpowiedz zaczynajac od warstwy zerowej która zna swoje wejscia.
 * 						Po obliczeniu odpowiedzi warstwy zerowej zapisywane s¹ do tabeli wejsc dla warstwy 1.
 * 						Ca³oœc powtarza siê a¿ do ostatniej warstwy z 1 neuronem. Wypisuje on odpowiedz ca³ej sieci.
 * 
 * 		void inicjujTablice() - definiuje wszystkie potrzebne tablice danych (wejscia, wagi, wspBledu, wyniki) 
 * 								oraz wype³nia je zerami. Inicjuje losowe wagi dla ca³ej sieci. Pobiera z pliku dane uczace
 * 								i zapisuje z tablicy 2D "dane uczace" .
 * 
 */




import java.util.Random;

public class Siec 
{
	double [][] daneUczace; 	// przechowuje wszystkie rekordy uczace. [nr_rekordu][wartosci]

	public Neuron wejsciowy1;
	public Neuron wejsciowy2;
	public Neuron wejsciowy3;
	double krok;

	
	double wynik1;
	double wynik2;
	double wynik3;
	
	double MAPE;
	double pomMAPE;
	double bladSredniokwadratowy;
	
	Siec ()
	{
		wejsciowy1 = new Neuron(2);
		wejsciowy2 = new Neuron(2);
		wejsciowy3 = new Neuron(2);
		
		inicjujDane();
		
	}
	
	
	
	
	
	
	
	// ********************************************************************
	void inicjujDane()	// tworzy wszelkie tablice zadeklarowane u gory i inicjuje je zerami*    (* - wagi inicjuje liczbami pseudolosowymi) 
	{	
		
		//wyszukuje ile nertonow jest w warstwie z najwieksza liczba neuronow
		
		OdczytWejsc o1 = new OdczytWejsc(daneUczace);		// pobieramy dane uczace i zapisujemy do tab
		daneUczace = o1.zwroc();
		Random rand = new Random();
		wejsciowy1.waga[0] = rand.nextDouble();
		wejsciowy1.waga[1] = rand.nextDouble();
		wejsciowy2.waga[0] = rand.nextDouble();
		wejsciowy2.waga[1] = rand.nextDouble();
		wejsciowy3.waga[0] = rand.nextDouble();
		wejsciowy3.waga[1] = rand.nextDouble();
		
		

		
		krok = 0.1;
		
		
		
		MAPE = 0;
		pomMAPE = 0;
		bladSredniokwadratowy = 0;

		
//		wejsciowy.waga[0] = 0.1;
//		ukryty[0].waga[0] = 0.4;
//		ukryty[1].waga[0] = 0.3;
//		ukryty[2].waga[0] = 0.7;
//		wyjsciowy.waga[0] = 0.2;
//		wyjsciowy.waga[1] = 0.9;
//		wyjsciowy.waga[2] = 0.5;
//		
		
		

	}
	
	void dzialaj()
	{	
		int n = 990;
		bladSredniokwadratowy = 0;
		MAPE = 0;
		pomMAPE = 0;
		double sumaBledu = 0;
		krok = krok*0.9995;
		for(int i = 0 ; i < n ; i++)	// petla po danych uczacych
		{
			wejsciowy1.wejscie[0] = daneUczace[i][0];
			wejsciowy1.wejscie[1] = daneUczace[i][1];

			wejsciowy2.wejscie[0] = daneUczace[i][0];
			wejsciowy2.wejscie[1] = daneUczace[i][1];
			
			wejsciowy3.wejscie[0] = daneUczace[i][0];
			wejsciowy3.wejscie[1] = daneUczace[i][1];
			
			
			wynik1 = wejsciowy1.obliczOdpowiedz();
			wynik2 = wejsciowy2.obliczOdpowiedz();
			wynik3 = wejsciowy3.obliczOdpowiedz();
			
			
						
			//System.out.println(wynik1);
			//System.out.println(wynik2);
			System.out.println(wynik3);
			
			
			double wspZapominania = 0.1;
			

			wejsciowy1.korektaWag(wynik1, krok, wspZapominania);

			wejsciowy2.korektaWag(wynik2, krok, wspZapominania);

			wejsciowy3.korektaWag(wynik3, krok, wspZapominania);
			

			


		}
		
	}
	
	
}
	
	
	
	
	