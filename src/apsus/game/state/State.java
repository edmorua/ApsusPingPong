package apsus.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import apsus.framework.game.main.Game;
import apsus.framework.game.main.GameMain;

/**
 * Clase abstracta que maneja el estado en el que se encuentra el juego
 * @author Aurom
 *
 */

public abstract class State {
	
	public abstract void init(Game game);
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick(MouseEvent e);
	
	public abstract void  onKeyPress(KeyEvent e);
	
	public abstract void  onKeyRelease(KeyEvent e);
	
	/**
	 * Asigna el nuevo estado al juego
	 * @param newState
	 */
	public void setCurrentState(State newState)
	{
		GameMain.game.setCurrentState(newState);
	}
}
