package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gioco.Partita;

/* SERVE SOLO PER TESTARE LA CLASSE AbstractComando */
public class FakeComando extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		return null;
	}

}
