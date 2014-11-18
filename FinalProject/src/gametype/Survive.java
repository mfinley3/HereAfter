package gametype;

public class Survive implements GameTypeInterface{

	private int turnsNeeded;
	
	public Survive(int turnsToSurvive){
		turnsNeeded = turnsToSurvive;
	}
	
	@Override
	public boolean CheckWinCondition(Object ob) {
		// TODO Auto-generated method stub
		return (int)ob >= turnsNeeded;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return "Monsters are closing in on you. \n"
				+ "Help will come, but you have to stay alive until it arrives."
				+ "Survive for " +turnsNeeded+ " turns against the respawning horde."
				+ "Best of luck";
	}

}
