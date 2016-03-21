package apsus.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;



import apsus.framework.game.main.Game;
import apsus.framework.game.main.Resources;
import apsus.game.model.Ball;
import apsus.game.model.Paddle;

/**
 * Clase que manejara los eventos del juego mientras se esta jugando
 * @author Apsus
 *
 */
public class PlayState extends State {
	
	private  final int PAD_WIDTH = 15;
	private  final int 	PAD_HEIGHT = 80;
	private  final int BALL_LENGHT = 20;
	
	private Paddle pLeft, pRight;
	private Ball gameBall;
	private int scorePlayer1;
	private int scorePlayer2;
	private Font scoreFont;
	private Game game;
	@Override
	public void init(Game game) {
		this.game = game;
		this.pLeft = new Paddle(0, (this.game.GAME_HEIGHT- PAD_HEIGHT)/2, PAD_WIDTH,PAD_HEIGHT);
		this.pLeft.setGame(this.game);
		this.pRight = new Paddle(this.game.GAME_WIDTH-PAD_WIDTH,(this.game.GAME_HEIGHT- PAD_HEIGHT)/2,PAD_WIDTH,PAD_HEIGHT);
		this.pRight.setGame(this.game);
		this.scorePlayer2 = 0;
		this.scoreFont = new Font("SansSerif",Font.BOLD,25);
		this.gameBall = new Ball(400 ,225, BALL_LENGHT, BALL_LENGHT);
		this.gameBall.setGame(this.game);
		System.out.println("Succes");
	}
	
	private boolean ballCollides(Paddle p){
		return this.gameBall.getRect().intersects(p.getRect());
	}

	@Override
	public void update() {
		this.pLeft.update();
		this.pRight.update();
		this.gameBall.update();
		if(ballCollides(pLeft)){
			this.gameBall.onCollideWidth(pLeft);
		}
		if(ballCollides(pRight))
			this.gameBall.onCollideWidth(pRight);
		
		if(this.gameBall.isDeadToLeft()){
			this.scorePlayer2++;
			Resources.hit.play();
			this.gameBall.reset();
		}
		if(this.gameBall.isDeadToRight())
		{
			this.scorePlayer1++;
			Resources.hit.play();
			this.gameBall.reset();
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(14,106,209));
		g.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
		g.setColor(new Color(200, 23,28));
		g.fillRect(this.game.getWidth()/2, 0, this.game.getWidth()/2,this.game.getHeight());
		g.drawImage(Resources.line, (this.game.getWidth()/2), 0, null);
		
		g.setColor(Color.WHITE);
		g.fillRect(this.pLeft.getX(),this.pLeft.getY(),this.pLeft.getWidth(), this.pLeft.getHeight());
		g.fillRect(this.pRight.getX(), this.pRight.getY(), this.pRight.getWidth(), this.pRight.getHeight());
		
		g.drawRect(this.gameBall.getX(),this.gameBall.getY(), this.gameBall.getWidth()	,this.gameBall.getHeight());
		g.setFont(scoreFont);
		g.drawString(this.scorePlayer1 + "", (this.game.getWidth()/2)- 50, 40);
		g.drawString(this.scorePlayer2 + "",	(this.game.getWidth()/2) + 50, 40);
		
	}

	@Override
	public void onClick(MouseEvent e) {
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			this.pLeft.accelUp();
		}
		if(e.getKeyCode() == KeyEvent.VK_S)
		{
			this.pLeft.accelDown();
		}
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			this.pLeft.accelLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_D)
		{
			this.pLeft.accelRight();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
			this.pRight.accelUp();
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
			this.pRight.accelDown();
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			this.pRight.accelLeft();
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			this.pRight.accelRight();
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
			this.pLeft.stop();
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
			this.pRight.stop();
		
	}

}
