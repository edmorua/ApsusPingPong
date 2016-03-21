package apsus.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import apsus.framework.game.main.Game;
import apsus.framework.game.main.Resources;
/**
 * Clase que cargara todos los recursos al inicializar el juego
 * @author Aurom
 *
 */
public class LoadState extends State {

	@Override
	public void init(Game game) {
		Resources.load(); //solo entraremos en este estado al inicializar el juego
		System.out.println("Loaded Successfully");
	}

	@Override
	public void update() {
		this.setCurrentState(new MenuState()); //inmediatamente despues de cargar los recursos pasamos al menu
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void onClick(MouseEvent e) {
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		
	}
	
	
}
