package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoPosaTest {

	private Partita nuova;
	private AbstractComando comando;
	private String attrezzoBorsa;
	
	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.attrezzoBorsa = "osso";
		this.nuova.setStanzaCorrente(this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("est")));
		this.nuova.getGiocatore().getBorsa().addAttrezzo(new Attrezzo(this.attrezzoBorsa,1));
		this.comando = new ComandoPosa();
		
	}

	@Test
	public void testPreliminare() {
		assertEquals("Aula N11", this.nuova.getStanzaCorrente().getNome());
		assertTrue(this.nuova.getStanzaCorrente().isEmpty());
		assertTrue(this.nuova.getGiocatore().getBorsa().hasAttrezzo(this.attrezzoBorsa));
	}
	
	@Test
	public void testEsegui_BorsaVuota() {
		this.nuova.getGiocatore().getBorsa().removeAttrezzo(this.attrezzoBorsa);
		assertTrue(this.nuova.getGiocatore().getBorsa().isEmpty());
		this.comando.setParametro("prova");
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getStanzaCorrente().isEmpty());
	}
	
	@Test
	public void testEsegui_AttrezzoNullo() {
		this.comando.setParametro(null);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getStanzaCorrente().isEmpty());
	}
	
	@Test
	public void testEsegui_AttrezzoInesistente() {
		String nomeAttrezzo = "prova";
		assertFalse(this.nuova.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo));
		this.comando.setParametro(nomeAttrezzo);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getStanzaCorrente().isEmpty());
	}
	
	@Test
	public void testEsegui_StanzaNonPiena() {
		for(int i=0; i<10; i++)
			this.nuova.getStanzaCorrente().addAttrezzo(new Attrezzo("altro"+i, 1));
		this.comando.setParametro(this.attrezzoBorsa);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getStanzaCorrente().hasAttrezzo(this.attrezzoBorsa));
		assertFalse(this.nuova.getGiocatore().getBorsa().hasAttrezzo(this.attrezzoBorsa));
	}
	
	@Test
	public void testEsegui() {
		this.comando.setParametro(this.attrezzoBorsa);
		this.comando.esegui(this.nuova);
		assertTrue(this.nuova.getStanzaCorrente().hasAttrezzo(this.attrezzoBorsa));
		assertTrue(this.nuova.getGiocatore().getBorsa().isEmpty());
	}

}
