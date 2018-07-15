package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class StregaTest {

	private AbstractPersonaggio strega;
	private Partita nuova;
	private Stanza iniziale;

	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.strega = new Strega("cane", null);
		this.nuova.getStanzaCorrente().setPersonaggio(strega);
		this.iniziale = this.nuova.getStanzaCorrente();
	}

	@Test
	public void testAgisci_NonHaSalutato() {
		for(int i = 0; i< 5; i++)
		    this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("est")).addAttrezzo(new Attrezzo("nuovo" + i, 1));
		assertEquals("Che maleducato e scorretto, ti faccio un dispetto", this.strega.agisci(nuova));
		assertNotSame(this.iniziale.getStanzaAdiacente(Direzione.valueOf("est")), this.nuova.getStanzaCorrente());
		this.nuova.setStanzaCorrente(iniziale);
		for(int i = 0; i< 5; i++)
		    this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("sud")).addAttrezzo(new Attrezzo("nuovo" + i, 1));
		this.strega.agisci(nuova);
		assertNotSame(this.iniziale.getStanzaAdiacente(Direzione.valueOf("est")), this.nuova.getStanzaCorrente());
		this.nuova.setStanzaCorrente(iniziale);
		for(int i = 0; i< 5; i++)
		    this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("nord")).addAttrezzo(new Attrezzo("nuovo" + i, 1));
		this.strega.agisci(nuova);
		assertNotSame(this.iniziale.getStanzaAdiacente(Direzione.valueOf("est")), this.nuova.getStanzaCorrente());
	}
	
	@Test
	public void testAgisci_HaSalutato() {
		for(int i = 0; i< 5; i++)
		    this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("ovest")).addAttrezzo(new Attrezzo("nuovo" + i, 1));
		this.strega.saluta();
		assertEquals("Ecco un gran favore per un gentil giocatore", this.strega.agisci(nuova));
		assertSame(this.iniziale.getStanzaAdiacente(Direzione.valueOf("ovest")), this.nuova.getStanzaCorrente());
		this.nuova.setStanzaCorrente(iniziale);
		for(int i = 0; i< 8; i++)
		    this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("sud")).addAttrezzo(new Attrezzo("nuovo" + i, 1));
		this.strega.agisci(nuova);
		assertSame(this.iniziale.getStanzaAdiacente(Direzione.valueOf("sud")), this.nuova.getStanzaCorrente());
		this.nuova.setStanzaCorrente(iniziale);
		for(int i = 0; i< 10; i++)
		    this.nuova.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf("est")).addAttrezzo(new Attrezzo("nuovo" + i, 1));
		this.strega.agisci(nuova);
		assertSame(this.iniziale.getStanzaAdiacente(Direzione.valueOf("est")), this.nuova.getStanzaCorrente());
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoNullo() {
		assertEquals("Nessun attrezzo mi hai regalato", this.strega.riceviRegalo(null, nuova));
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoEsistente() {
		Attrezzo attrezzo = new Attrezzo("attrezzo", 1);
		assertEquals("Ihihihi lo terrò tutto per me!", this.strega.riceviRegalo(attrezzo, nuova));
		assertSame(attrezzo, this.nuova.getStanzaCorrente().getPersonaggio().getTenuto());
	}

}
