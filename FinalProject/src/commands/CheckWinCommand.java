package commands;

import gametype.GameTypeInterface;

/**
 * The Class CheckWinCommand.
 */
public class CheckWinCommand extends Command<GameTypeInterface>{

	/** The ob. */
	private Object ob;
	
	/**
	 * Instantiates a new check win command.
	 */
	public CheckWinCommand(){
		
	}
	
	/* (non-Javadoc)
	 * @see commands.Command#execute(java.lang.Object)
	 */
	@Override
	public void execute(GameTypeInterface executeOn) {
		executeOn.CheckWinCondition(ob);
	}

}
