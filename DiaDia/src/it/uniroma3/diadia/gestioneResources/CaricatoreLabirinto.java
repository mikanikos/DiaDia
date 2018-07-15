package it.uniroma3.diadia.gestioneResources;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";      

	/* prefisso di una singola riga di testo contenente le stanze speciali nel formato: <tipoStanza> <nomeStanza> <parametroSpecifico> */
	private static final String STANZE_SPECIALI_MARKER = "Stanze speciali: ";      

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente le specifiche dei Personaggi da collocare nel formato <tipoPersonaggio> <nomePersonaggio> */
	private static final String PERSONAGGI_MARKER = "Personaggi: ";

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	/* per fissare una direzione bloccata per una StanzaBloccata sostituire nomeStanzaA con bloccata */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Stanze speciali: bloccata atrio martello, magica N6 3
		Inizio: N10
		Vincente: N11
		Personaggi: mago Merlino N10, cane Cagnaccio biblioteca
		Attrezzi: martello 10 biblioteca, pinza 2 Merlino, uovo 1 atrio, matita 2 N6
		Uscite: biblioteca nord N10, biblioteca sud N11, atrio est bloccata, atrio est N6, biblioteca est atrio

	 */

	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;
	private Map<String, AbstractPersonaggio> nome2personaggio;
	private Map<String, Attrezzo> nome2attrezzo;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.nome2personaggio = new HashMap<String, AbstractPersonaggio>();
		this.nome2attrezzo = new HashMap<String, Attrezzo>();
		ClassLoader loader = CaricatoreLabirinto.class.getClassLoader();
		//this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.reader = new LineNumberReader(new InputStreamReader(loader.getResourceAsStream(nomeFile)));
	}

	/* COSTRUTTORE PER TEST-CASE */
	public CaricatoreLabirinto(StringReader in) {
		this.nome2stanza = new HashMap<String, Stanza>();
		this.nome2personaggio = new HashMap<String, AbstractPersonaggio>();
		this.nome2attrezzo = new HashMap<String, Attrezzo>();
		this.reader = new LineNumberReader(in);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeSpeciali();
			this.leggiInizialeEvincente();
			this.leggiECollocaPersonaggi();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	/* METODO PER INSERIRE STANZE SPECIALI NEL LABIRINTO */
	private void leggiECreaStanzeSpeciali() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_SPECIALI_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String tipoStanza = null;
			String nomeStanza = null;
			String parametro1 = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di stanza speciale."));
				tipoStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza speciale."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il primo parametro della stanza speciale."));
				parametro1 = scannerLinea.next();
			}
			Stanza stanza = null;
			try {
				String nomeClasse = "it.uniroma3.diadia.ambienti.Stanza";
				nomeClasse += Character.toUpperCase(tipoStanza.charAt(0));
				nomeClasse += tipoStanza.substring(1);
				stanza = (Stanza)Class.forName(nomeClasse).getDeclaredConstructor(String.class, String.class).newInstance(nomeStanza, parametro1);
			} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) // AGGIUNTO PER ITERARE SU TUTTI GLI ELEMENTI
				result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String collocazione = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				collocazione = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, collocazione);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String collocazione) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			if(!isStanzaValida(collocazione) && !isPersonaggioValido(collocazione))
				check(isStanzaValida(collocazione),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza/personaggio " +collocazione+" inesistente");
			if(!giaCreato(nomeAttrezzo)) {
				if(isStanzaValida(collocazione))
					this.nome2stanza.get(collocazione).addAttrezzo(attrezzo);
				if(isPersonaggioValido(collocazione))
					this.nome2personaggio.get(collocazione).setTenuto(attrezzo);
				this.nome2attrezzo.put(attrezzo.getNome(), attrezzo);
			}
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	/* METODO PER INSERIRE PERSONAGGI NEL LABIRINTO */
	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String tipoPersonaggio = null;
			String nomePersonaggio = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo di personaggio."));
				tipoPersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nomePersonaggio+"."));
				nomeStanza = scannerLinea.next();
			}
			inserisciPersonaggio(tipoPersonaggio, nomePersonaggio, nomeStanza);
		}
	}

	private void inserisciPersonaggio(String tipoPersonaggio, String nomePersonaggio, String nomeStanza) throws FormatoFileNonValidoException {
		AbstractPersonaggio personaggio = null;
		try {
			String nomeClasse = "it.uniroma3.diadia.personaggi.";
			nomeClasse += Character.toUpperCase(tipoPersonaggio.charAt(0));
			nomeClasse += tipoPersonaggio.substring(1);
			personaggio = (AbstractPersonaggio)Class.forName(nomeClasse).getDeclaredConstructor(String.class).newInstance(nomePersonaggio);
			check(isStanzaValida(nomeStanza),"Personaggio "+ personaggio.getNome() +" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
			this.nome2personaggio.put(nomePersonaggio, personaggio);
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private boolean isPersonaggioValido(String nomePersonaggio) {
		return this.nome2personaggio.containsKey(nomePersonaggio);
	}
	
	/* Metodo per controllare se l'attrezzo con tale nome è stato gia' inserito */
	private boolean giaCreato(String nomeAttrezzo) {
		return this.nome2attrezzo.containsKey(nomeAttrezzo);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			String dir = null;
			String stanzaDestinazione = null;
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				stanzaDestinazione = scannerDiLinea.next();
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		if(nomeA.equals("bloccata")) {
			StanzaBloccata partenzaDa = null;
			try {
				partenzaDa = (StanzaBloccata)(this.nome2stanza.get(stanzaDa));
				partenzaDa.setDirezioneBloccata(Direzione.valueOf(dir));
			}
			catch (ClassCastException e) {
				check(false, "Stanza normale non può essere bloccata");
			}
		}
		else {
			check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
			Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
			Stanza arrivoA = this.nome2stanza.get(nomeA);
			partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir), arrivoA);
			arrivoA.impostaStanzaAdiacente(Direzione.opposta(Direzione.valueOf(dir)), partenzaDa); // AGGIUNTO PER CREARE LE USCITE INVERSE
		}
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}

	public Map<String, AbstractPersonaggio> getNome2personaggio() {
		return nome2personaggio;
	}
}

