package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, Direzione direzione, String sbloccante) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.attrezzoSbloccante = sbloccante;
	}

	public StanzaBloccata(String nome, String sbloccante) {
		super(nome);
		this.attrezzoSbloccante = sbloccante;
	}

	public Direzione getDirezioneBloccata() {
		return direzioneBloccata;
	}

	public void setDirezioneBloccata(Direzione direzioneBloccata) {
		this.direzioneBloccata = direzioneBloccata;
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(!direzione.equals(this.direzioneBloccata) || this.hasAttrezzo(this.attrezzoSbloccante))
			return super.getStanzaAdiacente(direzione);
		return this;
	}

	@Override
	public String getDescrizione() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.getNome());
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.getDirezioni()) // CICLO NECESSARIO PER AGGIUNGERE LA SCRITTA LOCKED
			if (direzione!=null)
				if (!this.hasAttrezzo(this.attrezzoSbloccante) && direzione.equals(this.direzioneBloccata))
					risultato.append(direzione + "(Locked) ");
				else
					risultato.append(direzione + " ");
		risultato.append("\nAttrezzi nella stanza: ");
		for(Attrezzo a : this.getAttrezzi().values())
			risultato.append(a + " ");
		if(this.getPersonaggio() != null)
			risultato.append("\nC'è qualcuno in questa stanza...");
		return risultato.toString();
	}
}
