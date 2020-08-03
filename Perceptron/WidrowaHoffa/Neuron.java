import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron 
{
	public List<Double> wejscie; // lista wejœæ
	public List<Double> waga;	// lista wag
	private int liczbaWejsc;
	private double Pm;
	Random rand;
	
	

	public Neuron (int n)
	{	
		liczbaWejsc = n;
		Pm = 0;
		rand = new Random();
		wejscie = new ArrayList<>(liczbaWejsc);
		waga = new ArrayList<>(liczbaWejsc);
		
		
		
		//********* Ustawiamy losowe wagi i wejscia = 0 ************
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			wejscie.add(i, 0.0);
			waga.add(i, rand.nextDouble());
			//System.out.println("W" + i + " = " + waga.get(i));
		}
	}
	
	
	
	
	//********************************************************************
	public void ustawWejscia (String tab[])
	{
		double pom;
		for (int i = 0 ; i < liczbaWejsc ; i++ )
		{
			pom = Double.valueOf(tab[i]);
			wejscie.set(i, pom);
			
			//System.out.println("X" + i +" = " + wejscie.get(i));

		}
	}
	
	//********************************************************************
	public void zmienWagi (int tab[])
	{
		for (int i = 0 ; i < liczbaWejsc ; i++ )
		{
			//waga.add(i, tab[i]);
		}
	}
	
	//********************************************************************
	public double blokSumujacy()
	{
		Pm = 0;
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			Pm += (wejscie.get(i) * waga.get(i));
		
		}
		//System.out.println("PotencjalMembranowy =  " + Pm);
		return Pm;
	}
	
	
	//********************************************************************
	//unipolarna funkcja aktywacji
	public double funckaAktywacji(double Pm)		// prog aktywacji jako arg
	{
		double pom;
		
		pom = 1/(1+Math.pow(2.718281828459, -2*Pm));
		return pom;
		
	}
}	
