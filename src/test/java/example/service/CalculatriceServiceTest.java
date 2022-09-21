/**
 * 
 */
package example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author orsys
 *
 */
class CalculatriceServiceTest {

public CalculatriceService	calculatriceservice=new CalculatriceService();
	
	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	//@BeforeAll
	void setUpBeforeClass()  {
	
	
	
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	//@AfterAll
	void tearDownAfterClass()  {
	}

	@org.junit.jupiter.api.Test
	void testSoustraction() {
	
	//etape 1 initialisation
		int a=27;
		int b=8;	
			
	//etape 2 appel du service à tester et stockage du résultat	
		int resultat=calculatriceservice.soustraction(a,b);
		 
	//etape 3 verification
    //assertEquals((expected, actual)
		assertEquals(19,resultat);	
		assertTrue(resultat>0);
		
		
		
	}

	@org.junit.jupiter.api.Test
	void testSomme() {
	
	//etape 1 initialisation
     int a=27;
	 int b=8;	
		
	//etape 2 appel du service à tester et stockage du résultat	
	 int resultat=calculatriceservice.somme(a,b);
	 
	 //etape 3 verification
	 //assertEquals((expected, actual)
	 assertEquals(35,resultat);
	 
	}
	
	@Test
	void testTemplate() {
	
	//etape 1 initialisation
     int a=27;
	 int b=8;	
		
	//etape 2 appel du service à tester et stockage du résultat	
	 int resultat=calculatriceservice.somme(a,b);
	 
	 //etape 3 verification
	 //assertEquals((expected, actual)
	 assertEquals(35,resultat);
	 
	}
	
	
	
}
