package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoPosa extends AbstractComando {

	/**
	 * Cerca di posare un attrezzo in una stanza. Gli attrezzi posati
	 * vengono rimossi dalla borsa e aggiunti alla stanza. Se non è
	 * presente l'attrezzo nella borsa o la stanza è piena,
	 * viene stampato un messaggio di errore.
	 */
	@Override
	public String esegui(Partita partita) {
		String s;
		if(this.getParametro() == null)
			s = "Quale attrezzo vuoi posare? Inserisci il nome dell'attrezzo dopo posa";
		else {
			Attrezzo attrezzoDaPosare = null;
			if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()))
				attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
			if(attrezzoDaPosare == null)
				s = "Attrezzo sconosciuto o non presente";
			else {
				partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
				partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());	
				s = "Hai posato l'attrezzo " + attrezzoDaPosare.getNome();
			}
		}
		/* TOGLIERE PER STAMPARE INFORMAZIONI DOPO AVER POSATO L'ATTREZZO
		//Stampa informazioni sullo stato della borsa
		System.out.println(partita.getGiocatore().getBorsa().toString());
		//Stampa informazioni sullo stato della partita
		System.out.println(partita.getStanzaCorrente().getDescrizione());*/
		return s;
	}

}
