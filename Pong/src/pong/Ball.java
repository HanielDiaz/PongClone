package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	Random random = new Random();
	public int x , y, diameter = 30, point1 = 0, point2 = 0;
	public int xSpeed = random.nextInt(6) - 3, ySpeed = random.nextInt(4) - 2, count = 0;	
	
	public Ball(Pong pong){
		this.x = pong.width/2 - diameter/2;
		this.y = pong.height/2- diameter/2;
	}

	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillOval(x, y, diameter, diameter);	
		
		if(Pong.pong.b.x + this.diameter <= 0)
		{
			Pong.pong.thisStatus = false;
			this.x = Pong.pong.width/2 - diameter/2;
			this.y = Pong.pong.height/2- diameter/2;
			xSpeed = random.nextInt(6) - 3;
			ySpeed = random.nextInt(4) - 2;
			point2++;
		}
		else if(Pong.pong.b.x >= Pong.pong.width)
		{
			Pong.pong.thisStatus = false;
			this.x = Pong.pong.width/2 - diameter/2;
			this.y = Pong.pong.height/2- diameter/2;
			xSpeed = random.nextInt(6) - 3;
			ySpeed = random.nextInt(4) - 2;
			point1++;
		}
	}
	public void Collision(){
		if(Pong.pong.p1.x + Pong.pong.p1.width  >= x ){
			if(Pong.pong.p1.y <= y && Pong.pong.p1.y + Pong.pong.p1.height > y ){
				xSpeed = -xSpeed;
				count++;
				if(count % 3 == 0)
					xSpeed =  xSpeed + 4;
				if(count % 6 == 0)
					ySpeed = 2 * ySpeed;
			}
		}
		if(Pong.pong.p2.x - this.diameter <= x ){
			if(Pong.pong.p2.y <= y && Pong.pong.p2.y + Pong.pong.p2.height > y ){
				xSpeed = -xSpeed;
				count++;
				if(count % 3 == 0)
					xSpeed = xSpeed - 4;
				if(count % 6 == 0)
					ySpeed = 2 * ySpeed;
			}
		}
		if (Pong.pong.height - this.diameter <= y || y < 0 )
			ySpeed = -ySpeed;
	}
	
	public void Move(){
		while(xSpeed == 0 || xSpeed == 1 || xSpeed == -1)
			xSpeed = random.nextInt(6) - 3;
		while(ySpeed == 0 || xSpeed == 1 || xSpeed == -1)
			ySpeed = random.nextInt(4) - 2;
			
		
		this.x += this.xSpeed;
		this.y += this.ySpeed;
		
	}
}
