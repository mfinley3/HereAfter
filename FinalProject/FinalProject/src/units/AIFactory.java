package units;

public class AIFactory {

	/**
	 * This creates the AlphaProtector {@link Unit}
	 * 
	 * @return A new AlphaProtector Unit with the given information
	 */
	public static Unit createAlphaProtector() {
		return new AlphaProtectorAI();
	}

	/**
	 * This creates the Runner {@link Unit}
	 * 
	 * @return A new Runner Unit with the given information
	 */
	public static Unit createRunner() {
		return new RunnerAI();
	}

	/**
	 * This creates the Spitter {@link Unit}
	 * 
	 * @return A new Spitter Unit with the given information
	 */
	public static Unit createSpitter() {
		return new SpitterAI();
	}

} // end of AIFactory
