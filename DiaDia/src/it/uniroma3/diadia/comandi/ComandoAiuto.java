package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gestioneResources.ConfigurationProperties;
import it.uniroma3.diadia.gioco.Partita;

public class ComandoAiuto extends AbstractComando {

	static final private String elencoComandi = ConfigurationProperties.getString("elenco_comandi");
	/**
	 * stampa informazioni di aiuto sui comandi
	 */
	@Override
	public String esegui(Partita partita) {
		return elencoComandi;
	}
}
