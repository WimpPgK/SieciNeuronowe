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
	double [][][] wejscia;  	// [numerWarstwy][numerNeuronu][x1,x2,...,xn]
	double [][][] wagi;			// [numerWarstwy][numerNeuronu][w1,w2,...,wn]
	double [][][] wspBledu;		// [numerWarstwy][numerNeuronu][wartoscWspolczynnikaBledu, zawsze jest 1 ale musi byc tab zeby przekazac przez wsk] 
	double [][][] wyniki;		// [numerWarstwy][numerNeuronu][wynik]
	int liczbaWarstw;
	int [] topologiaSieci;					// przechowuje liczby neuronow w kolejnych wasrstwach. top..[0] - liczba neuronow w warstie 0 itd...
	Warstwa []w;
	double []SEM;
	
	Siec (int [] topologiaSieci, double [] SEM)
	{
		this.SEM = SEM;
		this.topologiaSieci = topologiaSieci;
		liczbaWarstw = topologiaSieci.length;
		//System.out.println(liczbaWarstw);
		w = new Warstwa[topologiaSieci.length];
		
		inicjujTablice();
		
		for (int i = 0 ; i < liczbaWarstw ; i++)
		{
			if (i == 0)
			{
				w[i] = new Warstwa (topologiaSieci[i], 1 , wejscia[i], wagi[i], wspBledu[i], wyniki[i]);	// liczba neuronow w warstwie, liczba wejsc kazdego neuronu, ...
			}
			else
			{
				w[i] = new Warstwa (topologiaSieci[i], topologiaSieci[i-1] , wejscia[i], wagi[i], wspBledu[i], wyniki[i]); // liczba neuronow w warstwie, liczba wejsc kazdego neuronu, ...
			}
			
		}
	}
	
	
	
	
	
	
	// ********************************************************************
	void inicjujTablice()	// tworzy wszelkie tablice zadeklarowane u gory i inicjuje je zerami*    (* - wagi inicjuje liczbami pseudolosowymi) 
	{	
		
		//wyszukuje ile nertonow jest w warstwie z najwieksza liczba neuronow
		int maxNeuronow = topologiaSieci[0];
		for (int i = 1 ; i < topologiaSieci.length ; i++)
		{
			if (maxNeuronow < topologiaSieci[i]) 	{maxNeuronow = topologiaSieci[i];}
		}
		
		OdczytWejsc o1 = new OdczytWejsc(daneUczace);		// pobieramy dane uczace i zapisujemy do tab
		daneUczace = o1.zwroc();
		
		wejscia = new double [topologiaSieci.length][maxNeuronow][maxNeuronow];	// [liczba warstw][ w ktorych max jest tyle neuronow][i max maja tyle wejsc ile jest max neuronow]
										// trzeba by to zrobic na listach a nie tablicach
										// wtedy tworzylibysmy tyle ile trzeba a nie max mozliwy wariant
		wagi = new double [topologiaSieci.length][maxNeuronow][maxNeuronow];
		wspBledu = new double [topologiaSieci.length][maxNeuronow][maxNeuronow];
		wyniki = new double [topologiaSieci.length][maxNeuronow][maxNeuronow];
		
		Random rand = new Random();
		
		for (int i = 0 ; i < topologiaSieci.length ; i++)	// petla po warstwach
		{
			for (int j = 0 ; j < maxNeuronow ; j++)			// petla po neuronach w warstwach
			{
				for (int k = 0 ; k < maxNeuronow ; k++)				// petla po wejsciach neuronow
				{
					wejscia[i][j][k] = 0;
					wagi[i][j][k] = rand.nextDouble();		// losowe nadawanie wag polaczen
					wspBledu[i][j][k] = 0;
					wyniki[i][j][k] = 0;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	// ********************************************************************
	/* Na poczatku mamy tablice wartosci wejsciowych wypelniana zerami
	 * funkcja start wypelnia wartosci wejsciowe z tablicy uczacej dla zerowej warstwy neuronow
	 * Rozpoczyna sie petla po wszyskich warstwach, w ktorej warstwa zerowa obliczy odpowiedz, a gdy to zrobi
	 * to automatycznie neurony uzupelnia tablice wejsc dla warstwy 1
	 * warstwa 1 obliczy odpowiedz i zapisze wejscia dla warsty 2 itd...
	 */
	
	void start(int numerRekorduUczacego)		//
	{	
		// nadaje wartosci wejsciowe dla neuronow w zerowej warstwie sieci
		for (int i = 0 ; i < topologiaSieci[0]; i++)
		{
			wejscia[0][i][0] = daneUczace[numerRekorduUczacego][i];		// wejscia - [0 warstwa][ity neuron][0 wejscie (bo kazdy neuron w tej warstwie ma 1 wejscie]
		}																
		
		
		
		
								//********************************************
		// oblicza odpowiedz kazdego neuronu i jego wynik zapisuje jako wejscie do neuronow warstwy powyzej
		for (int i = 0 ; i < liczbaWarstw - 1 ; i++)
		{
				// TODO teraz uczymy siec tylko na 1 rekordzie uczacym
				w[i].obliczOdpowiedz();
				
				for (int j = 0 ; j < topologiaSieci[i+1] ; j++)
				{
					for( int k = 0 ; k < topologiaSieci[i] ; k++)
					{
						wejscia[i+1][j][k] = wyniki[i][k][0];
					}
				}
		}
		w[liczbaWarstw-1].obliczOdpowiedz();	// odpowiedz dla ostatniego neuronu w cales sieci
		
		
		// obliczamy wspolczynnik blêdu dla ostatniego neuronu, czyli dla calej sieci
		
		
		
		
		wspBledu[liczbaWarstw-1][0][0] =  daneUczace[numerRekorduUczacego][topologiaSieci[0]] - wyniki[liczbaWarstw-1][0][0];
		SEM[0] += wspBledu[liczbaWarstw-1][0][0]*wspBledu[liczbaWarstw-1][0][0];
		wspBledu[liczbaWarstw-1][0][0] =  pochodnaOstatniego() * (daneUczace[numerRekorduUczacego][topologiaSieci[0]] - wyniki[liczbaWarstw-1][0][0]);
				
		
		
		
		
		//**************************    WSTECZNA PROPAGACJA   ****************************************
		
		// oblicza wspolczynnik bledu dla kazdego neuronu
		for (int i = liczbaWarstw - 2 ; i >= 0 ; i--)
		{
			
			w[i].obliczWspBledu(wagi[i+1],  wspBledu[i+1],  topologiaSieci[i+1], topologiaSieci[i], wspBledu[i]);

		}
		
		//aktualizuje wagi dla kazdego polaczenia
		double krok = 0.000001;
		for (int i = 0 ; i < liczbaWarstw ; i++)
		{
			w[i].zmienWagi(krok);
		}
		
	}
	
	public double pochodnaOstatniego()
	{
		
		return w[liczbaWarstw - 1].n[0].pochodnaFAktywacjiDlaTegoNeuronu();
	}
	
	
}
