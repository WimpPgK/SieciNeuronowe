
public class Main 
{
	
	public static void main(String[] args)
	{
		Main program = new Main();
	}
	
	
	Main()
	{	
	//***************** Do uzupelnienia przez ubslugujacego **************************
		
		int tabWejscia[] = {1,1};	
		
		
		// AND
		int tabWagi[] = {1,1};	// w tym modelu wagi aktywacyjne musza byc sta³e
		int progAktywacji = 2;
		
		
	//*******************************  KONIEC  ****************************************
		
		
		
		int liczbaWejsc = tabWejscia.length;
		
		
		
		
		// obsluga neuronu
		Neuron n1 = new Neuron(liczbaWejsc);	// ile neuron ma miec wejsc		
		n1.ustawWejscia(tabWejscia);
		n1.ustawWagi(tabWagi);
		n1.blokSumujacy();
		int wynik = n1.funckaAktywacji(progAktywacji);
		
		System.out.println("Na wyjsciu dostalismy: " + wynik);
	}
}
