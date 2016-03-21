package apsus.game.model;

import java.awt.Rectangle;
import java.util.Random;

import apsus.framework.game.main.Game;

public class Ball {
	
	private int x,y,width,height,velX,velY;
	private Rectangle rect;
	private Game game;
	private static Random rand = new Random();
	
	public static int getRandIntBetween(int loBound, int upBound){
		return rand.nextInt(upBound - loBound) + loBound;
	}
	public static int getRandInt(int upBound){
		return rand.nextInt(upBound);
	}
	public Ball(int x, int y, int width, int height) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = 5;
		this.velY = getRandIntBetween(-5, 5);
		this.rect = new Rectangle(x, y, width, height);
	}
	
	public void setGame(Game game){
		this.game = game;
	}
	public void update(){
		x += velX;
		y += velY;
		correctYCollisions();
		updateRect();
		
	}
	private void correctYCollisions(){
		if( y < 0){
			y = 0;
		}
		if(y+height > game.GAME_HEIGHT)
			y = game.GAME_HEIGHT - height;
		velY = -velY;
		
	}
	private void updateRect(){
		rect.setBounds(x,y,width,height);
	}
	
	public void onCollideWidth(Paddle p){
		if(x < game.GAME_WIDTH/2){
			this.x = p.getX() + p.getWidth();
		}
		else
		{
			x =p.getX() - this.width;
		}
		velX = -velX;
		velY += getRandIntBetween(-3, 5);
	}
	
	public boolean isDeadToLeft(){
		return x < 0;
	}
	public boolean isDeadToRight(){
		return x + this.width > 800;
	}
	public void reset(){
		this.x = game.GAME_WIDTH/2;
		this.y = game.GAME_HEIGHT/2;
		velX = 5;
		velY = getRandIntBetween(-4, 5);
	}
	public int getX(){return this.x;}
	
	public int getY(){return this.y; }
	
	public int getWidth(){return this.width;}
	
	public int getHeight(){return this.height;}
	
	public Rectangle getRect(){return this.rect;}
	
}
