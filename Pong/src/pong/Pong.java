package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pong implements ActionListener, KeyListener {
	
	public static Pong pong;
	public int width = 800, height = 500;
	
	public Renderer renderer;
	
	public Paddle p1;
	public Paddle p2;
	public Ball b;
	
	Boolean up1, up2, thisStatus = false;
	
	public Pong() {
		Timer timer = new Timer(20,this);
		JFrame jframe = new JFrame("Pong");
		
		renderer = new Renderer();
		
		jframe.setVisible(true);
		jframe.setSize(width, height + 20);
		jframe.setLocation(300, 200);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		jframe.add(renderer);
		
		start();
		timer.start();	
	}
	
	public void start(){
		p1 = new Paddle(this,10);
		p2 = new Paddle(this, width - p1.width- 10);
		b = new Ball(this);
	}
	public void ai(){
		if(p1.y + p1.height/2 < b.y)
			p1.y += 8;
		if(p1.y + p1.height/2 > b.y)
			p1.y -= 8;
		
		if (p1.y < 0)
			p1.y = 0;
		if(p1.y + p1.height  > height)
			p1.y = height - p1.height;
		
	}
	

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		p1.render(g);
		p2.render(g);
		b.render(g);
		g.drawString(b.point1 + "|" + b.point2, width/2 - 10, 20);
		if(thisStatus == false)
			g.drawString("Paused", this.width/2 - 20, this.height/2 - 50);
	}
	private void update() {
		if(thisStatus == true){
			if(up1 != null)
				p1.move(up1);
			if(up2 != null)
				p2.move(up2);
				b.Collision();
				b.Move();
				ai();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		update();
		renderer.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		if (id == KeyEvent.VK_UP){
			up2 = true;
		}
		if (id == KeyEvent.VK_DOWN){
			up2 = false;
		}
		if(id == KeyEvent.VK_SPACE){
			thisStatus ^= true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();
		if (id == KeyEvent.VK_W || id == KeyEvent.VK_S){
			up1 = null;
		}
		if (id == KeyEvent.VK_UP || id == KeyEvent.VK_DOWN){
			up2 = null;
		}

		
	}
	public static void main(String[] args){
		pong = new Pong();
	}
}
