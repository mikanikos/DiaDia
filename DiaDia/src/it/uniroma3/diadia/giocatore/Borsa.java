package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.gestioneResources.ConfigurationProperties;

/**
 * Una classe che modella una borsa per portare attrezzi.
 * La borsa è gestita dal giocatore.
 *
 * @author  docente di POO
 * @see Giocatore
 * @version base
 */
public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = Integer.parseInt(ConfigurationProperties.getString("peso_max_borsa"));
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
	    this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null || attrezzo.getNome() == null) // eccezione
    		throw new IllegalArgumentException();
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
		    return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return this.attrezzi.containsValue(attrezzo); 
	}
	
	public int getPesoMax() {
	    return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null) // eccezione
    		throw new IllegalArgumentException();
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		int peso = 0;
		for(Attrezzo app : this.attrezzi.values()) // CICLO NECESSARIO PER SOMMARE I PESI
			peso += app.getPeso(); 
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	/**
	 * Rimuove il primo attrezzo con il nome passato come parametro
	 * @param nomeAttrezzo
	 * @return oggetto attrezzo eliminato
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null) // eccezione
    		throw new IllegalArgumentException();
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
		    s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		    s.append("Per peso --> ");
		    s.append(this.getSortedSetOrdinatoPerPeso().toString() + " ");
		    s.append("Per nome --> ");
		    s.append(this.getContenutoOrdinatoPerNome().toString() + " ");
		    s.append("Raggruppati in base al peso --> ");
		    s.append(this.getContenutoRaggruppatoPerPeso().toString() + " ");
		}
		else
		    s.append("Borsa vuota");
		return s.toString();
	}
	
	/**
	 * @return restituisce la lista degli attrezzi nella borsa ordinati per peso e
	 * quindi, a parità di peso, per nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = new ArrayList<>(this.attrezzi.values());
		Collections.sort(lista, new ComparatorePerPeso());
		return lista;
	}
	
	/**
	 * @return restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		return new TreeSet<>(this.attrezzi.values());
	}
	
	/**
	 * @return restituisce una mappa che associa un intero (rappresentante un
	 * peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
	 * peso: tutti gli attrezzi dell'insieme che figura come valore hanno lo
	 * stesso peso pari all'intero che figura come chiave
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Set<Attrezzo> insieme;
		Map<Integer, Set<Attrezzo>> m = new HashMap<>();
		for(Attrezzo a : this.attrezzi.values()) {
			insieme = m.get(a.getPeso());
			if (insieme == null) {
				insieme = new HashSet<Attrezzo>();
			    m.put(a.getPeso(), insieme);
			}
			insieme.add(a);
		}
		return m;
	}
	
	/**
	 * @return restituisce l'insieme gli attrezzi nella borsa ordinati per
	 * peso e quindi, a parità di peso, per nome
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> insieme = new TreeSet<>(new ComparatorePerPeso());
		insieme.addAll(this.attrezzi.values());
		return insieme;
	}
	
}
