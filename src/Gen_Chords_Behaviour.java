import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jm.music.data.CPhrase;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.music.tools.Mod;
import jm.util.View;
import jm.util.Write;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Gen_Chords_Behaviour extends SimpleBehaviour {
	
	
	static double[] pattern = new double[5];
	static double[] pattern0 = {1.0, 0.5, 0.5, 1.5, 0.5};
    static double[] pattern1 = {0.5, 0.5, 1.5, 0.5, 1.0};
    static double[] pattern2 = {2.0, 0.5, 0.5, 0.5, 0.5};
    static double[] pattern3 = {1.5, 0.5, 1.0, 0.5, 0.5};
    private static int chord_Target = 4;
    static ArrayList<Chord> mainChordStruct = new ArrayList<Chord>();
	private static Part part = new Part(0,0);

	//private static int voicesize = 0;
	//private static Part[] voice = new Part[10];
	private static Chord C = new Chord("C", 60, true);
	private static Chord c = new Chord("c", 60, false);
	private static Chord D = new Chord("D", 62, true);
	private static Chord d = new Chord("d", 62, false);
	private static Chord E = new Chord("E", 64, true);
	private static Chord e = new Chord("e", 64, false);
	private static Chord F = new Chord("F", 65, true);
	private static Chord f = new Chord("f", 65, false);
	private static Chord G = new Chord("G", 67, true);
	private static Chord g = new Chord("g", 67, false);
	private static Chord A = new Chord("A", 69, true);
	private static Chord a = new Chord("a", 69, false);
	static Chord B = new Chord("B", 71, true);
	static Chord b = new Chord("b", 71, false);
	static Chord last_chord;
	Agent agent = new Agent();
	
	
	private static ArrayList<Chord> chord_array = new ArrayList<Chord>();
	
	
	public Gen_Chords_Behaviour(Agent a){
		agent = a;
	}
	/*
	 * choose one of the rhythmic patterns
	 */
	public static void choosePattern(){
		Random r = new Random();
		int x = (r.nextInt(3));
		
		switch(x){
		case 1:
			pattern = pattern0;
			break;
		case 2:
			pattern = pattern1;
			break;
		case 3:
			pattern = pattern2;
			break;
		case 4:
			pattern = pattern3;
			break;
			default:
				System.out.println("Choose Pattern switch out of range");
			
		}
	}
	/*
	 * builds a major chord given a root pitch
	 */
	private static void major(int rootPitch, double noteDuration){
		int[] arr = new int[3];
		arr[0] = rootPitch;
		arr[1] = rootPitch + 4;
		arr[2] = rootPitch + 7;
		CPhrase chord = new CPhrase();
		chord.addChord(arr, noteDuration);
		part.addCPhrase(chord);
		System.out.println(rootPitch + " major");
	}
	/*
	 * build a minor chord given a root pitch
	 */
	private static void minor(int rootPitch, double noteDuration){
		int[] arr = new int[3];
		arr[0] = rootPitch;
		arr[1] = rootPitch + 3;
		arr[2] = rootPitch + 7;
		CPhrase chord = new CPhrase();
		chord.addChord(arr, noteDuration);
		part.addCPhrase(chord);
		System.out.println(rootPitch + " minor");
	}
	
	private static void random(ArrayList<Chord> chords){
		Random r = new Random();
		int i = (r.nextInt(chords.size()));
		Chord tempChord = chords.get(i);
		
		if(mainChordStruct.size() < chord_Target)
			mainChordStruct.add(tempChord);
		
		choosePattern();
		for(short j=0;j<pattern.length;j++){
			if(tempChord.getType()){
				major(tempChord.getRoot(),pattern[j]);
				}else{
					minor(tempChord.getRoot(),pattern[j]);
					}
			}
		
		last_chord = chords.get(i);
		chord_array.clear();
		
	}
		
	
private static void next_Chord(Chord chord){
		
		if (chord.getType()){
			
			switch (chord.getRoot()){
			
			case 60 : //C
				chord_array.add(e);
				chord_array.add(F);
				chord_array.add(G);
				chord_array.add(g);
				random(chord_array);
				break; 
			case 62: //D
				chord_array.add(C);
				break;
			case 64 : //E
				chord_array.add(C);
				chord_array.add(f);
				chord_array.add(F);
				break;
				
			case 65 : //F
				chord_array.add(C);
				chord_array.add(d);
				chord_array.add(e);
				random(chord_array);
				break; 
				
			case 67 : //G
				chord_array.add(C);
				chord_array.add(a);
				random(chord_array);
				break; 				
			case 69 : //A
				chord_array.add(C);
				chord_array.add(E);
				chord_array.add(f);
				chord_array.add(g);
				chord_array.add(F);
				chord_array.add(G);
				chord_array.add(B);
				random(chord_array);
				break; 
			case 71 : //B
				chord_array.add(C);
				random(chord_array);
				break;
			}
		}else{
			switch (chord.getRoot()){
			case 60 : //c
				chord_array.add(f);
				chord_array.add(A);
				random(chord_array);
				break; 
			
			case 62 : //d
				chord_array.add(c);
				chord_array.add(e);
				chord_array.add(g);
				random(chord_array);

				break; 
			
			case 64 : //e
				chord_array.add(d);
				chord_array.add(a);
				chord_array.add(C);
				chord_array.add(F);
				random(chord_array);

				break; 
				
			case 65 : //f
				chord_array.add(c);
				chord_array.add(g);
				chord_array.add(G);
				random(chord_array);
				break; 
				
			case 67 : //g
				chord_array.add(c);
				chord_array.add(A);
				random(chord_array);
				break; 
				
			case 69 : //a
				chord_array.add(c);
				chord_array.add(d);
				chord_array.add(e);
				chord_array.add(C);
				chord_array.add(F);
				chord_array.add(G);
				random(chord_array);
				break;
				
			case 71 : //b
				chord_array.add(c);
				random(chord_array);
				break; 		
			}
		}
	}

	public void action() {
		
		int rootPitch = 60;
		
		choosePattern();
		for(short i = 0; i<pattern.length;i++){
			major(rootPitch,pattern[i]);
		}
		
		last_chord = C;
		for(int i=0;i<3;i++){
			next_Chord(last_chord);
		}
		
		part.setInstrument(1);
		//score.addPart(part);
		

		//send chord structure to voice agent 
		ACLMessage chordStruct = new ACLMessage(ACLMessage.INFORM);
		chordStruct.addReceiver(new AID("a4", AID.ISLOCALNAME));
		try {
			chordStruct.setContentObject(mainChordStruct);
		} catch (IOException e) {
			e.printStackTrace();
		}
		agent.send(chordStruct);
		
		//send chord structure to main composer agent 
		ACLMessage chordStruct1 = new ACLMessage(ACLMessage.INFORM);
		chordStruct1.addReceiver(new AID("a1", AID.ISLOCALNAME));
		try {
			chordStruct1.setContentObject(part);
		} catch (IOException e) {
			e.printStackTrace();
		}
		agent.send(chordStruct1);
		
		//send part containing chords to composer agent
		/*ACLMessage chords = new ACLMessage(ACLMessage.INFORM);
		chords.addReceiver(new AID("a1", AID.ISLOCALNAME));
		try{
			chords.setContentObject(part);
		} catch (IOException e){
			e.printStackTrace();
		}
		agent.send(chords);*/
		
		//send request for a new voice part
		/*ACLMessage reqpart = new ACLMessage(ACLMessage.INFORM);
		reqpart.addReceiver(new AID("a3", AID.ISLOCALNAME));
		reqpart.setContent("1");
		agent.send(reqpart);*/
		
		
		//shows root notes of generated chord sequence
		/*for(short i=0;i<mainChordStruct.size();i++){
			System.out.println("Generator: "+mainChordStruct.get(i).getRoot());
		}*/
		

		
		
		//Mod.repeat(score, 4);

	}

	public boolean done(){
		return true;
	}

}
