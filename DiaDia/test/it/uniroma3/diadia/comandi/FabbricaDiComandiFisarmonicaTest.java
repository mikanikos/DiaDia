package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandi factory;
	private AbstractComando polimorfo;

	@Before
	public void setUp() throws Exception {
		this.factory = new FabbricaDiComandiFisarmonica();
	}

	@Test
	public void testComandoNullo() {
		this.polimorfo = new ComandoNonValido();
		AbstractComando comando = this.factory.costruisciComando("");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoNonValido() {
		this.polimorfo = new ComandoNonValido();
		AbstractComando comando = this.factory.costruisciComando("boh");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoVaiSenzaParametro() {
		this.polimorfo = new ComandoVai();
		AbstractComando comando = this.factory.costruisciComando("vai");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoVaiConParametro() {
		this.polimorfo = new ComandoVai();
		AbstractComando comando = this.factory.costruisciComando("vai sud");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertEquals("sud", comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiSenzaParametro() {
		this.polimorfo = new ComandoPrendi();
		AbstractComando comando = this.factory.costruisciComando("prendi");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoPrendiConParametro() {
		this.polimorfo = new ComandoPrendi();
		AbstractComando comando = this.factory.costruisciComando("prendi osso");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	public void testComandoPosaSenzaParametro() {
		this.polimorfo = new ComandoPosa();
		AbstractComando comando = this.factory.costruisciComando("posa");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoPosaConParametro() {
		this.polimorfo = new ComandoPosa();
		AbstractComando comando = this.factory.costruisciComando("posa osso");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	public void testComandoAiuto() {
		this.polimorfo = new ComandoAiuto();
		AbstractComando comando = this.factory.costruisciComando("aiuto");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoFine() {
		this.polimorfo = new ComandoFine();
		AbstractComando comando = this.factory.costruisciComando("fine");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoGuarda() {
		this.polimorfo = new ComandoGuarda();
		AbstractComando comando = this.factory.costruisciComando("guarda");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoInteragisci() {
		this.polimorfo = new ComandoInteragisci();
		AbstractComando comando = this.factory.costruisciComando("interagisci");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoSaluta() {
		this.polimorfo = new ComandoSaluta();
		AbstractComando comando = this.factory.costruisciComando("saluta");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoRegalaSenzaParametro() {
		this.polimorfo = new ComandoRegala();
		AbstractComando comando = this.factory.costruisciComando("regala");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testComandoRegalaConParametro() {
		this.polimorfo = new ComandoRegala();
		AbstractComando comando = this.factory.costruisciComando("regala osso");
		assertEquals(this.polimorfo.getNome(), comando.getNome());
		assertEquals("osso", comando.getParametro());
	}

}
