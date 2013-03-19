import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;


public class Voice_Receive_Message extends SimpleBehaviour{

	Agent agent = new Agent();
	
	public Voice_Receive_Message(Agent a){
		agent = a;
	}

	public static final String RCV_MSG_KEY = "received";
	boolean finished = false;
	
	public void action() {
		ACLMessage msg = agent.blockingReceive();
		
		if(msg!=null){
			getDataStore().put(RCV_MSG_KEY, msg.getContent());
			finished = true;
			System.out.println("VOICE AGENT HAS RECEIVED REQUEST FOR NEW PART");
		}else{
			block();
		}
		
	}

	public boolean done() {
		return finished;
	}

}
