package apsus.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import apsus.framework.game.main.Game;
import apsus.framework.game.main.Resources;
/**
 * Clase que manejara el juego mientras esta en el menu
 * @author Aurom
 *
 */
public class MenuState extends State {

	@Override
	public void init(Game game) {
		System.out.println("Entered menu");
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Resources.welcome, 0, 0, null); //dibujamos la imagen de bienvenida en pantalla sin ninguna alteracion
	}

	@Override
	public void onClick(MouseEvent e) {
		this.setCurrentState(new PlayState()); //Si hacemos click en cualquier parte de la ventana pasamos al estado de jugar
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		System.out.println("Tecla" + e.getKeyChar());
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		System.out.println("Tecla" + e.getKeyChar() + " soltada");
	}

}
