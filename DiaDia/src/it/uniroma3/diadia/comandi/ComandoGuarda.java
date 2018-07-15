package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gioco.Partita;

public class ComandoGuarda extends AbstractComando {
	
	/**
	 * Stampa informazioni sullo stato della partita
	 */
	@Override
	public String esegui(Partita partita) {
		StringBuilder risultato = new StringBuilder();
		risultato.append("Cfu a disposizione: " +partita.getGiocatore().getCfu());
		risultato.append("\n" + partita.getGiocatore().getBorsa().toString());
		risultato.append("\n" + partita.getStanzaCorrente().getDescrizione());
		return risultato.toString();
	}
	
}
