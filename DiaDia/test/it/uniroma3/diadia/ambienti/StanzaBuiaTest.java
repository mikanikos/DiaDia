package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	private Stanza oscura;
	private String chiarificatore;
	
	@Before
	public void setUp() throws Exception {
		this.chiarificatore = "attrezzo che illumina la stanza";
		this.oscura = new StanzaBuia("Buia", this.chiarificatore);
	}

	@Test
	public void testDescrizioneStanzaVuota() {
		assertEquals("Qui c'è un buio pesto", this.oscura.getDescrizione());
	}
	
	@Test
	public void testDescrizioneConAttrezzoChiarificatore() {
		this.oscura.addAttrezzo(new Attrezzo(this.chiarificatore,1));
		assertEquals("Buia"+"\nUscite: "+"\nAttrezzi nella stanza: attrezzo che illumina la stanza (1kg) ", this.oscura.getDescrizione());
	}
	
	@Test
	public void testDescrizioneConAttrezzoNonChiarificatore() {
		this.oscura.addAttrezzo(new Attrezzo("nuovo",1));
		assertEquals("Qui c'è un buio pesto", this.oscura.getDescrizione());
	}

}
