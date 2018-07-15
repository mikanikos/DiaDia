package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class CaneTest {

	private AbstractPersonaggio cane;
	private Partita nuova;
	private Attrezzo attrezzo;

	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.attrezzo = new Attrezzo("attrezzo", 1);
		this.cane = new Cane("cane", attrezzo);
		this.nuova.getStanzaCorrente().setPersonaggio(cane);
	}
	
	@Test
	public void testAgisci() {
		assertEquals("Grrr... WOF WOF WOF", this.cane.agisci(nuova));
		assertEquals(this.nuova.getGiocatore().getCfuIniziali()-1, this.nuova.getGiocatore().getCfu());
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoNullo() {
		assertEquals("Il cane non ha ricevuto nessun attrezzo", this.cane.riceviRegalo(null, nuova));
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoEsistenteNonPreferito() {
		Attrezzo preferito = new Attrezzo("osso", 2);
		assertEquals("Il cane ha gradito il regalo e forse ha lasciato qualcosa...", this.cane.riceviRegalo(preferito, nuova));
		assertSame(this.attrezzo, this.nuova.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()));
		assertSame(preferito, this.cane.getTenuto());
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoEsistentePreferito() {
		Attrezzo attrezzoInBorsa = new Attrezzo("altro", 1);
		assertEquals("Al cane non è piaciuto il regalo", this.cane.riceviRegalo(attrezzoInBorsa, nuova));
		assertEquals(attrezzoInBorsa, this.nuova.getStanzaCorrente().getAttrezzo(attrezzoInBorsa.getNome()));
	}

}
