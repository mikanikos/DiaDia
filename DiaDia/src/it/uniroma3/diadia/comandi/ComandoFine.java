package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gioco.Partita;

public class ComandoFine extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		String s = "Grazie di aver giocato!";  // si desidera smettere
		partita.setFinita();
		return s;
	}

}
