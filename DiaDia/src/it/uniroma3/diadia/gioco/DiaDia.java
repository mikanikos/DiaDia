package it.uniroma3.diadia.gioco;

import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.gestioneResources.ConfigurationProperties;
import it.uniroma3.diadia.utente.InterfacciaUtenteConsole;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale, crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ConfigurationProperties.getString("messaggio_benvenuto");

	private Partita partita;
	private InterfacciaUtenteConsole interfaccia;

	public DiaDia() {
		this.partita = new Partita();
		this.interfaccia = new InterfacciaUtenteConsole();
	}

	public void gioca() {
		String istruzione; 
		this.interfaccia.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do {
			this.interfaccia.mostraMessaggio("1° livello\n");
			do		
				istruzione = this.interfaccia.prendiIstruzione();
			while (!processaIstruzione(istruzione));
			this.interfaccia.mostraMessaggio("Vuoi rigiocare? (Y/N)");
			istruzione = this.interfaccia.prendiIstruzione();
			this.partita = new Partita();
		}
		while(istruzione.equals("Y") || istruzione.equals("y"));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti 
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		System.out.println(comandoDaEseguire.esegui(this.partita));
		if (this.partita.livelloCompletato() && !this.partita.vinta()) {
			this.interfaccia.mostraMessaggio("\nLivello Completato");
			this.partita.nextLevel();
			this.interfaccia.mostraMessaggio(this.partita.getLivelloCorrente() + "° livello\n");
		}
		if (this.partita.vinta())
			this.interfaccia.mostraMessaggio("Hai vinto!");
		if (this.partita.persa() && !this.partita.vinta())
			this.interfaccia.mostraMessaggio("Hai perso! Hai esaurito tutti i cfu");
		return this.partita.isFinita();
	}   
	
	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}