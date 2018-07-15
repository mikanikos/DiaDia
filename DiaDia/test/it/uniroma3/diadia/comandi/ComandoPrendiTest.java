package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoPrendiTest {
	
	private Partita nuova;
	private AbstractComando comando;
	private String attrezzoStanza;
	
	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.comando = new ComandoPrendi();
		this.attrezzoStanza = "osso";
	}
	
	@Test
	public void testPreliminare() {
		assertEquals("Atrio", this.nuova.getStanzaCorrente().getNome());
		assertTrue(this.nuova.getGiocatore().getBorsa().isEmpty());
		assertTrue(this.nuova.getStanzaCorrente().hasAttrezzo(this.attrezzoStanza));
	}
	
	@Test
	public void testEsegui_StanzaVuota() {
		this.nuova.setStanzaCorrente(this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("est")));
		assertTrue(this.nuova.getStanzaCorrente().isEmpty());
		this.comando.setParametro("prova");
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testEsegui_AttrezzoNullo() {
		this.comando.setParametro(null);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testEsegui_AttrezzoInesistente() {
		String nomeAttrezzo = "prova";
		assertFalse(this.nuova.getStanzaCorrente().hasAttrezzo(nomeAttrezzo));
		this.comando.setParametro(nomeAttrezzo);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	public void testEsegui_BorsaNonPiena() {
		
		this.nuova.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("altro", 1));
		this.comando.setParametro(this.attrezzoStanza);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getGiocatore().getBorsa().hasAttrezzo(this.attrezzoStanza));
		assertFalse(this.nuova.getStanzaCorrente().hasAttrezzo(this.attrezzoStanza));
	}
	
	@Test
	public void testEsegui_BorsaPesante() {
		for(int i=0; i<this.nuova.getGiocatore().getBorsa().getPesoMax(); i++)
			this.nuova.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("altro"+i, 1));
		assertTrue(this.nuova.getGiocatore().getBorsa().getPeso() == this.nuova.getGiocatore().getBorsa().getPesoMax());
		this.comando.setParametro(this.attrezzoStanza);
		this.comando.esegui(this.nuova);
		assertFalse(this.nuova.getGiocatore().getBorsa().hasAttrezzo(this.attrezzoStanza));
		assertTrue(this.nuova.getStanzaCorrente().hasAttrezzo(this.attrezzoStanza));
	}
	
	@Test
	public void testEsegui() {
		this.comando.setParametro(this.attrezzoStanza);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getGiocatore().getBorsa().hasAttrezzo(this.attrezzoStanza));
		assertTrue(this.nuova.getStanzaCorrente().isEmpty());
	}

}
