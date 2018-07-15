package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gioco.Partita;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	private Attrezzo tenuto;
	
	public AbstractPersonaggio(String nome, String presentaz, Attrezzo tenuto) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
		this.tenuto = tenuto;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Attrezzo getTenuto() {
		return this.tenuto;
	}
	
	public void setTenuto(Attrezzo attrezzo) {
		this.tenuto = attrezzo;;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}

	public String saluta() {
		StringBuilder risposta =
				new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+". ");
		if (!haSalutato)
			risposta.append(this.presentazione);
		else
			risposta.append("Ci siamo gia' presentati!");
		this.haSalutato = true;
		return risposta.toString();
	}
	
	
	public abstract String agisci(Partita partita);
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
