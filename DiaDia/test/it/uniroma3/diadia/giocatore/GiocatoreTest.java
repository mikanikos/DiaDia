package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {

	private Giocatore giocatore;
	
	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
		this.giocatore.setCfu(10);
	}
	
	@Test
	public void testGetBorsa() {
		Borsa borsa = this.giocatore.getBorsa();
		assertSame(borsa, this.giocatore.getBorsa());
	}
	
	@Test
	public void testGetCfu() {
		assertEquals(10, this.giocatore.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(60);
		assertEquals(60, this.giocatore.getCfu());
	}

}
