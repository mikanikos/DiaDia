package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Before;

public class StanzaTest {
	
	private Stanza stanzaConUscitaEAttrezzi;
	private Stanza stanzaSenzaUsciteEVuota;
	private Attrezzo attrezzoSemplice;
	private Attrezzo attrezzoNomeNullo;
	
	@Before
	public void setUp() {
		this.stanzaConUscitaEAttrezzi = new Stanza("stanzaComplessa");
		this.stanzaSenzaUsciteEVuota = new Stanza("stanzaSemplice");
		this.attrezzoSemplice = new Attrezzo("attrezzoDiTest",2);
		this.attrezzoNomeNullo = new Attrezzo(null,3);
		this.stanzaConUscitaEAttrezzi.impostaStanzaAdiacente(Direzione.valueOf("ovest"), this.stanzaSenzaUsciteEVuota);
		this.stanzaConUscitaEAttrezzi.addAttrezzo(this.attrezzoSemplice);
	}
	
	@Test
	public void testGetStanzaAdiacente_StanzaEsistente() {
		assertSame(this.stanzaSenzaUsciteEVuota, this.stanzaConUscitaEAttrezzi.getStanzaAdiacente(Direzione.valueOf("ovest")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetStanzaAdiacente_StanzaInesistente() {
		assertNull(this.stanzaConUscitaEAttrezzi.getStanzaAdiacente(Direzione.valueOf("direzioneStrana")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetStanzaAdiacente_DirezioneNull() {
		this.stanzaConUscitaEAttrezzi.getStanzaAdiacente(null);
	}
	
	@Test
	public void testImpostaStanzaAdiacente() {
		this.stanzaSenzaUsciteEVuota.impostaStanzaAdiacente(Direzione.valueOf("sud"), this.stanzaConUscitaEAttrezzi);
		assertEquals(this.stanzaConUscitaEAttrezzi, this.stanzaSenzaUsciteEVuota.getStanzaAdiacente(Direzione.valueOf("sud")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testImpostaStanzaAdiacente_DirezioneInesistente() {
		this.stanzaSenzaUsciteEVuota.impostaStanzaAdiacente(Direzione.valueOf("direzioneStrana"), this.stanzaConUscitaEAttrezzi);
		assertSame(this.stanzaConUscitaEAttrezzi, this.stanzaSenzaUsciteEVuota.getStanzaAdiacente(Direzione.valueOf("direzioneStrana")));;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testImpostaStanzaAdiacente_DirezioneNull() {
		this.stanzaSenzaUsciteEVuota.impostaStanzaAdiacente(null, this.stanzaConUscitaEAttrezzi);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testImpostaStanzaAdiacente_StanzaNulla() {
		this.stanzaSenzaUsciteEVuota.impostaStanzaAdiacente(Direzione.valueOf("direzioneStrana"), null);
	}
	
	@Test
	public void testAddAttrezzo_InStanzaVuota() {
		assertTrue(this.stanzaSenzaUsciteEVuota.addAttrezzo(this.attrezzoSemplice));
	}
	
	@Test
	public void testAddAttrezzo_InStanzaConAttrezzi() {
		assertTrue(this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("nuovo", 4)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddAttrezzo_AttrezzoNullo() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddAttrezzo_AttrezzoNomeNullo() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(this.attrezzoNomeNullo);
	}
	
	@Test
	public void testAddAttrezzo_InStanzaNonPiena() {
		for(int i=0; i<9; i++)
			this.stanzaConUscitaEAttrezzi.addAttrezzo(this.attrezzoSemplice);
		assertTrue(this.stanzaConUscitaEAttrezzi.addAttrezzo(this.attrezzoSemplice));
	}
	
	@Test
	public void testToString_StanzaConUsciteEAttrezzi() {
		assertEquals("stanzaComplessa"+"\nUscite: ovest "+"\nAttrezzi nella stanza: attrezzoDiTest (2kg) ",this.stanzaConUscitaEAttrezzi.toString());
	}
	
	@Test
	public void testToString_StanzaSenzaUsciteEAttrezzi() {
		assertEquals("stanzaSemplice"+"\nUscite: "+"\nAttrezzi nella stanza: ",this.stanzaSenzaUsciteEVuota.toString());
	}
	
	@Test
	public void testIsEmpty_Verificato() {
		assertTrue(this.stanzaSenzaUsciteEVuota.isEmpty());
	}
	
	@Test
	public void testIsEmpty_NonVerificato() {
		assertFalse(this.stanzaConUscitaEAttrezzi.isEmpty());
	}
	
	@Test
	public void testHasAttrezzo_DoubletonVerificato() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertTrue(this.stanzaConUscitaEAttrezzi.hasAttrezzo("attrezzoNuovo"));
	}
	
	@Test
	public void testHasAttrezzo_DoubletonNonVerificato() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertFalse(this.stanzaConUscitaEAttrezzi.hasAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testHasAttrezzo_SingletonVerificato() {
		assertTrue(this.stanzaConUscitaEAttrezzi.hasAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testHasAttrezzo_SingletonNonVerificato() {
		assertFalse(this.stanzaConUscitaEAttrezzi.hasAttrezzo("attrezzoNonPresente"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHasAttrezzo_NomeNullo() {
		this.stanzaConUscitaEAttrezzi.hasAttrezzo(this.attrezzoNomeNullo.getNome());
	}
	
	@Test
	public void testHasAttrezzo_InStanzaVuota() {
		assertFalse(this.stanzaSenzaUsciteEVuota.hasAttrezzo("attrezzoInStanzaVuota"));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoEsistenteInTesta() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertSame(this.attrezzoSemplice, this.stanzaConUscitaEAttrezzi.getAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoEsistenteInCoda() {
		Attrezzo nuovo = new Attrezzo("attrezzoNuovo",1);
		this.stanzaConUscitaEAttrezzi.addAttrezzo(nuovo);
		assertSame(nuovo, this.stanzaConUscitaEAttrezzi.getAttrezzo("attrezzoNuovo"));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoInesistente() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertNull(this.stanzaConUscitaEAttrezzi.getAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoConNomeDuplicato() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("attrezzoDiTest",1));
		assertNotSame(this.attrezzoSemplice, this.stanzaConUscitaEAttrezzi.getAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testGetAttrezzo_SingletonAttrezzoEsistente() {
		 assertSame(this.attrezzoSemplice, this.stanzaConUscitaEAttrezzi.getAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testGetAttrezzo_SingletonAttrezzoInesistente() {
		assertNull(this.stanzaConUscitaEAttrezzi.getAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testGetAttrezzo_InStanzaVuota() {
		assertNull(this.stanzaSenzaUsciteEVuota.getAttrezzo("attrezzoInStanzaVuota"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAttrezzo_NomeNullo() {
		this.stanzaConUscitaEAttrezzi.getAttrezzo(this.attrezzoNomeNullo.getNome());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAttrezzo_NomeNullo() {
		this.stanzaConUscitaEAttrezzi.removeAttrezzo(this.attrezzoNomeNullo);
	}
	
	@Test
	public void testRemoveAttrezzo_SingletonEsistente() {
		assertTrue(this.stanzaConUscitaEAttrezzi.removeAttrezzo(this.attrezzoSemplice));
	}
	
	@Test
	public void testRemoveAttrezzo_SingletonInesistente() {
		assertFalse(this.stanzaConUscitaEAttrezzi.removeAttrezzo(new Attrezzo("attrezzoNonPresente",3)));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonEsistenteInTesta() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("nuovoAttrezzo",1));
		assertTrue(this.stanzaConUscitaEAttrezzi.removeAttrezzo(this.attrezzoSemplice));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonEsistenteInCoda() {
		Attrezzo nuovo = new Attrezzo("nuovoAttrezzo",1);
		this.stanzaConUscitaEAttrezzi.addAttrezzo(nuovo);
		assertTrue(this.stanzaConUscitaEAttrezzi.removeAttrezzo(nuovo));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonInesistente() {
		this.stanzaConUscitaEAttrezzi.addAttrezzo(new Attrezzo("nuovoAttrezzo",1));
		assertFalse(this.stanzaConUscitaEAttrezzi.removeAttrezzo(new Attrezzo("attrezzoNonPresente",3)));
	}
	
	@Test
	public void testRemoveAttrezzo_InStanzaVuota() {
		assertFalse(this.stanzaSenzaUsciteEVuota.removeAttrezzo(this.attrezzoSemplice));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAttrezzo_AttrezzoNullo() {
		this.stanzaConUscitaEAttrezzi.removeAttrezzo(null);
	}
	
}
