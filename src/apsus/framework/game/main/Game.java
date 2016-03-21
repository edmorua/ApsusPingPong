package apsus.framework.game.main;

/**
 * Clase para manejar el juego, este extendera la clase JPanel y aqui se creaara
 * toda la ventana grafica del juego, sus dimensiones, y su color de fondo,
 * tambien sera el que vaya cambiando los estados del juego en un hilo diferente al del main
 * @see JPanel
 * @see Runnable
 * @author Apsus
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import apsus.framework.util.InputHandler;
import apsus.game.state.LoadState;
import apsus.game.state.State;


@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable {
	
	public final int GAME_WIDTH = 800;
	public final int GAME_HEIGHT = 450;
	private Image gameImage;
	private Thread gameThread;
	private volatile boolean running;
	private volatile State currentState;
	private InputHandler inputHandler;
	private int gameWidth;
	private int gameHeight;
	
	/**
	 * Constructor por defecto, utiliza las dimensiones de los atributos staticos GAME_WIDTH y GAME_HEIGHT
	 */
	public Game() {
		this.gameWidth = GAME_WIDTH;
		this.gameHeight = GAME_HEIGHT;
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocus();	
	}
	/**
	 * Constructor que genera la ventana de juego dado los parametros
	 * @param width Anchura de la ventana
	 * @param height Altura de la ventana
	 */
	public Game(int width, int height)
	{
		this.gameWidth = width;
		this.gameHeight = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	/**
	 * Inicializa el hilo del juego
	 */
	private void initGame(){
		this.running = true;
		this.gameThread = new Thread(this,"Game Thread");
		this.gameThread.start();
	}
	/**
	 * Prepara la imagen, 
	 * si es nula significa que apenas empezo y genera una image con las dimensiones de la ventana
	 * si no fue nula, destruye la imagen en pantalla y genera una vacia para poder dar el cambio del videojuego
	 */
	private void prepareGameImage(){
		if(this.gameImage == null)
			this.gameImage = createImage(gameWidth, gameHeight);
		Graphics g = gameImage.getGraphics();
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	private void exit(){
		running = false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.gameImage == null)
			return;
		g.drawImage(this.gameImage, 0, 0, null);
	}
	
	private void initInput(){
		this.inputHandler = new InputHandler();
		this.addKeyListener(inputHandler);
		this.addMouseListener(inputHandler);
	}
	
	public void setCurrentState(State newState){
		System.gc(); //borramos manualmente los objetos innecesarios al estilo de C
		newState.init(this);//inicializamos el estado nuevo
		this.currentState = newState; //actualizamo el estado
		this.inputHandler.setCurrentState(this.currentState);
		this.currentState = newState;
	}
	
	@Override
	public void addNotify(){
		super.addNotify();//este metodo se ejecuta al empezar el main
		this.initInput();
		this.setCurrentState(new LoadState());//primer estado en el que nos encontramos sera el de carga
		initGame();
	}
	@Override
	public void run() {
		while(running)
		{
			this.currentState.update();
			this.prepareGameImage();
			this.currentState.render(gameImage.getGraphics());
			this.repaint();
			try{
				Thread.sleep(14);//para generar 60 cambios de imagen por segundo (fps)
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		System.exit(0); //cerramos el hilo
	}
	public int getWidth(){return this.gameWidth;}
	
	public int getHeight(){return this.gameHeight;}

}
