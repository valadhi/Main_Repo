import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jade.core.*;
import jade.core.behaviours.*;

public class Chords extends Agent{
	
	private static Score s = new Score("Chords");
	private static Part p = new Part("Piano", 0, 0);
	
	private static void secondInversion(int rootPitch) {
		// build the chord from the rootPitch 
	 	int[] pitchArray = new int[4];
	 	pitchArray[0] = rootPitch;
	 	pitchArray[1] = rootPitch + 4;
	 	pitchArray[2] = rootPitch - 2;
	 	pitchArray[3] = rootPitch - 5;
	 	//add chord to the part
		CPhrase chord = new CPhrase();
		chord.addChord(pitchArray, 1);
		p.addCPhrase(chord);
	}		
	
	private static void ending(int rootPitch) {
		// build the chord from the rootPitch 
	 	int[] pitchArray = new int[4];
	 	pitchArray[0] = rootPitch;
	 	pitchArray[1] = rootPitch + 4;
	 	pitchArray[2] = rootPitch + 7;
	 	pitchArray[3] = rootPitch + 12;
	 	//add chord to the part
		CPhrase chord = new CPhrase();
		chord.addChord(pitchArray, 4);
		p.addCPhrase(chord);
	}
	
	private static void rootPosition(int rootPitch) {
		// build the chord from the rootPitch 
	 	int[] pitchArray = new int[4];
	 	pitchArray[0] = rootPitch;
	 	pitchArray[1] = rootPitch + 4;
	 	pitchArray[2] = rootPitch + 7;
	 	pitchArray[3] = rootPitch + 10;
	 	//add chord to the part
		CPhrase chord = new CPhrase();
		chord.addChord(pitchArray, 1);
		p.addCPhrase(chord);
	}
	
	public void setup(){
		addBehaviour(new SimpleBehaviour(){
			
			public void action(){
				int rootPitch = 60; //set start note to middle C
				for (int i = 0; i < 6; i++) {
					secondInversion(rootPitch);
					rootPitch -= 7;
					rootPosition(rootPitch);
					rootPitch += 5;
					}
		
		//add a final chord
		ending(rootPitch);
		
		//pack the part into a score
		s.addPart(p);
		
		//display the music
		View.show(s);
		
		// write the score to a MIDIfile
		Write.midi(s, "Chords.mid");
			}
		
		public boolean done(){
			return true;
		}
	});
	}
}
