package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoVai extends AbstractComando {

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public String esegui(Partita partita) {
		StringBuilder risultato = new StringBuilder();
		if(this.getParametro()==null)
			risultato.append("Dove vuoi andare? Inserisci una direzione valida (nord, sud, est, ovest) dopo vai\n");
		else {
			Stanza prossimaStanza = null;
			try {
			    prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(this.getParametro()));
			}
			catch (IllegalArgumentException e) {
			}
			if (prossimaStanza == null)
				risultato.append("Direzione inesistente\n");
			else {
				/* PER NON CONTEGGIARE LO SPOSTAMENTO QUANDO LA STANZA E' BLOCCATA */
				if(!partita.getStanzaCorrente().equals(prossimaStanza))
			        partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			    partita.setStanzaCorrente(prossimaStanza);
			}
		}
		/* Stampa informazioni sullo stato della partita */
		risultato.append("Cfu a disposizione: " + partita.getGiocatore().getCfu());
		risultato.append("\n" + partita.getStanzaCorrente().getDescrizione());
		return risultato.toString();
	}

}
