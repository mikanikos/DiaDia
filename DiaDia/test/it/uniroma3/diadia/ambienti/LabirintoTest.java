package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza stanzaUscente;
	private Stanza stanzaEntrante;
	private Stanza nuovaStanza;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		this.stanzaUscente = new Stanza("nuovaStanzaUscente");
		this.stanzaEntrante = new Stanza("nuovaStanzaEntrante");
		this.labirinto.setStanzaIniziale(this.stanzaEntrante);
		this.labirinto.setStanzaVincente(this.stanzaUscente);
		this.nuovaStanza=new Stanza("stanzaDiTest");
	}
	
	@Test
	public void testGetStanzaEntrante() {
		assertSame(this.stanzaEntrante, this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testGetStanzaUscente() {
		assertSame(this.stanzaUscente, this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testSetStanzaEntrante(){
		this.labirinto.setStanzaIniziale(this.nuovaStanza);	
		assertSame(this.nuovaStanza, this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void testSetStanzaUscente(){
		this.labirinto.setStanzaVincente(this.nuovaStanza);
		assertSame(this.nuovaStanza,this.labirinto.getStanzaVincente());
	}
	
	@Test
	public void testAggiungiAttrezzo(){
		assertTrue(this.labirinto.aggiungiAttrezzo(new Attrezzo("attrezzoNuovo", 2), this.nuovaStanza));
		assertFalse(this.labirinto.aggiungiAttrezzo(new Attrezzo("attrezzoNuovo", 2), this.nuovaStanza));
		assertFalse(this.labirinto.aggiungiAttrezzo(new Attrezzo("attrezzoNuovo", 8), this.nuovaStanza));
		assertTrue(this.labirinto.aggiungiAttrezzo(new Attrezzo("attrezzoNuovoDiverso", 2), this.nuovaStanza));
	}
}
