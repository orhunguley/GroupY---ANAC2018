import java.util.HashMap;

import negotiator.AgentID;

public class OpponentModelHolder {
	private HashMap<AgentID,OpponentModel> agentModels;
	public OpponentModelHolder() {
		agentModels=new HashMap<AgentID,OpponentModel>();
			
		
	}
	public HashMap<AgentID, OpponentModel> getAgentModels() {
		return agentModels;
	}
	
	
	
}
