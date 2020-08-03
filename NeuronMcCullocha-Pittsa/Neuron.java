import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron 
{
	private List<Integer> wejscie; // lista wejœæ
	private List<Integer> waga;	// lista wag
	private int liczbaWejsc;
	private int Pm;
	Random rand;
	
	

	public Neuron (int n)
	{	
		liczbaWejsc = n;
		Pm = 0;
		rand = new Random();
		wejscie = new ArrayList<>(liczbaWejsc);
		waga = new ArrayList<>(liczbaWejsc);
		
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			wejscie.add(i, 0);
			waga.add(i, 0);
		}
	}
	
	
	
	
	//********************************************************************
	void ustawWejscia (int tab[])
	{
		for (int i = 0 ; i < liczbaWejsc ; i++ )
		{
			wejscie.add(i, tab[i]);
		}
	}
	
	//********************************************************************
	void ustawWagi (int tab[])
	{
		for (int i = 0 ; i < liczbaWejsc ; i++ )
		{
			waga.add(i, tab[i]);
		}
	}
	
	//********************************************************************
	void blokSumujacy()
	{
		for (int i = 0 ; i < liczbaWejsc ; i++)
		{
			Pm += wejscie.get(i)*waga.get(i);
		}
	}
	
	
	//********************************************************************
	//unipolarna funkcja aktywacji
	int funckaAktywacji(int t)		// prog aktywacji jako arg
	{
		if (Pm >= t)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}	
