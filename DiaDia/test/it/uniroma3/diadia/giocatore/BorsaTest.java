package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	
	private Borsa borsaConAttrezzi;
	private Borsa borsaVuota;
	private Attrezzo attrezzoSemplice;
	private Attrezzo attrezzoNomeNullo;
	
	@Before
	public void setUp() {
		this.borsaConAttrezzi = new Borsa();
		this.borsaVuota = new Borsa(10);
		this.attrezzoSemplice = new Attrezzo("attrezzoDiTest",1);
		this.attrezzoNomeNullo = new Attrezzo(null,1);
		this.borsaConAttrezzi.addAttrezzo(this.attrezzoSemplice);
	}
	

	@Test
	public void testGetPeso_BorsaCarica() {
		assertEquals(1, this.borsaConAttrezzi.getPeso());
	}
	
	@Test
	public void testGetPeso_BorsaVuota() {
		assertEquals(0, this.borsaVuota.getPeso());
	}
	
	@Test
	public void testAddAttrezzo_InBorsaVuota() {
		assertTrue(this.borsaVuota.addAttrezzo(this.attrezzoSemplice));
	}
	
	@Test
	public void testAddAttrezzo_InBorsaConAttrezzi() {
		assertTrue(this.borsaConAttrezzi.addAttrezzo(this.attrezzoSemplice));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddAttrezzo_AttrezzoNullo() {
		this.borsaConAttrezzi.addAttrezzo(null);
	}
	
	@Test
	public void testAddAttrezzo_InBorsaNonPiena() {
		for(int i=0; i<10; i++)
			this.borsaVuota.addAttrezzo(new Attrezzo("attrezzoPerRiempireBorsa"+i ,0));
		assertTrue(this.borsaVuota.addAttrezzo(new Attrezzo("attrezzoNuovo",3)));
	}
	
	@Test
	public void testAddAttrezzo_InBorsaTroppoPesante() {
		for(int i=0; i<3; i++)
			this.borsaVuota.addAttrezzo(new Attrezzo("attrezzoPerRiempireBorsa"+i ,3));
		assertFalse(this.borsaVuota.addAttrezzo(new Attrezzo("attrezzoNuovo",3)));
	}
	
	@Test
	public void testToString_BorsaConAttrezzi() {
		assertEquals("Contenuto borsa (1kg/" + this.borsaConAttrezzi.getPesoMax() + "kg): Per peso --> [attrezzoDiTest (1kg)] Per nome --> [attrezzoDiTest (1kg)] Raggruppati in base al peso --> {1=[attrezzoDiTest (1kg)]} ",this.borsaConAttrezzi.toString());
	}
	
	@Test
	public void testToString_BorsaVuota() {
		assertEquals("Borsa vuota",this.borsaVuota.toString());
	}
	
	@Test
	public void testIsEmpty_Verificato() {
		assertTrue(this.borsaVuota.isEmpty());
	}
	
	@Test
	public void testIsEmpty_NonVerificato() {
		assertFalse(this.borsaConAttrezzi.isEmpty());
	}
	
	@Test
	public void testHasAttrezzo_DoubletonVerificato() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertTrue(this.borsaConAttrezzi.hasAttrezzo("attrezzoNuovo"));
	}
	
	@Test
	public void testHasAttrezzo_DoubletonNonVerificato() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertFalse(this.borsaConAttrezzi.hasAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testHasAttrezzo_SingletonVerificato() {
		assertTrue(this.borsaConAttrezzi.hasAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testHasAttrezzo_SingletonNonVerificato() {
		assertFalse(this.borsaConAttrezzi.hasAttrezzo("attrezzoNonPresente"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHasAttrezzo_NomeNullo() {
		this.borsaConAttrezzi.hasAttrezzo(null);
	}
	
	@Test
	public void testHasAttrezzo_InBorsaVuota() {
		assertFalse(this.borsaVuota.hasAttrezzo("attrezzoInBorsaVuota"));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoEsistenteInTesta() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertSame(this.attrezzoSemplice, this.borsaConAttrezzi.getAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoEsistenteInCoda() {
		Attrezzo nuovo = new Attrezzo("attrezzoNuovo",1);
		this.borsaConAttrezzi.addAttrezzo(nuovo);
		assertSame(nuovo, this.borsaConAttrezzi.getAttrezzo("attrezzoNuovo"));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoInesistente() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("attrezzoNuovo",1));
		assertNull(this.borsaConAttrezzi.getAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testGetAttrezzo_DoubletonAttrezzoConNomeDuplicato() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("attrezzoDiTest",1));
		assertNotSame(this.attrezzoSemplice, this.borsaConAttrezzi.getAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testGetAttrezzo_SingletonAttrezzoEsistente() {
		 assertSame(this.attrezzoSemplice, this.borsaConAttrezzi.getAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testGetAttrezzo_SingletonAttrezzoInesistente() {
		assertNull(this.borsaConAttrezzi.getAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testGetAttrezzo_InBorsaVuota() {
		assertNull(this.borsaVuota.getAttrezzo("attrezzoInBorsaVuota"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAttrezzo_NomeNullo() {
		this.borsaConAttrezzi.getAttrezzo(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAttrezzo_NomeNullo() {
		this.borsaConAttrezzi.removeAttrezzo(this.attrezzoNomeNullo.getNome());
	}
	
	@Test
	public void testRemoveAttrezzo_SingletonEsistente() {
		assertSame(this.attrezzoSemplice, this.borsaConAttrezzi.removeAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzo_SingletonInesistente() {
		assertNull(this.borsaConAttrezzi.removeAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonEsistenteInTesta() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("nuovoAttrezzo",1));
		assertSame(this.attrezzoSemplice, this.borsaConAttrezzi.removeAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonEsistenteInCoda() {
		Attrezzo nuovo = new Attrezzo("nuovoAttrezzo",1);
		this.borsaConAttrezzi.addAttrezzo(nuovo);
		assertSame(nuovo, this.borsaConAttrezzi.removeAttrezzo("nuovoAttrezzo"));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonInesistente() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("nuovoAttrezzo",1));
		assertNull(this.borsaConAttrezzi.removeAttrezzo("attrezzoNonPresente"));
	}
	
	@Test
	public void testRemoveAttrezzo_DoubletonEsistenteConNomeDuplicato() {
		this.borsaConAttrezzi.addAttrezzo(new Attrezzo("attrezzoDiTest",1));
		assertNotSame(this.attrezzoSemplice, this.borsaConAttrezzi.removeAttrezzo("attrezzoDiTest"));
	}
	
	@Test
	public void testRemoveAttrezzo_InBorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo(this.attrezzoSemplice.getNome()));
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaVuota() {
		assertEquals("[]", this.borsaVuota.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaSingleton() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg)]", this.borsaVuota.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaDoubletonConPesoDiverso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg), attrezzo2 (2kg)]", this.borsaVuota.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso_BorsaDoubletonConPesoUguale() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg), attrezzo2 (1kg)]", this.borsaVuota.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaVuota() {
		assertEquals("[]", this.borsaVuota.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaSingleton() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg)]", this.borsaVuota.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaDoubletonConPesoDiverso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg), attrezzo2 (2kg)]", this.borsaVuota.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome_BorsaDoubletonConPesoUguale() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg), attrezzo2 (1kg)]", this.borsaVuota.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_BorsaVuota() {
		assertEquals("{}", this.borsaVuota.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_BorsaSingleton() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("{1=[attrezzo1 (1kg)]}", this.borsaVuota.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_BorsaDoubletonConPesoDiverso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("{1=[attrezzo1 (1kg)], 2=[attrezzo2 (2kg)]}", this.borsaVuota.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso_BorsaDoubletonConPesoUguale() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("{1=[attrezzo1 (1kg), attrezzo2 (1kg)]}", this.borsaVuota.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaVuota() {
		assertEquals("[]", this.borsaVuota.getSortedSetOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaSingleton() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg)]", this.borsaVuota.getSortedSetOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaDoubletonConPesoDiverso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg), attrezzo2 (2kg)]", this.borsaVuota.getSortedSetOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_BorsaDoubletonConPesoUguale() {
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo2", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("attrezzo1", 1));
		assertEquals("[attrezzo1 (1kg), attrezzo2 (1kg)]", this.borsaVuota.getSortedSetOrdinatoPerPeso().toString());
	}
	
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("piombo", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("ps", 3));
		this.borsaVuota.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("libro", 3));
		assertEquals("[piuma (1kg), piombo (2kg), libro (3kg), ps (3kg)]", this.borsaVuota.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNome() {
		this.borsaVuota.addAttrezzo(new Attrezzo("piombo", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("ps", 3));
		this.borsaVuota.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("libro", 3));
		assertEquals("[libro (3kg), piombo (2kg), piuma (1kg), ps (3kg)]", this.borsaVuota.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("piombo", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("ps", 3));
		this.borsaVuota.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("libro", 3));
		assertEquals("{1=[piuma (1kg)], 2=[piombo (2kg)], 3=[libro (3kg), ps (3kg)]}", this.borsaVuota.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		this.borsaVuota.addAttrezzo(new Attrezzo("piombo", 2));
		this.borsaVuota.addAttrezzo(new Attrezzo("ps", 3));
		this.borsaVuota.addAttrezzo(new Attrezzo("piuma", 1));
		this.borsaVuota.addAttrezzo(new Attrezzo("libro", 3));
		assertEquals("[piuma (1kg), piombo (2kg), libro (3kg), ps (3kg)]", this.borsaVuota.getSortedSetOrdinatoPerPeso().toString());
	}

}
