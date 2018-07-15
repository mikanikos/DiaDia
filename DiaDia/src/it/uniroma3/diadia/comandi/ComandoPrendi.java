package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoPrendi extends AbstractComando {

	/**
	 * Cerca di prendere un attrezzo da una stanza. Gli attrezzi presi
	 * vengono rimossi dalla stanza e aggiunti alla borsa del giocatore.
	 * Se non è presente l'attrezzo nella stanza o la borsa è piena,
	 * viene stampato un messaggio di errore
	 */
	@Override
	public String esegui(Partita partita) {
		String s;
		if(this.getParametro() == null)
			s = "Quale attrezzo vuoi prendere? Inserisci il nome dell'attrezzo dopo prendi";
		else {
			Attrezzo attrezzoDaPrendere = null;
			if(partita.getStanzaCorrente().hasAttrezzo(this.getParametro()))
				attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(this.getParametro());
			if(attrezzoDaPrendere == null)
				s = "Attrezzo sconosciuto o non presente";
			else {
				if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) {
					partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
					s = "Hai preso l'attrezzo " + attrezzoDaPrendere.getNome();
				}
				else
					s = "Borsa troppo pesante! Posa degli oggetti per prenderne degli altri";
			}
		}
		/* TOGLIERE PER STAMPARE INFORMAZIONI DOPO AVER PRESO L'ATTREZZO
		//Stampa informazioni sullo stato della borsa
		System.out.println(partita.getGiocatore().getBorsa().toString());
		//Stampa informazioni sullo stato della partita
		System.out.println(partita.getStanzaCorrente().getDescrizione());*/
		return s;
	}

}
