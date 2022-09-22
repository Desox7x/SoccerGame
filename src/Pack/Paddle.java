package Pack;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable {
	int x, y, xDirection, yDirection, id;
	Rectangle paddle;
	public Paddle(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
		paddle = new Rectangle(x, y, 20, 40);
		
	}
	public void keyPressed(KeyEvent e) {
		switch(id) {
			default:
				System.out.println("AAAAAA");
				break;
			case 1:
				if(e.getKeyCode()== e.VK_W) {
					setYDirection(-1);
				}
				if(e.getKeyCode()== e.VK_S) {
					setYDirection(+1);
				}
				if(e.getKeyCode()== e.VK_A) {
					setXDirection(-1);
				}
				if(e.getKeyCode()== e.VK_D) {
					setXDirection(+1);
				}
				break;
			case 2:
				if(e.getKeyCode()== e.VK_UP) {
					setYDirection(-1);
				}
				if(e.getKeyCode()== e.VK_DOWN) {
					setYDirection(+1);
				}
				if(e.getKeyCode()== e.VK_LEFT) {
					setXDirection(-1);
				}
				if(e.getKeyCode()== e.VK_RIGHT) {
					setXDirection(+1);
				}
				break;
		}
		
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
			default:
				System.out.println("AAAAAA");
				break;
			case 1:
				if(e.getKeyCode()== e.VK_W) {
					setYDirection(0);
				}
				if(e.getKeyCode()== e.VK_S) {
					setYDirection(0);
				}
				if(e.getKeyCode()== e.VK_A) {
					setXDirection(0);
				}
				if(e.getKeyCode()== e.VK_D) {
					setXDirection(0);
				}
				break;
			case 2:
				if(e.getKeyCode()== e.VK_UP) {
					setYDirection(0);
				}
				if(e.getKeyCode()== e.VK_DOWN) {
					setYDirection(0);
				}
				if(e.getKeyCode()== e.VK_LEFT) {
					setXDirection(0);
				}
				if(e.getKeyCode()== e.VK_RIGHT) {
					setXDirection(0);
				}
				break;
		}
		
	}
	public void setXDirection(int xdir) {
		xDirection = xdir;
	}
	public void setYDirection (int ydir) {
		yDirection = ydir;
	}
	public void move() {
		paddle.x += xDirection;
		paddle.y += yDirection;
		if(paddle.x <= 55)
			paddle.x = 55;
		if (paddle.x >= 895)
			paddle.x = 895;
		if (paddle.y <= 25)
			paddle.y = 25;
		if (paddle.y >= 555)
			paddle.y = 555;
			
	}
	
	public void draw(Graphics g) {
		switch (id) {
		default:
			System.out.println("AAAAAA");
			break;
		case 1:
			g.setColor(Color.BLACK);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;
		case 2:
			g.setColor(Color.RED);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
			break;
		}
	}
	public void run() {
		try {
			while(true) {
				move();
				Thread.sleep(4);
			}
			
		}catch(Exception e) {}
	}

}
