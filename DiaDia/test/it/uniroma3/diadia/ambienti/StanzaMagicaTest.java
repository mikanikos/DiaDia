package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica speciale;
	private Attrezzo nuovo;
	
	@Before
	public void setUp() throws Exception {
		this.speciale = new StanzaMagica("Con nuova soglia", 1);
		this.nuovo = new Attrezzo("attrezzoDiTest", 3);
	}

	@Test
	public void testAddAttrezzoInStanzaVuota() {
		this.speciale.addAttrezzo(this.nuovo);
		assertSame(this.nuovo, this.speciale.getAttrezzo(this.nuovo.getNome()));
	}
	
	@Test
	public void testAddAttrezzoComportamentoMagico() {
		this.speciale.addAttrezzo(new Attrezzo("primo",1));
		this.speciale.addAttrezzo(this.nuovo);
		assertTrue(this.speciale.hasAttrezzo("tseTiDozzertta"));
		assertEquals(6, this.speciale.getAttrezzo("tseTiDozzertta").getPeso());
		this.speciale.addAttrezzo(new Attrezzo("altro",1));
		assertTrue(this.speciale.hasAttrezzo("ortla"));
		assertEquals(2, this.speciale.getAttrezzo("ortla").getPeso());
	}
	
	@Test
	public void testAddAttrezzoComportamentoMagicoRimuovendoAttrezziERimettendoli() {
		Attrezzo attrezzo = new Attrezzo("primo",1);
		this.speciale.addAttrezzo(attrezzo);
		this.speciale.addAttrezzo(this.nuovo);
		assertTrue(this.speciale.hasAttrezzo("tseTiDozzertta"));
		assertEquals(6, this.speciale.getAttrezzo("tseTiDozzertta").getPeso());
		this.speciale.removeAttrezzo(attrezzo);
		this.speciale.removeAttrezzo(this.nuovo);
		this.speciale.addAttrezzo(new Attrezzo("diverso",5));
		assertTrue(this.speciale.hasAttrezzo("osrevid"));
		assertEquals(10, this.speciale.getAttrezzo("osrevid").getPeso());
	}

}
