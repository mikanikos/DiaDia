package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractComandoTest {
	
	private AbstractComando cmd;

	@Before
	public void setUp() throws Exception {
		this.cmd = new FakeComando();
	}

	@Test
	public void testGetSetParametro() {
		assertNull(this.cmd.getParametro());
		this.cmd.setParametro("parametro");
		assertEquals("parametro", this.cmd.getParametro());
	}
	
	@Test
	public void testGetSetNome() {
		assertNull(this.cmd.getNome());
		this.cmd.setNome("comando");
		assertEquals("comando", this.cmd.getNome());
	}
	
	

}
