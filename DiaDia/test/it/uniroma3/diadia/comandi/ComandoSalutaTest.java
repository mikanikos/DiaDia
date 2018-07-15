package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.gioco.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.FakePersonaggio;

public class ComandoSalutaTest {
	
	private AbstractPersonaggio character;
	private Partita nuova;
	private AbstractComando comando;

	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.character = new FakePersonaggio("Fake", "Presentazione", null);
		this.nuova.setStanzaCorrente(this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("ovest")));
		this.comando = new ComandoSaluta();
	}

	@Test
	public void testEsegui_PersonaggioInesistente() {
		assertEquals("Chi dovrei salutare?...", this.comando.esegui(nuova));
	}
	
	@Test
	public void testEsegui_PersonaggioEsistente() {
		this.nuova.getStanzaCorrente().setPersonaggio(character);
		assertEquals("Ciao, io sono Fake. Presentazione", this.comando.esegui(nuova));
	}

}
