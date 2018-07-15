package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class AbstractPersonaggioTest {

	private AbstractPersonaggio fake;
	
	@Before
	public void setUp() throws Exception {
		this.fake = new FakePersonaggio("Fake", "Presentazione", null);
	}

	@Test
	public void testGetNomeEToString() {
		assertEquals("Fake", this.fake.getNome());
		assertEquals("Fake", this.fake.toString());
	}
	
	@Test
	public void testGetSetTenuto() {
		assertNull(this.fake.getTenuto());
		this.fake.setTenuto(new Attrezzo("attrezzo", 1));
		assertNotNull(this.fake.getTenuto());
	}
	
	@Test
	public void testSalutaEHaSalutato() {
		assertFalse(this.fake.haSalutato());
		assertEquals("Ciao, io sono Fake. Presentazione", this.fake.saluta());
		assertTrue(this.fake.haSalutato());
		assertEquals("Ciao, io sono Fake. Ci siamo gia' presentati!", this.fake.saluta());
		assertTrue(this.fake.haSalutato());
	}

}
