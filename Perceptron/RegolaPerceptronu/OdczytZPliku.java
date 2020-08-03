import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OdczytZPliku 
{
	private FileReader odczyt;
	private String lokalizajcaPliku = "plik.txt";
	private BufferedReader buforowanyOdczyt;
	private File plik;
	private String linia;
	private String [] nowyRekord;
	
	public OdczytZPliku()
	{
		
	}
	
	
	public void otworzPlik()
	{
		File plik = new File (lokalizajcaPliku);
		try
		{
			odczyt = new FileReader(plik);
			buforowanyOdczyt = new BufferedReader(odczyt);
			linia = "";

			
			
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public String[] zwrocLinie()
	{
		nowyRekord = null;
		try
		{
			if(  (linia = buforowanyOdczyt.readLine())   != null) // odczyt linia po linii
			{
				String [] nowyRekord = linia.split(" ");
				return nowyRekord;
				
			}
			else
			{
				String[] nowyRekord = {"Koniec"};
				zamknijPlik();
				return nowyRekord;
				
				
			}
		}			
		catch(IOException e){String[] nowyRekord = {"Koniec"}; return nowyRekord;}
	}
	
	
	
	private void zamknijPlik()
	{
		//*************************************************************
		try 
		{
			odczyt.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//***************************************************************
	}

}
