package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.gioco.Partita;

public class ComandoNonValido extends AbstractComando {

	@Override
	public String esegui(Partita partita) {
		return "Comando sconosciuto";
	}
	
}
