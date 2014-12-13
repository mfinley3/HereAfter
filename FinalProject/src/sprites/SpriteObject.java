package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import sprites.Sprite;

public abstract class SpriteObject {
	public Point position;
	private Sprite sprite;
	private Image frame;
	private Timer t;
	
	protected SpriteObject(Sprite sprite, int x, int y, int delay){
		this.sprite = sprite;
		this.position = new Point(x, y);
		frame = sprite.getImage();
		
		t = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextFrame();
			}
		});
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setPosition(int x, int y){
		position.setLocation(x, y);
	}
	
	public void draw(Graphics g){
		if (sprite != null && !sprite.isFinished())  {
			g.drawImage(frame, position.x  - sprite.getWidth(), position.y - sprite.getHeight(), null);
			//nextFrame();
		}
	}
	
	public void start(){
		if (!t.isRunning())
			t.start();
		sprite.reset();
	}
	
	public void stop(){
		t.stop();
	}
	
	public void nextFrame(){
		frame = sprite.getImage();
	}
	
	public boolean isFinished(){
		return sprite.isFinished();
	}
}
