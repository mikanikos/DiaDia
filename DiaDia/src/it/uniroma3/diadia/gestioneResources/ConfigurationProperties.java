package it.uniroma3.diadia.gestioneResources;

import java.io.*;
import java.util.Properties;

import java.lang.ClassLoader;

public class ConfigurationProperties {

	//FILE PROPERTIES
	private static String fileProperties="diadia.properties";

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.setProperty("cfu_iniziali", "15");
		prop.setProperty("peso_max_borsa", "10");
		prop.setProperty("numero_livelli", "2");
		prop.setProperty("elenco_comandi", "guarda, vai, prendi, posa, interagisci, saluta, regala, aiuto, fine");
		prop.setProperty("messaggio_benvenuto", ""+
				"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissà!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.");
		
		prop.store(new FileWriter("diadia.properties"),
				"Configurazione del gioco DIADIA");
		prop.storeToXML(new FileOutputStream("diadia.xml"),
				"Configurazione del gioco DIADIA");
	}

	public static String getString(String chiave) {
		ClassLoader loader = ConfigurationProperties.class.getClassLoader();
		String valore="";
		Properties prop = new Properties();
		try {
			prop.load(loader.getResourceAsStream(fileProperties));
			valore = prop.getProperty(chiave);
		} catch(IOException e) {
			System.out.println("ERRORE NELLA LETTURA DEL FILE DIADIA.PROPERTIES NELLA CHIAVE: " + chiave + "\nERRORE: " + e.getMessage());
		}
		return valore ;
	}

}