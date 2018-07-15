package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gioco.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	private String messaggio;
	
	@Override
	public String esegui(Partita partita) {
		String s;
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.saluta();
			s = this.messaggio;
		} else s = "Chi dovrei salutare?...";
		return s;
	}

}
