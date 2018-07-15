package it.uniroma3.diadia.ambienti;

public enum Direzione {
	
	nord, est, sud, ovest;

	public static Direzione opposta(Direzione direzione) {
		switch(direzione) {
		case nord: return sud;
		case est: return ovest;
		case sud: return nord;
		case ovest: return est;
		default: return null;
		}
	}
}