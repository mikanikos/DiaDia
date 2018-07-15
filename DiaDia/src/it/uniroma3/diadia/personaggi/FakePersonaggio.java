package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

/* SERVE SOLO PER TESTARE LA CLASSE AbstractPersonaggio */
public class FakePersonaggio extends AbstractPersonaggio {

	public FakePersonaggio(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione, attrezzo);
	}
	
	@Override
	public String agisci(Partita partita) {
		return "done";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		this.setTenuto(attrezzo);
		return "done";
	}

}
