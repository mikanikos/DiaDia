package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoRegala extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		String s;
		if(partita.getStanzaCorrente().getPersonaggio() == null)
			s = "Non puoi regalare nulla perché non c'è nessun personaggio in stanza";
		else {
			if(this.getParametro() == null)
				s = "Quale attrezzo vuoi regalare al personaggio nella stanza? Inserisci il nome dell'attrezzo dopo regala";
			else {
				Attrezzo attrezzoDaRegalare = null;
				if(partita.getGiocatore().getBorsa().hasAttrezzo(this.getParametro()))
					attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(this.getParametro());
				if(attrezzoDaRegalare == null)
					s = "Attrezzo sconosciuto o non presente";
				else {
					partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
					s = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoDaRegalare, partita);
				}
			}
			/* TOGLIERE PER STAMPARE INFORMAZIONI DOPO AVER REGALATO UN ATTREZZO
			//Stampa informazioni sullo stato della borsa
			System.out.println(partita.getGiocatore().getBorsa().toString());
			//Stampa informazioni sullo stato della partita
			System.out.println(partita.getStanzaCorrente().getDescrizione());*/
		}
		return s;
	}

}
