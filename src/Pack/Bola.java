package Pack;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.util.Random;

public class Bola implements Runnable  {
	int x, y, xDirection, yDirection;
	int difficulty = 5;
	//Puntaje
	int p1Score, p2Score;
	//Personajes

	Paddle p1 = new Paddle(130, 288, 1); // Posicion personaje 1 ( negro )
	Paddle p2 = new Paddle(830, 288, 2); // Posicion personaje 2 ( rojo )
	Rectangle bola;
	Rectangle goal1 = new Rectangle(52, 215, 5, 190);
	Rectangle goal2 = new Rectangle(910, 215, 5, 190);
	
	
	public Bola(int x, int y) {
		p1Score = p2Score = 0;
		this.x = x;
		this.y = y;
		Random r = new Random();
		
		int xrDir = r.nextInt(1);
		if(xrDir == 1);
		//xrDir--;							// comentando aqui la bola queda quieta al inicio
		setXDirection(xrDir);
		int yrDir = r.nextInt(1);
		if(yrDir == 1);
		//yrDir--;							// y aqui tambien
		setYDirection(yrDir);
		bola = new Rectangle(this.x, this.y, 17, 17);
		
	}
	public int chooseRandomDirection() {
		Random ran = new Random();
		int[] randDir = new int[3];
		randDir[0] = 1;
		randDir[1] = 0;
		randDir[2] = -1;
		int randChoice = ran.nextInt(3);
		return randDir[randChoice];
	}
	public void setXDirection(int xdir) {
		xDirection = xdir;
	}
	public void setYDirection(int ydir) {
		yDirection = ydir;
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(bola.x, bola.y, bola.width, bola.height);
		g.setColor(new Color(220, 220, 220));
		g.fillRect(goal1.x, goal1.y, goal1.width, goal1.height);
		g.setColor(new Color(220, 220, 220));
		g.fillRect(goal2.x, goal2.y, goal2.width, goal2.height);
	}
	public void colisiones() throws InterruptedException {
		
		if (bola.intersects(goal1)){
			
			
			
			}
		
		
		if (bola.intersects(goal1)){
			
			p2Score++;
			URL url = MainFoot.class.getResource("cartoon.wav");
			AudioClip clip = Applet.newAudioClip(url);
			clip.loop();
			
			Random r = new Random();
			int xrDir = r.nextInt(1);
			int yrDir = r.nextInt(1);
			if(xrDir == 1 && yrDir == 1);
										
			setXDirection(xrDir);
			setYDirection(yrDir);
			bola.x = 475;
			bola.y = 300;
			
			
		}
		
		
		if(bola.intersects(goal2)) {
			p1Score++;
			URL url = MainFoot.class.getResource("cartoon.wav");
			AudioClip clip = Applet.newAudioClip(url);
			clip.loop();
			Random r = new Random();
			int xrDir = r.nextInt(1);
			int yrDir = r.nextInt(1);
			if(xrDir == 1 && yrDir == 1);
			//xrDir--;							
			setXDirection(xrDir);
			setYDirection(yrDir);
			bola.x = 475;
			bola.y = 300;
			
		}
	
			
				
		if(bola.intersects(p1.paddle)) {
			setXDirection(+1);
			setYDirection(chooseRandomDirection());
			
		}
		
			
		if(bola.intersects(p2.paddle)) {
			setXDirection(-1);
			setYDirection(chooseRandomDirection());
			
		}
		
	
		
	}
	
	public void move() throws InterruptedException {
		colisiones(); //Chequea constantemente las colisiones
		bola.x += xDirection;
		bola.y += yDirection;
		//Rebote de la bola cuando encuentre un limite
		if(bola.x<=50) {
			setXDirection(+1);
			
			
		}
		if(bola.x>=910) {
			setXDirection(-1);
			 //Se suma el puntaje
			      		// Comentado para que no se reinicie la bola
			
		}
		if(bola.y<=25) {
			setYDirection(+1);
			
		}
		if(bola.y>=580) {
			setYDirection(-1);
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				move();
				
				Thread.sleep(difficulty);
				
			}
		}catch(Exception e) {}
	}
	public void setDifficulty(int diff) {
		difficulty = diff;
	}
}


/* para resolver el hecho de que la bola al ser colisionada solo va a una direccion se puede creae una variable 
llamada direccion que almacene la tecla pulsada y poner un condicion que dependiendo la direcion (basado en la tecla pulsada)
le indique a la pelota haci donde debe moverse */