package src;

import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Quarto {

	private SpielStein[][] steinfeld;
	private Spielbrett spielBrett;
	private final UserInterface ui;
	private int angeklickterGewonnenButton;

	public Quarto(UserInterface ui) {
		this.ui = ui;
		ui.setQuarto(this);
		init();
	}

	public void init() {
		spielBrett = new Spielbrett();
		steinfeld = new SpielStein[4][4];
		for (int zeile = 0; zeile < steinfeld.length; zeile++) { // erstellt
																	// Steinstartfeld
			for (int reihe = 0; reihe < steinfeld[zeile].length; reihe++) {
				boolean istGruen = zeile < 2;
				boolean istKlein = zeile % 2 == 1;
				boolean istRund = reihe < 2;
				boolean istLoch = reihe % 2 == 1;
				steinfeld[zeile][reihe] = new SpielStein(istGruen, istRund,
						istLoch, istKlein);
				ImageIcon icon = steinfeld[zeile][reihe].getIcon();
				ui.addSpielFeldStein(zeile, reihe, icon);
			}
		}
	}

	public void exitGame() { // Methode Spiel verlassen
		System.exit(0);
	}

	public void onSpielfeldClicked(int zeileFeld, int reiheFeld, int zeile,
			int reihe) { // Methode setzt Spielstein und prÃ¼ft ob es einen
							// Sieger gibt
		SpielStein stein = steinfeld[zeile][reihe];
		spielBrett.setSpielStein(zeileFeld, reiheFeld, stein);
		
		if (spielBrett.istSpielBeendet()) {
			abfrageNachBeendet(ui.getMomentanerSpieler().getText() + " hat gewonnen");
		}

		if (spielBrett.isUnentschieden() ) {
			abfrageNachBeendet("UNENTSCHIEDEN");
		}
	}



	public void onSteinfeldClicked() { // Spieler wechsel
		ui.naechsterSpieler();
	}

	public void spielZurücksetzten() { // Methode Spiel neu starten
		ui.spielZurücksetzten();
		init();
	}
	
	private void abfrageNachBeendet(String text) {
		do {
			angeklickterGewonnenButton = JOptionPane
					.showConfirmDialog(
							ui,
							text + " \n Ja: neustarten|| Nein: Programm Schliessen",
							null, JOptionPane.YES_NO_OPTION);
		} while (angeklickterGewonnenButton == JOptionPane.CLOSED_OPTION);

		if (angeklickterGewonnenButton == JOptionPane.YES_OPTION) {
			ui.spielZurücksetzten();
			init();

		} else if (angeklickterGewonnenButton == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
}
