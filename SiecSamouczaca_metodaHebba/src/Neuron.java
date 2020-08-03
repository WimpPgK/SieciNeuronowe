
public class Neuron 
{

	public double [] wejscie;
	public double [] waga;
	int n;					// liczba wejsc
	double pm;
	public Neuron(int n)
	{
		wejscie = new double[n];
		waga = new double[n];
		this.n = n;
	}
	

	//*************************************************************
	
	public double obliczOdpowiedz()
	{
		potecialMembranowy();

		double odpowiedz = funkcjaAktywacji();
		return odpowiedz;

	}
	

	//************************************************************
	private double funkcjaAktywacji()		
	{
		
		
		return (1/(1+Math.pow(2.718281828459, -10*pm)));
	}
	
	
	//*************************************************************
	private void potecialMembranowy() // suma wi*xi
	{
		pm = 0;
		for (int i = 0 ; i < n ; i++)
		{
			pm += waga[i]*wejscie[i];
			
		}
	}

	
	//*************************************************************
	public void korektaWag(double wynik, double krok, double wspZapominania) 
	{
		for (int i = 0 ; i < n ; i++)
		{

			waga[i]= waga[i]*(1-wspZapominania);
			waga[i] += krok * wynik * wejscie[i];
			
		}
		
	}
	
	
	
	
	//*************************************************************
	public double pochodnaFAktywacjiDlaTegoNeuronu()
	{	
		potecialMembranowy();
		
		double B = 20;
		double fx = 1/(1+Math.pow(2.718281828459, -B*pm));
		fx = B*fx*(1-fx);
				
		return  fx;

	}




}
