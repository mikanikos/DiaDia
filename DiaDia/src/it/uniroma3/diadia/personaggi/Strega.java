package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_GENTILE = "Ecco un gran favore per un gentil giocatore";

	private static final String MESSAGGIO_PERMALOSO = "Che maleducato e scorretto, ti faccio un dispetto";

	private static final String MESSAGGIO_RISATA = "Ihihihi lo terrò tutto per me!";

	private static final String MESSAGGIO_PRESENTAZIONE = "Sono una strega un po' permalosetta";

	public Strega(String nome) {
		this(nome, null);
	}
	
	public Strega(String nome, Attrezzo attrezzo) {
		super(nome, MESSAGGIO_PRESENTAZIONE, attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.haSalutato()) {
			msg = MESSAGGIO_GENTILE;
			partita.setStanzaCorrente(calcolaStanzaAdiacente(partita, msg));
		}
		else {
			msg = MESSAGGIO_PERMALOSO;
			partita.setStanzaCorrente(calcolaStanzaAdiacente(partita, msg));
		}
		return msg;
	}

	private Stanza calcolaStanzaAdiacente(Partita partita, String msg) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Direzione primaDirezione = new ArrayList<>(stanzaCorrente.getDirezioni()).get(0);
		Stanza stanza = stanzaCorrente.getStanzaAdiacente(primaDirezione);
		int numeroAttrezzi = stanzaCorrente.getStanzaAdiacente(primaDirezione).getNumeroAttrezzi();
		for(Stanza s : stanzaCorrente.getStanzeAdiacenti()) {
			if(msg.equals(MESSAGGIO_GENTILE) && numeroAttrezzi < s.getNumeroAttrezzi()) {
				stanza = s;
				numeroAttrezzi = s.getNumeroAttrezzi();
			}
			if(msg.equals(MESSAGGIO_PERMALOSO) && numeroAttrezzi > s.getNumeroAttrezzi()) {
				stanza = s;
				numeroAttrezzi = s.getNumeroAttrezzi();
			}
		}
		return stanza;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo != null) {
			this.setTenuto(attrezzo);
			return MESSAGGIO_RISATA;
		}
		return "Nessun attrezzo mi hai regalato";
	}

}
