package units;

public class AIFactory {

	/**
	 * This creates the AlphaProtector {@link Unit}
	 * 
	 * @return A new AlphaProtector Unit with the given information
	 */
	public static Unit createAlphaProtector(double difficulty) {
		return new AlphaProtectorAI(difficulty);
	}

	/**
	 * This creates the Runner {@link Unit}
	 * 
	 * @return A new Runner Unit with the given information
	 */
	public static Unit createRunner(double difficulty) {
		return new RunnerAI(difficulty);
	}

	/**
	 * This creates the Spitter {@link Unit}
	 * 
	 * @return A new Spitter Unit with the given information
	 */
	public static Unit createSpitter(double difficulty) {
		return new SpitterAI(difficulty);
	}

} // end of AIFactory
