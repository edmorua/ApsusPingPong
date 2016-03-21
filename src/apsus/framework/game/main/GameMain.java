package apsus.framework.game.main;

/**
 * Clase main del juego
 * @author Aurom
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GameMain {
	public static final String Game_TITLE = "ApsusPingPong";
	public static Game game;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame(Game_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		game = new Game();
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setIconImage(Resources.iconimage);
	}
}
