package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoIlluminante;

	public StanzaBuia(String nome, String chiarificatore) {
		super(nome);
		this.attrezzoIlluminante = chiarificatore;
	}

	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(this.attrezzoIlluminante))
			return this.toString();
		return "Qui c'è un buio pesto";
	}
}
