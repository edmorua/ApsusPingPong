package apsus.game.model;

import java.awt.Rectangle;

import apsus.framework.game.main.Game;

/**
 * Clase de las paletas de los jugadores
 * @author Apsus
 *
 */


public class Paddle {
	private int x,y, width, height, velY, velX;
	private Rectangle rect;
	private  int move_speed_y;
	private  int move_speed_x;
	private Game game;
	
	public Paddle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.move_speed_x = 5;
		this.move_speed_y = 5;
		this.rect = new Rectangle(x, y, width, height);
		this.velX = 0;
		this.velY = 0;
		
	}
	public void setGame(Game game){
		this.game = game;
	}
	/**
	 * incrementa la velocidad en el eje X por 1
	 */
	public void increaseSpeedX(){
		this.move_speed_x +=1;
	}
	/**
	 * Incrementa la velocidad en el eje Y por 1
	 */
	public void increaseSpeedY(){
		this.move_speed_y +=1;
	}
	public int getX(){return this.x;}
	
	public int getY(){return this.y;}
	
	public int getWidth(){return this.width;}
	
	public int getHeight(){return this.height;}
	
	public Rectangle getRect(){return this.rect;}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public void setRect(Rectangle rect){
		this.rect = rect;
	}
	public void update(){
		y += velY;
		x+= velX;
		if(y < 0)
			y=0;
		if(x < 0)
			x = 0;
		if(y+height > game.GAME_HEIGHT)
			y = game.GAME_HEIGHT-height;
		if(x + width > game.GAME_WIDTH)
			x = game.GAME_WIDTH-this.width;
		
		this.rect.setBounds(x,y,width,height);
	}
	public void accelUp(){
		velY = -move_speed_y;
	}
	public void accelDown(){
		velY = move_speed_y;
	}
	public void accelLeft(){
		velX = -move_speed_x;
	}
	public void accelRight(){
		velX= move_speed_x;
	}
	public void stop(){
		velY = 0;
		velX = 0;
	}
}
