package it.uniroma3.diadia.gestioneResources;

import static org.junit.Assert.*;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto clab;;
	private String stanze;

	@Before
	public void setUp() throws Exception {
		this.stanze = "Stanze: Stanza1, Stanza3\nStanze speciali: bloccata Stanza2 attrezzoSbloccante, magica Stanza4 2\n"
				+ "Inizio: Stanza1\nVincente: Stanza2\nPersonaggi: mago Personaggio1 Stanza1, cane Personaggio2 Stanza2\n"
				+ "Attrezzi: attrezzo1 1 Stanza1, attrezzo2 1 Personaggio1\n"
				+ "Uscite: Stanza1 nord Stanza2, Stanza2 est bloccata";
		this.clab = new CaricatoreLabirinto(new StringReader(stanze));
		this.clab.carica();
	}
	
	@Test
	public void testCreaStanze() {
		assertEquals("Stanza1", this.clab.getNome2stanza().get("Stanza1").getNome());
		assertEquals("Stanza2", this.clab.getNome2stanza().get("Stanza2").getNome());
		assertEquals("Stanza3", this.clab.getNome2stanza().get("Stanza3").getNome());
		assertEquals("Stanza4", this.clab.getNome2stanza().get("Stanza4").getNome());
	}
	
	@Test
	public void testLeggiStanzeInizialeVincente() {
		assertEquals("Stanza1", this.clab.getStanzaIniziale().getNome());
		assertEquals("Stanza2", this.clab.getStanzaVincente().getNome());
	}
	
	@Test
	public void testCreaAttrezzi() {
		assertEquals("attrezzo2", this.clab.getNome2personaggio().get("Personaggio1").getTenuto().getNome());
		assertEquals("attrezzo1", this.clab.getNome2stanza().get("Stanza1").getAttrezzo("attrezzo1").getNome());
	}
	
	@Test
	public void testCreaPersonaggi() {
		assertEquals("Personaggio1", this.clab.getNome2personaggio().get("Personaggio1").getNome());
		assertEquals("Personaggio1", this.clab.getNome2stanza().get("Stanza1").getPersonaggio().getNome());
		assertEquals("Personaggio2", this.clab.getNome2personaggio().get("Personaggio2").getNome());
		assertEquals("Personaggio2", this.clab.getNome2stanza().get("Stanza2").getPersonaggio().getNome());
	}
	
	@Test
	public void testCreaUscite() {
		assertEquals("Stanza2", this.clab.getNome2stanza().get("Stanza1").getStanzaAdiacente(Direzione.valueOf("nord")).getNome());
		assertEquals("Stanza1", this.clab.getNome2stanza().get("Stanza2").getStanzaAdiacente(Direzione.valueOf("sud")).getNome());
		assertEquals("Stanza2", this.clab.getNome2stanza().get("Stanza2").getStanzaAdiacente(Direzione.valueOf("est")).getNome());
	}

}