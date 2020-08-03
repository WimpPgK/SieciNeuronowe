
public class Warstwa 
{
	Neuron [] n;
	double [][] wejscia;  		// [numerNeuronu][x1,x2,...,xn]
	double [][] wagi;			// [numerNeuronu][w1,w2,...,wn]
	double [][] wspBledu;		// [numerWarstwy][numerNeuronu][wartoscWspolczynnikaBledu]
	double [][] wyniki;			// [numerWarstwy][numerNeuronu][wynik]
	int liczbaNeuronow;
	int liczbaWejsc;
	

	Warstwa(int liczbaNeuronow, int liczbaWejsc, double [][]wejscia, double [][]wagi, double[][] wspBledu, double[][]wyniki)		
	{
		this.liczbaNeuronow = liczbaNeuronow;
		this.liczbaWejsc = liczbaWejsc;
		this.wejscia = wejscia;
		this.wagi = wagi;
		this.wspBledu = wspBledu;
		this.wyniki = wyniki;
		
		
		n = new Neuron[liczbaNeuronow];
		for (int i = 0 ; i < liczbaNeuronow ; i++)
		{
			n[i] = new Neuron(liczbaWejsc, wejscia[i], wagi[i], wspBledu[i], wyniki[i]);
			//System.out.println("Tworze neuron");
		}
	}

	
	void obliczOdpowiedz()
	{
		for (int i = 0 ; i < liczbaNeuronow ; i++)
		{
				n[i].obliczOdpowiedz();
		}
	}
	
	
	
	
	
	void obliczWspBledu(double [][] wagiWsteczne, double [][] wspBleduWsteczny, int liczbaWejscWstecznych,int liczbaNeuronow, double [][] wspBledu)
	{	
		
		double [][] wagiWsteczneUlozone = new double[100][100];
		for (int i = 0 ; i < liczbaNeuronow ; i++)
		{
			for (int j = 0 ; j < liczbaWejscWstecznych ; j++)
			{
				
				wagiWsteczneUlozone [i][j] = wagiWsteczne[j][i];
			}
		}
		
		double [] pomWspolczynniki = new double[100]; 
		
		for (int i = 0;  i < liczbaWejscWstecznych ; i++)
		{
		//	pomWspolczynniki[i] =  wspBleduWsteczny[i]
		}
		
		for (int i = 0 ; i < liczbaNeuronow ; i++)
		{
			n[i].obliczWspBledu(wagiWsteczneUlozone[i], wspBleduWsteczny, liczbaWejscWstecznych,liczbaNeuronow, wspBledu[i]);
		}
	}
	
	
	
	
	
	void zmienWagi (double krok)
	{
		for (int i = 0 ; i < liczbaNeuronow ; i++)
		{		
				n[i].zmienWagi(krok);
		}
	}
}
