package it.uniroma3.diadia.gioco;

import java.io.FileNotFoundException;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.gestioneResources.ConfigurationProperties;
import it.uniroma3.diadia.gestioneResources.FormatoFileNonValidoException;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private final static int NUMERO_LIVELLI = Integer.valueOf(ConfigurationProperties.getString("numero_livelli"));
	
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private int livello;
	private boolean finita;

	public Partita(){
		this.livello = 0;
		nextLevel();
		this.finita = false;
	}

	/**
	 * Crea il labirinto di gioco
	 */
	private void creaLabirinto(){
		try {
			this.labirinto = new Labirinto("labirinto" + this.livello + ".txt");
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			e.printStackTrace();
		}
	}

	public void creaLabirintoStandard() {
		this.labirinto = new Labirinto();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}
	
	/**
	 * Crea il giocatore
	 */
	private void creaGiocatore(){
		this.giocatore = new Giocatore();
	}

	public Labirinto getLabirinto(){
		return this.labirinto;
	}

	public Giocatore getGiocatore(){
		return this.giocatore;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}

	public int getNumeroLivelli() {
		return NUMERO_LIVELLI;
	}
	
	public int getLivelloCorrente() {
		return this.livello;
	}

	public void setLivello(int livello) {
		this.livello = livello;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente() && this.livello == NUMERO_LIVELLI;
	}
	
	public boolean livelloCompletato() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}
	
	public void nextLevel() {
		this.livello++;
		creaLabirinto();
		creaGiocatore();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata persa
	 * @return vero se partita persa
	 */
	public boolean persa(){
		return this.giocatore.getCfu() == 0;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || persa();
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}

}
