package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.gioco.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;

public class ComandoInteragisciTest {

	private AbstractPersonaggio character;
	private Partita nuova;
	private AbstractComando comando;

	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.character = new FakePersonaggio("Fake", "Presentazione", null);
		this.nuova.setStanzaCorrente(this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("ovest")));
		this.comando = new ComandoInteragisci();
	}

	@Test
	public void testEsegui_PersonaggioInesistente() {
		assertEquals("Con chi dovrei interagire?...", this.comando.esegui(nuova));
	}
	
	@Test
	public void testEsegui_PersonaggioEsistente() {
		this.nuova.getStanzaCorrente().setPersonaggio(character);
		assertEquals("done", this.comando.esegui(nuova));
	}

}
