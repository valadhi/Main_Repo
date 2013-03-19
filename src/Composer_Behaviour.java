import java.lang.reflect.Method;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.View;
import jm.util.Write;


public class Composer_Behaviour extends SimpleBehaviour {

	private static Score score = new Score();
	int counter = 0;
	Agent agent = new Agent();
	Part chords_part = new Part(0,0);
	private static Part drum = new Part("Drums",0,9) ;
	private static Part voice = new Part("Lead voice",0,2);
	
	public Composer_Behaviour(Agent a){
		agent = a;
	}
	public void action() {
		System.out.println("THE COMPOSER AGENT IS EXECUTING...." + counter);
		score.setTempo(120);
		//agent.getClass().getMethod("addBehaviour", null);
		
		//sending request to Voice agent for a new part
		ACLMessage reqvoice = new ACLMessage(ACLMessage.REQUEST);
		reqvoice.addReceiver(new AID("a4", AID.ISLOCALNAME));
		reqvoice.setContent("32");
		agent.send(reqvoice);
		System.out.println("COMPOSER AGENT SENT REQUEST FOR VOICE");
		
		//receive chords
		ACLMessage chords = agent.blockingReceive();
		System.out.println("COMPOSER AGENT RECEIVED CHORDS");
		if(chords != null) {
			try {
				chords_part = (Part) chords.getContentObject();
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		}
		
		
		//receive & append beat part
		ACLMessage beatContent = agent.blockingReceive();
		System.out.println("COMPOSER AGENT RECEIVED DRUMS");
		if(beatContent != null) {
			try {
				drum = (Part) beatContent.getContentObject();
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		}
		//receive  & append voice part
		ACLMessage voiceContent = agent.blockingReceive();
		System.out.println("COMPOSER AGENT RECEIVED VOICE");
		if(voiceContent != null) {
			try {
				//voice[voicesize] = new Part("Lead voice",0,2);
				voice = (Part) voiceContent.getContentObject();
				System.out.println("VOICE PART APPENDED");
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		}
		
		voice.setInstrument(56);
		score.addPart(chords_part);
		score.addPart(drum);
		score.addPart(voice);
		
		counter++;
	}

	@Override
	public boolean done() {
		if(counter>0){
			View.show(score);
			Write.midi(score, "Random_Chords+beat.mid");
			return true;
		}
		else return false;
	}

}
