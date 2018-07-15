package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.gestioneResources.CaricatoreLabirinto;
import it.uniroma3.diadia.gestioneResources.FormatoFileNonValidoException;
import it.uniroma3.diadia.personaggi.*;

/**
 * Questa classe modella il labirinto del gioco.
 * Il labirinto ha un'entrata (stanza di ingresso) ed
 * un' uscita (stanza vincente)
 *
 * @author  studente di POO
 * @see Partita
 * @version base
 */

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Set<Attrezzo> attrezziLabirinto;

	public Labirinto() {
		this.attrezziLabirinto = new HashSet<>();
		creaLabirintoStandard();
	}

	public Labirinto(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void creaLabirintoStandard() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* crea i personaggi */
		AbstractPersonaggio mago = new Mago("Merlino", new Attrezzo("scettro",3));
		AbstractPersonaggio cane = new Cane("Cagnaccio", new Attrezzo("peluche", 1));
		AbstractPersonaggio strega = new Strega("Gelosetta", null);

		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.valueOf("nord"), biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.valueOf("est"), aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.valueOf("sud"), aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.valueOf("ovest"), laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.valueOf("est"), laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.valueOf("ovest"), atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.valueOf("nord"), atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.valueOf("est"), aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.valueOf("ovest"), laboratorio);
		laboratorio.impostaStanzaAdiacente(Direzione.valueOf("est"), atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.valueOf("ovest"), aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzione.valueOf("sud"), atrio);

		/* pone gli attrezzi nelle stanze */
		aggiungiAttrezzo(lanterna,aulaN10);
		aggiungiAttrezzo(osso,atrio);
		//aggiungiAttrezzo(new Attrezzo("osso", 5), aulaN11);

		/* pone i personaggi nelle stanze */
		atrio.setPersonaggio(mago);
		aulaN10.setPersonaggio(cane);
		aulaN11.setPersonaggio(strega);

		/* PER VERIFICARE NUOVE STANZE */ 
		Stanza magica = new StanzaMagica("Magica", 1);
		Stanza buia = new StanzaBuia("Buia", "lanterna");
		Stanza bloccata = new StanzaBloccata("Bloccata", Direzione.valueOf("sud"), "osso");
		aulaN10.impostaStanzaAdiacente(Direzione.valueOf("sud"), magica);
		magica.impostaStanzaAdiacente(Direzione.valueOf("nord"), aulaN10);
		laboratorio.impostaStanzaAdiacente(Direzione.valueOf("nord"), buia);
		buia.impostaStanzaAdiacente(Direzione.valueOf("sud"), laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.valueOf("nord"), bloccata);
		bloccata.impostaStanzaAdiacente(Direzione.valueOf("sud"), aulaN11);
		bloccata.impostaStanzaAdiacente(Direzione.valueOf("nord"), buia);
		buia.impostaStanzaAdiacente(Direzione.valueOf("nord"), bloccata);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;  
		this.stanzaVincente = biblioteca;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	/**
	 * 
	 * @param a attrezzo da aggiungere
	 * @param s stanza in cui aggiungere l'attrezzo
	 * @return true se è stato aggiunto l'attrezzo correttamente (cioè, non
	 *         è gia' presente uno stesso attrezzo con nome uguale), false altrimenti
	 */
	public boolean aggiungiAttrezzo(Attrezzo a, Stanza s) {
		if(this.attrezziLabirinto.add(a))
			return s.addAttrezzo(a);
		return false;
	}

}
