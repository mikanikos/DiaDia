package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class MagoTest {
	
	private AbstractPersonaggio mago;
	private Partita nuova;
	private Attrezzo attrezzo;

	@Before
	public void setUp() throws Exception {
		this.nuova = new Partita();
		this.nuova.creaLabirintoStandard();
		this.mago = new Mago("mago", null);
		this.nuova.getStanzaCorrente().setPersonaggio(mago);
		this.attrezzo = new Attrezzo("attrezzo", 2);
	}

	@Test
	public void testAgisci_AttrezzoInesistente() {
		assertEquals("Mi spiace, ma non ho piu' nulla...", this.mago.agisci(nuova));
	}
	
	@Test
	public void testAgisci_AttrezzoEsistente() {
		this.mago.setTenuto(this.attrezzo);
		assertEquals("Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!", this.mago.agisci(nuova));
		assertNull(this.mago.getTenuto());
		assertSame(this.attrezzo, this.nuova.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()));
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoNullo() {
		assertEquals("Nessun attrezzo mi hai regalato", this.mago.riceviRegalo(null, nuova));
	}
	
	@Test
	public void testRiceviRegalo_AttrezzoEsistente() {
		assertEquals("Ho modificato il tuo attrezzo per renderti più leggero", this.mago.riceviRegalo(attrezzo, nuova));
		assertEquals(this.attrezzo.getNome(), this.nuova.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()).getNome());
		assertEquals(1, this.nuova.getStanzaCorrente().getAttrezzo(this.attrezzo.getNome()).getPeso());
	}
	
}
