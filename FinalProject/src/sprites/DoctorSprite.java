package sprites;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class DoctorSprite extends Sprite {
	private static final int leftBorder = 3, vertBorder = 4; // magic numbers for the sheet
	private static final int MAX_FRAMES = 6; // more magic numbers
	private static final int width = 40, height = 50; // width and height of a single frame
	
	private static BufferedImage sheet; // the sprite sheet
	private State previousState; // the last state of the frog

	// TODO 11: talk about FrogSprite
	public DoctorSprite(){
		if (sheet == null) // load the sprite sheet into memory
			try{
				sheet = ImageIO.read(new File("DRSprite.png"));
			}catch(Exception e){};
		previousState = State.MOVING_DOWN; // what is this for?
	}
	
	@Override
	public void setState(State state){ // why is this being overridden?
		previousState = getState();
		super.setState(state);
	}

	@Override
	public Image getImage() {
		// TODO 12: finish implementing getImage in Frog. 
		
		if (sheet == null)
			return null;
		
		int row = 0, col = frame, offset = 3;
		
		// Unlike explosion, we have to consider the sprite's different states
		switch(getState()){
		
		}
	
		/*
		 * This is a bit of hard-coding because certain 
		 * frames in the sheet are thinner than others
		 */
		if (row == 3 && (col == 4 || col == 1))
			offset = 4;
		
		frame = (frame + 1) % MAX_FRAMES; // increment frame
		
		// get the subimage of the frame from the row and column
		BufferedImage temp = sheet.getSubimage(leftBorder + col * width, row * (height + vertBorder), width - offset, height);
		
		// Up-scaling the frame by 2x
		return temp;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}
}
