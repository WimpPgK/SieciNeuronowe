import java.util.Random;

/**************************    PgK    ********************************
 * 		
 * 			void potecialMembranowy()
 * 			void funkcjaAktywacji()
 *			void wspBledu()
 *			void obliczDeltaIAktualizuj()
 *
 */


public class Neuron 
{	
	private double [] wejscia;
	private double [] wagi;
	private int liczbaWejsc;
	private double [] wspBledu;			// To nie musialaby byc tab bo mamy dla kazdego neuronu 1 wspolczynnik, ale zeby przekazac przez wskaznik to tak jest
	public double pm;					// potecial membranowy (w1*x1 + w2*x2 + ... + wn*xn)
	double wynikNeuronu;
	double [] wyniki; 					// NIE musi byc tablica, ale jest zeby byl przekazany jako wskaznik
	
	
	Neuron(int liczbaWejsc, double [] wejscia, double[] wagi, double[] wspBledu, double wyniki[])
	{	
		this.liczbaWejsc = liczbaWejsc;
		this.wejscia = wejscia;
		this.wagi = wagi;
		this.liczbaWejsc = liczbaWejsc;
		this.wspBledu = wspBledu;
		this.wyniki = wyniki;
			
	}
	
	
	//********************************************************
	private void potecialMembranowy() // suma wi*xi
	{
		pm = 0;
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			pm += wagi[i]*wejscia[i];
			
		}
	}
	
	

	//********************************************************
	private double funkcjaAktywacji()		// dla x > 0 y = 1, dla x < 0 y = 0
	{
		return (1/(1+Math.pow(2.718281828459, -2*pm)));
	}
	
	


	
	//********************************************************
	public void obliczOdpowiedz()
	{
		potecialMembranowy();
		double odpowiedz = funkcjaAktywacji();
		wyniki[0] = odpowiedz;
	
	}
	
	
	
	//********************************************************
	public void obliczWspBledu(double []wagiWsteczneUlozone,double[][] wspBleduWsteczny,int liczbaWejscWstecznych,int liczbaNeuronow,double []wspBledu)
	{
		wspBledu[0] = 0;
		for (int i = 0 ; i < liczbaWejscWstecznych ; i++)
		{	
			
			wspBledu[0] += wagiWsteczneUlozone[i] * wspBleduWsteczny[i][0];
			
		}
		wspBledu[0] = wspBledu[0]*pochodnaFAktywacjiDlaTegoNeuronu();
		
	}
	
	//********************************************************
	public double pochodnaFAktywacjiDlaTegoNeuronu()
	{	
		potecialMembranowy();
		
		double B = 2;
		double fx = 1/(1+Math.pow(2.718281828459, -B*pm));
		
				
		return  B*fx*(1-fx);
	}
	
	
	
	//********************************************************
	public void zmienWagi(double krok)
	{
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			wagi[i] += krok * wspBledu[0];
		}
	}
}
