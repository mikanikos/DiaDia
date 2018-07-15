package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gioco.Partita;

/**
 * Questa interfaccia modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */
public abstract class AbstractComando {
	
	private String parametro;
	private String nome;
	
	/**
	 * esecuzione del comando
	 * @param partita è la partita in un certo stato
	 */
	public abstract String esegui(Partita partita);
	
	/**
	 * set parametro del comando
	 * @param parametro è il parametro che serve per eseguire un certo comando
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
