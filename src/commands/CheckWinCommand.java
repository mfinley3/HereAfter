package commands;

import gametype.GameTypeInterface;

public class CheckWinCommand extends Command<GameTypeInterface>{

	private Object ob;
	
	public CheckWinCommand(){
		
	}
	
	@Override
	public void execute(GameTypeInterface executeOn) {
		// TODO Auto-generated method stub
		executeOn.CheckWinCondition(ob);
	}

}
