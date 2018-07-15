package it.uniroma3.diadia.utente;

import java.util.Scanner;

public class InterfacciaUtenteConsole implements InterfaceUtente {

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public String prendiIstruzione() {
		String istruzione;
		Scanner scannerDiLinee = new Scanner(System.in);
		istruzione = scannerDiLinee.nextLine();
		return istruzione;
	}
	
}
