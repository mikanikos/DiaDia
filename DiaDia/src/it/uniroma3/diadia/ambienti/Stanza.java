package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
		this.personaggio = null;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(stanza == null || direzione == null) // eccezione
			throw new IllegalArgumentException();
		this.stanzeAdiacenti.put(direzione,stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(direzione == null) // eccezione
			throw new IllegalArgumentException();
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null || attrezzo.getNome() == null) // eccezione
			throw new IllegalArgumentException();
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return this.attrezzi.containsValue(attrezzo);
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for(Direzione d : this.stanzeAdiacenti.keySet())
			risultato.append(d + " ");
		//risultato.append(this.stanzeAdiacenti.keySet().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		for(Attrezzo a : this.attrezzi.values())
			risultato.append(a + " ");
		//risultato.append(this.attrezzi.values().toString());
		if(this.personaggio != null)
			risultato.append("\nC'è qualcuno in questa stanza...");
		return risultato.toString();

	}

	/**
	 * Controlla se la stanza è vuota
	 * @return true se non ci sono attrezzi nella stanza, false altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null) // eccezione
			throw new IllegalArgumentException();
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null) // eccezione
			throw new IllegalArgumentException();
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null || attrezzo.getNome() == null) // eccezione
			throw new IllegalArgumentException();
		if(this.attrezzi.remove(attrezzo.getNome()) == null)
			return false;
		return true;
	}
	
	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}

	/**
	 * @return the numeroStanzeAdiacenti
	 */
	public int getNumeroStanzeAdiacenti() {
		return this.stanzeAdiacenti.size();
	}

	/**
	 * @return the stanzeAdiacenti
	 */
	public Collection<Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti.values();
	}

	/**
	 * @return the numeroAttrezzi
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

}