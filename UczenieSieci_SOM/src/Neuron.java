import java.util.Random;

public class Neuron
{
	
	private int liczbaWejsc;
	private double []wejscie;
	public double[] waga;
	private double []wyjscie;
	
	
	Neuron(int liczbaWejsc, double[] x)
	{
		System.out.println("Neuron utworzony");
		this.liczbaWejsc = liczbaWejsc;
		wejscie = new double[liczbaWejsc];
		waga = new double[liczbaWejsc];
		wyjscie = new double[liczbaWejsc];
		Random rand = new Random();
		
		
		
		
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			wejscie[i] = x[i];
			waga[i] = rand.nextDouble();
	
		}
		
		
	}
	
	
	
//******************************* odleglosc  *************************************
	
	public double obliczOdleglosc() // Liczy odleglosc miedzy wektorem wejsciowym a wagami neuronu (odleglosc euklidesowa)
	{
		double d_kwadrat = 0; // odleglosc do ^2
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			d_kwadrat += Math.pow((wejscie[i] - waga[i]), 2.0);
			
		}
		 // System.out.println(d_kwadrat);
		return d_kwadrat;
	}
	

	
	
//******************************* korekta wag  *************************************
	
	public void korektaWag(double wsp)
	{
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			waga[i] = waga[i] + wsp*(wejscie[i] - waga[i]);
		}
	}
	
	
	
//*************************** ustawia nowy rekord wejsciowy  **********************
	
	public void ustawWejscia(double w[])
	{
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			wejscie[i] = w[i];
		}
	}
	
	
	
//*******************   wypisuje wagi  ********************************
	
	public void wypiszWagi()
	{
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			System.out.println(waga[i]);
			
		}
		System.out.println();
	}

}
