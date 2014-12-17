package src;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class SpielStein {

	private boolean istGruen;
	private boolean istRund;
	private boolean istLoch;
	private boolean istKlein;

	public SpielStein(boolean istGruen, boolean istRund, boolean istLoch, boolean istKlein) {
		this.istGruen = istGruen;
		this.istRund = istRund;
		this.istLoch = istLoch;
		this.istKlein = istKlein;
	}

	public ImageIcon getIcon() {
		String iconTitle = this.getIconTitle();
		URL iconUrl = getClass().getResource(iconTitle + ".jpg");
		ImageIcon icon = new ImageIcon(iconUrl);
		Image image = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		icon.setImage(image);
		return icon;
	}

	private String getIconTitle() {														//Bild auswählen
		String iconTitle = "spielsteine_";
		iconTitle += istGruen ? "green_" : "red_";
		iconTitle += istKlein ? "small_" : "big_";
		iconTitle += istRund ? "round_" : "rect_";
		iconTitle += istLoch ? "whole" : "normal";
		return iconTitle;
	}

	public boolean isIstGruen() {
		return istGruen;
	}

	public void setIstGruen(boolean istGruen) {
		this.istGruen = istGruen;
	}

	public boolean isIstRund() {
		return istRund;
	}

	public void setIstRund(boolean istRund) {
		this.istRund = istRund;
	}

	public boolean isIstLoch() {
		return istLoch;
	}

	public void setIstLoch(boolean istLoch) {
		this.istLoch = istLoch;
	}

	public boolean isIstKlein() {
		return istKlein;
	}

	public void setIstKlein(boolean istKlein) {
		this.istKlein = istKlein;
	}
}
