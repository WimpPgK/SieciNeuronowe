
public class Sortowanie 
{
	
	public Odleglosc[] odleglosc;
	
	public Sortowanie (Odleglosc [] odleglosc)
	{
	
		this.odleglosc = odleglosc;
	}
	
	
	public void sortuj()
	{
		Odleglosc pom = new Odleglosc();
		for (int i = 0; i < odleglosc.length; i++) 
		{
			for (int j = i + 1; j < odleglosc.length; j++) 
			{
				if (odleglosc[i].wartosc > odleglosc[j].wartosc)
				{
					pom = odleglosc[i];
					odleglosc[i] = odleglosc[j];
					odleglosc[j] = pom;
				}
			}
		}
	}
}
