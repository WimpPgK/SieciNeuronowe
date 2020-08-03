	import java.io.FileNotFoundException;
	import java.io.PrintWriter;
public class Zapis
{
		PrintWriter zapis;
	 
	  	public Zapis(String nazwa) 
	  	{
	      
	      try {
	    	  zapis = new PrintWriter(nazwa);
	      } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	      zapis.println("Ala ma kota, a kot ma Alê");
	      
	  	}
	  	
	  	void zapiszTo(String napis)
	  	{
	  		zapis.println(napis);
	  	}
	  	
	  	void koniecZapisu()
	  	{
	  		zapis.close();
	  	}
}

