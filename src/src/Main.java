package src;

public class Main {

	public static void main(String[] args) {
		UserInterface q = new UserInterface();
		q.init();
		q.setVisible(true);
		new Quarto(q);
	}
}
