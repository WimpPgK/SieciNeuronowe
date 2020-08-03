/**********************    PgK    ********************************
 * 
 * 	Klasa pobiera wszystkie rekordy uczace i zapisuje je do tab ktora
 * 	zwraca w konstruktorze.
 *  W pierwszym wierszu pliku ucz¹cego mamy liczbe wierszy pliku uczacego i liczbe rekordow uczacych
 *  ktore maja wziac udzial w nauce. 
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class OdczytWejsc 
{
	
	private FileReader odczyt;
	private String lokalizajcaPliku = "plik.txt";
	private BufferedReader buforowanyOdczyt;
	private File plik;
	private String linia;
	private String [][] calyPlik;
	private double [][]tab;
	int wiersze;
	int kolumny;
	

	
	public OdczytWejsc(double [][]dane)
	{	
		dane = tab;
		
		otworzPlik();
		odczyt();
		zapisDoTab();
		zamknijPlik();
		zwroc();
	}
	
	double [][] zwroc()
	{
		return tab;
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
	
	public double[][] zapisDoTab()
	{
		tab = new double [wiersze][kolumny];
		for (int i = 0 ; i < wiersze ; i++)
		{
			for (int j = 0 ; j < kolumny ; j++)
			{
				tab[i][j] = Double.valueOf(calyPlik[i][j]);
				//System.out.print(calyPlik[i][j] + "  ");
			}
			//System.out.print("\n");
		}
		return tab;
	}
	
	public void odczyt()
	{

		
		try
		{	
			String linia = buforowanyOdczyt.readLine();
			String [] pom = linia.split(" ");
			kolumny = Integer.valueOf(pom[0]);
			wiersze = Integer.valueOf(pom[1]);
			calyPlik = new String[wiersze][];
			
			
			for (int i = 0; i < wiersze ; i++) // odczyt linia po linii
			{
				
				linia = buforowanyOdczyt.readLine();
				calyPlik[i] = linia.split(" ");


			}
			
				zamknijPlik();

		}			
		catch(IOException e){String[] nowyRekord = {"Koniec"};}
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

