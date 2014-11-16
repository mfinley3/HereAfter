package units;

import java.awt.Color;
import java.awt.Point;


public class AIFactory {

	/**
	 * This creates the Rectangle {@link Shape}
	 * 
	 * @param start		the starting point of the shape.
	 * @param finish	the ending point of the shape.
	 * @param color		the color of the shape
	 * @return A new Rectangle Shape with the given information
	 */
	public static Unit createAlphaProtector() {
		return new AlphaProtectorAI();
	}

	/**
	 * This creates the Line {@link Shape}
	 * 
	 * @param start		the starting point of the shape.
	 * @param finish	the ending point of the shape.
	 * @param color		the color of the shape
	 * @return A new Line Shape with the given information
	 */
	public static Unit createRunner() {
		return new RunnerAI();
	}

	/**
	 * This creates the Oval {@link Shape}
	 * 
	 * @param start		the starting point of the shape.
	 * @param finish	the ending point of the shape.
	 * @param color		the color of the shape
	 * @return A new Oval Shape with the given information
	 */
	public static Unit createSpitter() {
		return new SpitterAI();
	}

} // end of AIFactory
