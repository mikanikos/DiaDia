package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_RINGHIO = "Grrr... WOF WOF WOF";

	private static final String MESSAGGIO_CONSENSO = "Il cane ha gradito il regalo e forse ha lasciato qualcosa...";

	private static final String MESSAGGIO_RIFIUTO = "Al cane non è piaciuto il regalo";

	private static final String MESSAGGIO_PRESENTAZIONE = "Sono cattivo, ti mordo e ti caccio";

	private static final String CIBO_PREFERITO = "osso";

	public Cane(String nome) {
		this(nome, null);
	}
	
	public Cane(String nome, Attrezzo attrezzo) {
		super(nome, MESSAGGIO_PRESENTAZIONE, attrezzo);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_RINGHIO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		try {
			if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
				partita.getStanzaCorrente().addAttrezzo(this.getTenuto());
				this.setTenuto(attrezzo);
				msg = MESSAGGIO_CONSENSO;
			}
			else {
				partita.getStanzaCorrente().addAttrezzo(attrezzo);
				msg = MESSAGGIO_RIFIUTO;
			}
		}
		catch (NullPointerException e) {
			msg = "Il cane non ha ricevuto nessun attrezzo";
		}
		return msg;	
	}

}
