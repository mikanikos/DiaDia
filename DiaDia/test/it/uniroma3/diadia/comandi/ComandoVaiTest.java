package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.gioco.Partita;

public class ComandoVaiTest {
	
	private Partita nuova;
	private AbstractComando comando;
	
	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.comando = new ComandoVai();
		this.nuova.creaLabirintoStandard();
	}

	@Test
	public void testEsegui_DirezioneNulla() {
		assertEquals("Atrio", this.nuova.getStanzaCorrente().getNome());
		this.comando.setParametro(null);
		this.comando.esegui(this.nuova);
		assertEquals("Atrio", this.nuova.getStanzaCorrente().getNome());
		assertEquals(this.nuova.getGiocatore().getCfuIniziali(), this.nuova.getGiocatore().getCfu());
	}
	
	@Test
	public void testEsegui_DirezioneInesistente() {
		assertEquals("Atrio", this.nuova.getStanzaCorrente().getNome());
		this.comando.setParametro("sud-ovest");
		this.comando.esegui(this.nuova);
		assertEquals("Atrio", this.nuova.getStanzaCorrente().getNome());
		assertEquals(this.nuova.getGiocatore().getCfuIniziali(), this.nuova.getGiocatore().getCfu());
	}
	
	@Test
	public void testEsegui() {
		assertEquals("Atrio", this.nuova.getStanzaCorrente().getNome());
		this.comando.setParametro("nord");
		this.comando.esegui(this.nuova);
		assertEquals("Biblioteca", this.nuova.getStanzaCorrente().getNome());
		assertEquals(this.nuova.getGiocatore().getCfuIniziali()-1, this.nuova.getGiocatore().getCfu());
	}

}
