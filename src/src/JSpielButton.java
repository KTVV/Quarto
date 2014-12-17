package src;

import javax.swing.Icon;
import javax.swing.JButton;

public class JSpielButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int zeile;
	private int reihe;

	public JSpielButton(int zeile, int reihe) {
		super("");
		this.zeile = zeile;
		this.reihe = reihe;
	}

	public JSpielButton(int zeile, int reihe, Icon icon) {
		super("", icon);
		this.zeile = zeile;
		this.reihe = reihe;
	}

	public JSpielButton() {
		this.zeile = -1;
		this.reihe = -1;
	}

	public int getReihe() {
		return reihe;
	}

	public void setReihe(int reihe) {
		this.reihe = reihe;
	}

	public int getZeile() {
		return zeile;
	}

	public void setZeile(int zeile) {
		this.zeile = zeile;
	}
}
