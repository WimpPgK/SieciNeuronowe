import java.util.Scanner;

public class Main 
{
	
	public static void main(String[] args)
	{
		ObslogaNeuron n1 = new ObslogaNeuron();
		n1.uczenie(1000, 0.00001);		// liczba epok i krok nauki
		n1.z1.koniecZapisu();
		n1.z2.koniecZapisu();
		n1.testNeuronu();
		
	}


}
