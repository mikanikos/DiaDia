package it.uniroma3.diadia.attrezzi;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class AttrezzoTest {
	
	private Attrezzo attrezzo;
	
	@Before
	public void setUp() {
		this.attrezzo = new Attrezzo("attrezzoSemplice",5);
	}

	@Test
	public void testGetNome() {
		assertEquals("attrezzoSemplice", this.attrezzo.getNome());
	}
	
	@Test
	public void testGetPeso() {
		assertEquals(5, this.attrezzo.getPeso());
	}
	
	@Test
	public void testToString() {
		assertEquals("attrezzoSemplice (5kg)", this.attrezzo.toString());
	}

}
