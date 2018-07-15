package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.gestioneResources.ConfigurationProperties;

/**
 * Questa classe modella il giocatore.
 * Il giocatore ha la responsabilità di gestire i CFU del giocatore e
 * di memorizzare gli attrezzi in un oggetto istanza della classe Borsa
 *
 * @author  studente di POO
 * @see Partita
 * @version base
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = Integer.parseInt(ConfigurationProperties.getString("cfu_iniziali"));
	
	private Borsa borsa;
	private int cfu;
	
	public Giocatore(){
		creaBorsa();
		this.cfu = CFU_INIZIALI;	
	}
	
	/**
     * Crea una borsa per il giocatore
     */
	private void creaBorsa(){
		this.borsa = new Borsa();
	}
	
	public Borsa getBorsa(){
		return this.borsa;
	}
	
	public int getCfuIniziali() {
		return CFU_INIZIALI;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}

}
