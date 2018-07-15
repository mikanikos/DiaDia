package it.uniroma3.diadia.comandi;

public interface FabbricaDiComandi {

	/**
	 * costruisce il comando in base all'istruzione
	 * @param istruzione è l'istruzione che deve essere eseguita
	 * @return comando è il comando creato per l'istruzione immessa 
	 */
	public AbstractComando costruisciComando(String istruzione);

}
