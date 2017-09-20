package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	public int x, y, width = 20, height = 120;
	
	public Paddle(Pong pong,int x){
		
		this.x = x;
		
		this.y = pong.height/2 - this.height/2;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
	}

	public void move(boolean i) {
		
		int speed = 10;
		if(i){
			if(y - speed >= 0){
				y -= speed;
			}
		}
		else if(y + height + speed <= Pong.pong.height){
			y += speed;
		}
	}
}
