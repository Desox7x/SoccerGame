package Pack;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.TimerTask;
import java.applet.Applet;
import java.applet.Applet.*;
import java.applet.AudioClip;


public class MainFoot extends JFrame {
	int x, y, xDirection, yDirection, secondsPassed;
	Image dbImage;
	Graphics dbg;
	Image soccer;
	Image soccer1;
	Image gir;
	
	static Bola ball = new Bola(475, 300);
	
	
	Thread b1 = new Thread(ball);
    Thread p1 = new Thread(ball.p1);
    Thread p2 = new Thread(ball.p2);
    
    boolean hardDiff = false;
    static boolean gameStart = false;
    static boolean pauseGame = false;
    boolean startHover;
    boolean diffHover;
    boolean menuHover;
    int Tiempo = 0;
	// Botones de Menu
	Rectangle StarB = new Rectangle(60, 250, 100, 25);
	Rectangle diffB = new Rectangle(60, 300, 110, 25);
	Rectangle menucb = new Rectangle(430, 573, 110, 30);
	Rectangle dont = new Rectangle (60, 350, 113, 30);

	int Width1 = 960;
	int Height1 = 600;
	
	Dimension SizePantalla = new Dimension(Width1, Height1);
    
	public MainFoot() throws InterruptedException {
		
		Toolkit laventana = Toolkit.getDefaultToolkit();
		
		Image Icono = laventana.getImage("pig.png");
		setIconImage(Icono);
		ImageIcon i = new ImageIcon("campo 1.jpg");
		ImageIcon i1 = new ImageIcon("soccerball.jpg");
		ImageIcon i2 = new ImageIcon("girdog.png");
		soccer1 = i1.getImage();
		soccer = i.getImage();
		gir = i2.getImage();
		this.setSize(SizePantalla);
		this.setResizable(false);
		this.setTitle("Gir Soccer! v3");
		this.addKeyListener(new AL());
		this.addMouseListener(new MR());
		this.addMouseMotionListener(new MR());
		this.Time();
	
	}
			
	public void startgame() {
		gameStart = true;
		b1.start();
		p1.start();
		p2.start();
		secondsPassed++;
		
	}
	public void pausegame() {
		pauseGame = true;
		b1.stop();
		p1.stop();
		p2.stop();
	}
	public void Time() {
		Timer timer = new Timer (1000, new ActionListener () 
		{ 	
		    public void actionPerformed(ActionEvent e) 
		    { 
		    	if(Tiempo<90)
		    		Tiempo+= 1;
		    	
		     } 
		}); 
		

		timer.start();
	}
	
	public void setXDirection(int xdir) {
		xDirection = xdir; //Para el movimiento en x
	}
	
	public void setYDirection(int ydir) {
		yDirection = ydir; //Para el movimiento en y
	}
	
	
	
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) { //Eventos con el teclado
			ball.p1.keyPressed(e);
			ball.p2.keyPressed(e);
			
			if(e.getKeyCode()== e.VK_P) {
				pausegame();
			}
			
			
		}
		public void keyReleased(KeyEvent e) {
			ball.p1.keyReleased(e);
			ball.p2.keyReleased(e);
			
			
		}
	}
	public class MR extends MouseAdapter{
		@Override
		public void mouseMoved(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			if(mx > StarB.x && mx < StarB.x+StarB.width &&
			   my > StarB.y && my < StarB.y+StarB.height) {
				startHover = true;
			}else
				startHover = false;
			if(mx > diffB.x && mx < diffB.x+diffB.width &&
				my > diffB.y && my < diffB.y+diffB.height) {
				diffHover = true;
			}else
				diffHover = false;
			if(mx > menucb.x && mx < menucb.x+menucb.width &&
			   my > menucb.y && my < menucb.y+menucb.height) {
				menuHover = true;
			}else
				menuHover = false;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			if(mx > StarB.x && mx < StarB.x+StarB.width &&
			   my > StarB.y && my < StarB.y+StarB.height) {
				startgame();
			}
			if(mx > diffB.x && mx < diffB.x+diffB.width &&
			   my > diffB.y && my < diffB.y+diffB.height) {
				if(!hardDiff) {
					ball.setDifficulty(3);
					hardDiff = true;
				}
				else if(hardDiff) {
					ball.setDifficulty(5);
					hardDiff = false;
				}
				else {
					ball.setDifficulty(1);
					hardDiff = true;
				}
			}
			if(mx > menucb.x && mx < menucb.x+menucb.width &&
			   my > menucb.y && my < menucb.y+menucb.height) {
				gameStart = false;
				
			}
			if(mx > dont.x && mx < dont.x+dont.width &&
			   my > dont.y && my < dont.y+dont.height) {
				if(!hardDiff) {
					ball.setDifficulty(1);
					startgame();
				}
			}
			
		}
	}
	
	
	

	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight()); //Se crea una imagen de la pantalla
		dbg = dbImage.getGraphics(); //Objetemos los graficos de esa pantalla y los almacena en dgb
		
		paintComponent(dbg);
		
		 //Se pintan estos graficos con paintComponent
		g.drawImage(dbImage, 0, 0, this); //Se plasman estas imagenes en la pantalla
		//Asi el objeto no deja un trazo cuando se desplaza
		
		
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		
		
		// Menu
		if(!gameStart) {
			

			g.drawImage(soccer1, 5, 10, this);
			g.drawImage(gir, 530, 30, this);
			g.setFont(new Font("Elephant",Font.BOLD, 48));
			g.setColor(Color.WHITE);
			g.drawString("Gir Soccer!", 50, 200);
			if(!startHover)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.CYAN);
			g.fillRect(StarB.x, StarB.y, StarB.width, StarB.height);
			g.setFont(new Font("Arial", Font.ITALIC, 20));
			g.setColor(Color.WHITE);
			g.drawString("Start", StarB.x+22, StarB.y+20);
			if(!diffHover)
				g.setColor(Color.RED);
			else
				g.setColor(Color.ORANGE);
			g.fillRect(diffB.x, diffB.y, diffB.width, diffB.height);
			g.setFont(new Font("Arial", Font.ITALIC, 16));
			g.setColor(Color.GREEN);
			g.drawString("Difficulty:  ",diffB.x,diffB.y+20);
			if(!hardDiff) {
				g.setColor(Color.BLUE);
				g.drawString("Easy", diffB.x+65, diffB.y+20);
			}
			else{
				g.setColor(Color.BLACK);
				g.drawString("Hard", diffB.x+65, diffB.y+20);
			}
			g.setColor(Color.BLACK);
			g.fillRect(dont.x, dont.y, dont.width, dont.height);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.drawString("Don't touch this", dont.x+1, dont.y+20);
			
	
		//Juego	
		}else {
		g.drawImage(soccer, 3, 10, this);	
		ball.draw(g);
		ball.p1.draw(g);
		ball.p2.draw(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Goles: "+ball.p1Score, 90, 50);
		g.drawString("Goles: "+ball.p2Score, 760, 50);
		if(!menuHover) {
			g.setColor(new Color(12, 88, 30));
		}else{
			g.setColor(new Color(19, 154, 50));
		}
		//Volver al menu
		g.fillRect(menucb.x, menucb.y, menucb.width, menucb.height);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.setColor(Color.BLACK);
		g.drawString("Volver al menu", menucb.x+3, menucb.y+18);
		//Timer
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Timer: "+Tiempo , 423,50 );
		if(Tiempo >= 90) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Elephant", Font.BOLD, 36));
			g.drawString("Game Over" , 370,300 );
			b1.stop();
			p1.stop();
			p2.stop();
			
			if(ball.p1Score > ball.p2Score) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Elephant", Font.BOLD, 22));
			g.drawString("Player 1 wins" , 400,350 );
			//System.out.println("Gano el 1");
			}
			if(ball.p1Score < ball.p2Score) {
			g.setColor(Color.RED);
			g.setFont(new Font("Elephant", Font.BOLD, 22));
			g.drawString("Player 2 wins" , 400,350 );	
			//System.out.println("Gano el 2");
			}
			if(ball.p1Score == ball.p2Score) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Elephant", Font.BOLD, 18));
			g.drawString("Empate" , 440,350 );	
			//System.out.println("Empate ");
		}
			
	}
	
		
}
		
		
		repaint();
		
		
	}

		
		
		public static void main(String[] args) throws InterruptedException {
		MainFoot M = new MainFoot();
		M.setVisible(true);
		M.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		URL url = MainFoot.class.getResource("linkinpark.wav");
		AudioClip clip = Applet.newAudioClip(url);
		clip.loop();
		Thread.sleep(300000);
		
		}
	
			

}








