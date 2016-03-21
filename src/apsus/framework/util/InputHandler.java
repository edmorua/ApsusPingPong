package apsus.framework.util;

/**
 * Clase para manejar los cambios de estados en el juego y 
 * las acciones que ocurren al atrapar un evento del teclado o el raton
 * @author Apsus
 * @see KeyListener
 * @see MouseListener
 */
import java.awt.event.*;

import apsus.game.state.State;

public class InputHandler implements KeyListener, MouseListener {
	
	private State currentState;// cuando encuentra un evento se utiliza la accion en el estado que se encuentra el juego
	
	/**
	 * Metodo para cambiar de estados.
	 * @param newState
	 */
	public void setCurrentState(State newState){
		this.currentState = newState;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		this.currentState.onClick(e);  
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		this.currentState.onKeyPress(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		this.currentState.onKeyRelease(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
