import java.io.IOException;
import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jm.music.data.Note;
import jm.music.data.Phrase;

public class Melody_Generation extends SimpleBehaviour{
	
	public static int startPitch = 14;
	public static Phrase voice = new Phrase();;
	public static Serializable_Part lead = new Serializable_Part("Lead voice",0,2);
	static ArrayList<Chord> mainChordStruct = new ArrayList<Chord>();
	private static Chord C = new Chord("C", 60, true);
	private static double rhythmValtoDate = 0;
	private static double maxMelodySize = 16.0;
	public boolean part_needed = false;
	public boolean turnoff = true;
	Agent agent = new Agent();
	
	/*
	 * Constructor
	 */
	public Melody_Generation(Agent a){
		agent = a;
	}
	
	/*
	 * returns a random integer value from 1(inclusive) to limit
	 * also checks how much is left until bar is full and adjust limit accordingly
	 */
	public int getRandomValue(int limit){
		int temp = 0;
		
		if(maxMelodySize-rhythmValtoDate < limit * 0.5)
			limit = (int)((maxMelodySize-rhythmValtoDate)/0.5);
		
		temp = (int)((Math.random() * 10 % limit) + 1);
		
		System.out.println("testRandom: " + temp);
		rhythmValtoDate += temp * 0.5;
		return temp;
	}
	
	public int fixPitch(int pitch){
		if(pitch>72)pitch-=12;
		if(pitch<48)pitch+=12;
		return pitch;
	}
	
	public void action(){
		
		int currPitch = startPitch;
		double rand;
		double rand1;
		
		//receive chord structure
		ACLMessage chordStruct = agent.blockingReceive();
		System.out.println("VOICE AGENT RECEIVED CHORDS");
		//receive 'generate new part' request
		/*ACLMessage incomingreq = agent.blockingReceive();
		if(incomingreq!=null){
			System.out.println("A NEW VOICE PART HAS BEEN REQUESTED "+incomingreq.getContent());
			part_needed = true;
		}*/
		
		int req = Integer.parseInt((String) getDataStore().get("received"));
		System.out.println("Am primit valoarea: " + req + "de la composer");
		if(req!=0){
			maxMelodySize = req;
			part_needed = true;
		}
				
		if(part_needed){
			if(chordStruct != null){
				try {
					mainChordStruct = (ArrayList<Chord>) chordStruct.getContentObject();
					} catch (UnreadableException e) {
						e.printStackTrace();
						}
				}
			mainChordStruct.add(0, C);
		
			for(short i=0;i<mainChordStruct.size();i++){
				System.out.println("Voice: "+ mainChordStruct.get(i).getRoot());
				}
		
			for(short j=0;j<mainChordStruct.size();j++){
				currPitch=mainChordStruct.get(j).getRoot();
				int currStartPitch = currPitch;
			
				voice.addNote(new Note(currPitch,getRandomValue(3)*0.5));
				
				while(maxMelodySize-rhythmValtoDate != 0){
					rand = Math.random();
					rand1 = Math.random();
			
					if(rand<0.25){
				
						currPitch=fixPitch(currPitch+8);
						System.out.println(currPitch);
						voice.addNote(new Note(currPitch,getRandomValue(3)*0.5));
						}else if(rand1 <0.5){
							currPitch=fixPitch(currPitch-2);
							System.out.println(currPitch);
							voice.addNote(new Note(fixPitch(currPitch),getRandomValue(3)*0.5));
							}else{
								currPitch=fixPitch(currPitch+1);
								System.out.println(currPitch);
								voice.addNote(new Note(fixPitch(currPitch),getRandomValue(3)*0.5));						
								}
					}
		
			}
			lead.addPhrase(voice);
			//lead.appendPhrase(voice.copy());
			
			//send voice Part to main agent
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(new AID("a1", AID.ISLOCALNAME));
			try {
				msg.setContentObject(lead);
				} catch (IOException e) {
					e.printStackTrace();
					}
			System.out.println("I AM SENDING A VOICE PART");
			agent.send(msg);
			//part_needed = false;
			}
		}
	
	public boolean done(){
		if(turnoff)
			return true;
		else return false;
	}

}
