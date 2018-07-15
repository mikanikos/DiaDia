package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";

	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	private static final String MESSAGGIO_REGALO = "Ho modificato il tuo attrezzo per renderti più leggero";

	private static final String MESSAGGIO_PRESENTAZIONE = "Sono un mago buono e carino";

	public Mago(String nome) {
		this(nome, null);
	}
	
	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, MESSAGGIO_PRESENTAZIONE, attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.getTenuto()!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.getTenuto());
			this.setTenuto(null);
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		try {
			Attrezzo incantato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2);
			partita.getStanzaCorrente().addAttrezzo(incantato);
			return MESSAGGIO_REGALO;
		}
		catch (NullPointerException e) {
			return "Nessun attrezzo mi hai regalato";
		}
	}

}
