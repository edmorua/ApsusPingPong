package apsus.framework.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Clase que va a manejar los recursos del juego, imagenes, sonidos, etc.
 * @author Apsus
 *
 */


public class Resources {
	
	public static BufferedImage welcome,iconimage,line;
	public static AudioClip hit;
	
	
	public static void load(){
		welcome = loadImage("welcome.png");
		iconimage = loadImage("iconimage.png");
		line = loadImage("line.png");
		hit = loadSound("hit.wav");
	}
	
	public static AudioClip loadSound(String filename){
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);
	}
	
	public static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try{
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (IOException e)
		{
			System.out.println("Error while reading : "+ filename);
			e.printStackTrace();
		}
		return img;
	}
}





