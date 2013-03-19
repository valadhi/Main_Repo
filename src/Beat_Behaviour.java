import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jm.music.data.Note;
import jm.music.data.Phrase;
import jm.music.data.Score;


public class Beat_Behaviour extends SimpleBehaviour{
	
	
	public static Score score = new Score("Drum Beat");
	public static Serializable_Part drums = new Serializable_Part("Drums",0,9);
	public static Phrase BD = new Phrase(0.0);
	public static Phrase SD = new Phrase(0.0);
	public static Phrase hihat = new Phrase(0.0);
	public static Note bass = new Note(36, 1);
	public static Note snare = new Note(38, 1);
	public static Note snare_triplet = new Note(38, 0.3333);
	public static Note snare_half = new Note(38, 0.375);
	public static Note[] drum_break = {snare_triplet, snare_triplet, snare_triplet};
	public static Note hi_hat_acc = new Note(42, 0.375,95);
	public static Note hi_hat = new Note(42, 0.375,60);
	public static Note hi_hat_rest = new Note(jm.music.data.Note.REST, 0.25);
	public static Note rest = new Note(jm.music.data.Note.REST, 1);
	Agent agent = new Agent();
	
	public Beat_Behaviour(Agent a){
		agent = a;
	}
	
	public void action(){
		
		score.setTempo(100);
	
		for(short i=0;i<32;i++){
			
			if(i%2==0){

					BD.addNote(bass);
					SD.addNote(rest);
				}else if((i+1)%16==0){
					SD.addNoteList(drum_break);
					BD.addNote(rest);
				}else if((i+1)%8==0){
					SD.addNote(snare_half);
					SD.addNote(hi_hat_rest);
					SD.addNote(snare_half);
					BD.addNote(rest);
				}else{
					SD.addNote(snare);
					BD.addNote(rest);
				}
			
		}
		for(int i=0;i<32;i++){
			hihat.addNote(hi_hat_acc);
			hihat.addNote(hi_hat_rest);
			hihat.addNote(hi_hat);
		}
		
		drums.addPhrase(BD);
		drums.addPhrase(SD);
		drums.addPhrase(hihat);
		drums.addPhrase(hihat);
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("a1", AID.ISLOCALNAME));
		
		
		try {
			msg.setContentObject(drums);
		} catch (IOException e) {
			e.printStackTrace();
		}
		agent.send(msg);

	}
	@Override
	public boolean done() {
		
		return true;
	}

}
