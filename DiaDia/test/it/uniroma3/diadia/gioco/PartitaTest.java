package it.uniroma3.diadia.gioco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.gioco.Partita;

public class PartitaTest {

	private Partita partita;
	private Stanza stanza;
	
	@Before
	public void setUp() {
		this.partita = new Partita();
		this.stanza = new Stanza("stanzaDiTest");
		this.partita.setStanzaCorrente(this.stanza);
	}
	
	@Test
	public void testGetStanzaCorrente() {
		assertSame(this.stanza, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		Stanza nuovaStanza = new Stanza("stanzaNuova");
		this.partita.setStanzaCorrente(nuovaStanza);
		assertSame(nuovaStanza, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertSame(this.partita.getLabirinto().getStanzaVincente(), this.partita.getStanzaVincente());
	}
	
	@Test
	public void testVinta_Verificato() {
	    this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		this.partita.setLivello(this.partita.getNumeroLivelli());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testVinta_NonVerificato() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testPersa_Verificato() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.persa());
	}
	
	@Test
	public void testPersa_NonVerificato() {
		assertFalse(this.partita.persa());
	}
	
	@Test
	public void testIsFinita_CausaSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_CausaSconfitta() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_CausaVittoria() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertFalse(this.partita.isFinita());
		this.partita.setLivello(this.partita.getNumeroLivelli());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_NonVerificato() {
		assertFalse(this.partita.isFinita());
	}

}
