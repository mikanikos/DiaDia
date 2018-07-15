package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private Stanza conDirezioneBloccata;
	private Stanza bloccata;
	private Direzione direzioneBloccata;
	private String sbloccante;
	
	@Before
	public void setUp() throws Exception {
		this.sbloccante = "attrezzo che sblocca";
		this.direzioneBloccata = Direzione.valueOf("nord");
		this.conDirezioneBloccata = new StanzaBloccata("Stanza con direzione bloccata", this.direzioneBloccata, this.sbloccante);
		this.bloccata = new Stanza("Stanza bloccata");
		this.conDirezioneBloccata.impostaStanzaAdiacente(this.direzioneBloccata, this.bloccata);
	}

	@Test
	public void testGetDescrizioneConDirezioneBloccata() {
		assertEquals("Stanza con direzione bloccata"+"\nUscite: nord(Locked) "+"\nAttrezzi nella stanza: ", this.conDirezioneBloccata.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConDirezioneSbloccata() {
		this.conDirezioneBloccata.addAttrezzo(new Attrezzo(this.sbloccante, 1));
		assertEquals("Stanza con direzione bloccata"+"\nUscite: nord "+"\nAttrezzi nella stanza: attrezzo che sblocca (1kg) ", this.conDirezioneBloccata.getDescrizione());
	}
	
	@Test
	public void testGetStanzaAdiacene_StanzaVuota() {
		Stanza vuota = new StanzaBloccata("vuota", Direzione.valueOf("nord"), "chiave");
		assertSame(vuota, vuota.getStanzaAdiacente(Direzione.valueOf("nord")));
	}
	
	@Test
	public void testGetStanzaAdiacene_ConDirezioneBloccata() {
		assertSame(this.conDirezioneBloccata, this.conDirezioneBloccata.getStanzaAdiacente(this.direzioneBloccata));
	}
	
	@Test
	public void testgetStanzaAdiacente_ConDirezioneSbloccata() {
		this.conDirezioneBloccata.addAttrezzo(new Attrezzo(this.sbloccante, 1));
		assertSame(this.bloccata, this.conDirezioneBloccata.getStanzaAdiacente(this.direzioneBloccata));
	}
	
	@Test
	public void testGetStanzaAdiacene_DirezioneNonBloccata() {
		this.conDirezioneBloccata.addAttrezzo(new Attrezzo(this.sbloccante, 1));
		Stanza nuova = new Stanza("nuova");
		this.conDirezioneBloccata.impostaStanzaAdiacente(Direzione.valueOf("sud"), nuova);
		assertSame(nuova, this.conDirezioneBloccata.getStanzaAdiacente(Direzione.valueOf("sud")));
	}

}
