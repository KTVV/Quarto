package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel spielname;
	private JButton spielst, spielbe;
	private JSpielButton[][] steinfeld;
	private JSpielButton[][] spielfeld;
	private JTextField spieler1, spieler2, momentanerSpieler;
	private JSpielButton clickedStein;
	private Quarto quarto;
	private final ActionListener spielfeldClickedListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			spieldfeldClicked((JSpielButton) e.getSource());
		}
	};

	public void init() {
		this.setSize(800, 700);																	//Spielfeld
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JPanel pane = new JPanel(new BorderLayout());		
		JPanel head = new JPanel(new GridLayout(2, 2));
		pane.add("North", head);
		
		Image img = Toolkit.getDefaultToolkit().getImage("src/src/Quarto!-Icon.jpg");			//Icon
		this.setIconImage(img);
		
		initSpielButtons();
		initSpielfeld();
		steinfeld = new JSpielButton[4][4];
		setztSpieler(spieler1);
	}

	private void setztSpieler(JTextField spieler) {												//Spieler wechsel (Farbe)
		if (momentanerSpieler != null) {
			momentanerSpieler.setBackground(Color.WHITE);
		}
		momentanerSpieler = spieler;
		momentanerSpieler.setBackground(Color.ORANGE);
	}

	private void initSpielButtons() {
		spielname = new JLabel("Quarto!"); 														// Ã¼berschrift
		spielname.setSize(100, 50);
		spielname.setLocation(375, 10);
		spielname.setFont(new Font( "Vivaldi", Font.BOLD, 28));
		this.add(spielname);

		spielst = new JButton("Spiel neu starten");												//Spiel starten Button
		spielst.setSize(200, 50);
		spielst.setLocation(350, 60);
		spielst.addActionListener(this);
		this.add(spielst);

		spielbe = new JButton("Spiel beenden");													//Spiel beenden Button
		spielbe.setSize(200, 50);
		spielbe.setLocation(550, 60);
		spielbe.addActionListener(this);
		this.add(spielbe);

		spieler1 = new JTextField("Spieler 1"); 												// Spieler 1
		spieler1.setSize(100, 50);
		spieler1.setLocation(10, 200);
		this.add(spieler1);

		spieler2 = new JTextField("Spieler 2"); 												// Spieler 2
		spieler2.setSize(100, 50);
		spieler2.setLocation(650, 200);
		this.add(spieler2);

		clickedStein = new JSpielButton();														//Auswahlfeld
		clickedStein.setSize(75, 75);
		clickedStein.setLocation(100, 60);
		clickedStein.setVisible(false);
		this.add(clickedStein);

	}

	public void naechsterSpieler() {															//wechselt Spieler
		if (momentanerSpieler == spieler1) {
			setztSpieler(spieler2);
		} else {
			setztSpieler(spieler1);
		}
	}

	private void initSpielfeld() {																//Spielfeld erstellen

		spielfeld = new JSpielButton[4][4];
		int y = 150, x = 250;

		for (int zeile = 0; zeile < spielfeld.length; zeile++) {
			for (int reihe = 0; reihe < spielfeld[zeile].length; reihe++) {
				spielfeld[zeile][reihe] = new JSpielButton(zeile, reihe);
				spielfeld[zeile][reihe].setSize(75, 75);
				spielfeld[zeile][reihe].setLocation(x + 80 * reihe, y + 80 * zeile);
				spielfeld[zeile][reihe].addActionListener(spielfeldClickedListener);
				this.add(spielfeld[zeile][reihe]);
			}
		}
	}

	public void addSpielFeldStein(int zeile, int reihe, Icon icon) {							//erzeugt Spielsteinfelder
		int y1 = 500, x1 = 80;
		steinfeld[zeile][reihe] = new JSpielButton(zeile, reihe, icon);
		JButton stein = steinfeld[zeile][reihe];
		stein.setSize(75, 75);
		this.add(stein);
		stein.addActionListener(this);
		reihe = (reihe + zeile * 4) % 8;
		zeile = (zeile > 1 ? 1 : 0);
		stein.setLocation(x1 + 80 * reihe, y1 + 80 * zeile);
	}

	@Override
	public void actionPerformed(ActionEvent e) {												//Funktion der Buttons "Spiel starten" "Spiel beenden"
		JButton button = (JButton) e.getSource();
		if (button == spielbe) {
			quarto.exitGame();
		} else if (button == spielst) {
			quarto.spielZurücksetzten();
		} else {
			this.steinfeldClicked((JSpielButton) button);
		}
	}

	private void spieldfeldClicked(JSpielButton button) {										//ermittelt Zielfeld und setzt Stein
		if (clickedStein.isVisible() && button.getIcon() == null) {
			button.setIcon(clickedStein.getIcon());
			clickedStein.setVisible(false);
			clickedStein.setIcon(null);
			quarto.onSpielfeldClicked(button.getZeile(), button.getReihe(), 
					clickedStein.getZeile(), clickedStein.getReihe());
		}
	}

	private void steinfeldClicked(JSpielButton button) {										//ermittelt ausgewÃ¼hlten Spielstein
		if (!clickedStein.isVisible()) {
			button.setVisible(false);
			clickedStein.setZeile(button.getZeile());
			clickedStein.setReihe(button.getReihe());
			clickedStein.setVisible(true);
			clickedStein.setIcon(button.getIcon());
			quarto.onSteinfeldClicked();
		}
	}

	public JTextField getMomentanerSpieler() {
		return momentanerSpieler;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public void spielZurücksetzten() {															//Spiel neu starten
		for (JButton[] steinReihe : spielfeld) {
			for (JButton stein : steinReihe) {
				this.remove(stein);
			}
		}
		for (JButton[] steinReihe : steinfeld) {
			for (JButton stein : steinReihe) {
				this.remove(stein);
			}
		}
		clickedStein.setIcon(null);
		clickedStein.setVisible(false);
		initSpielfeld();
	}
}