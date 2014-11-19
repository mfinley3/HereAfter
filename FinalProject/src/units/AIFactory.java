package units;

public class AIFactory {

	/**
	 * This creates the AlphaProtector {@link Unit}
	 * 
	 * @return A new AlphaProtector Unit with the given information
	 */
	public static Unit createAlphaProtector() {
		return new AlphaProtectorAI(.5);
	}

	/**
	 * This creates the Runner {@link Unit}
	 * 
	 * @return A new Runner Unit with the given information
	 */
	public static Unit createRunner() {
		return new RunnerAI(.5);
	}

	/**
	 * This creates the Spitter {@link Unit}
	 * 
	 * @return A new Spitter Unit with the given information
	 */
	public static Unit createSpitter() {
		return new SpitterAI(.5);
	}

} // end of AIFactory
