import jm.JMC;
import jm.music.data.*;
import jm.midi.*;
import jm.util.*;

/**
 * A short example which generates a random snare drum patterns in 4/4 time
 * and writes to a MIDI file called randomPatterns.mid
 * @author Andrew Brown
 */
public final class RandomPatterns implements JMC{
	public static void main(String[] args){
		Score score = new Score("JMDemo - Random Patterns");
		Part inst = new Part("Snare", 0, 9);
		Phrase phr = new Phrase(0.0); 
		int x;
		double[] pattern = new double[5];
		double[] pattern0 = {1.0, 0.5, 0.5, 1.5, 0.5};
		double[] pattern1 = {0.5, 0.5, 1.5, 0.5, 1.0};
		double[] pattern2 = {2.0, 0.5, 0.5, 0.5, 0.5};
		double[] pattern3 = {1.5, 0.5, 1.0, 0.5, 0.5};

		// create a phrase of eight patterns of four beats each.
		for(int i=0;i<8;i++){
			// choose one of the patterns at random
			x = (int)(Math.random()*4);
			System.out.println("x = " + x);
		
			switch (x) {
		
			case 0:
				pattern = pattern0;
				break;
			case 1:
				pattern = pattern1;
				break;
			case 2:
				pattern = pattern2;
				break;
			case 3:
				pattern = pattern3;
				break;
			default:
			    System.out.println("Random number out of range");
			    System.exit(0); // end the program now
			}
			// create notes for the chosen pattern to the phrase
			for (short j=0; j<pattern.length; j++) {
				Note note = new Note(38, pattern[j]);
				phr.addNote(note);
			}
		}
		
		// finish with a crash cymbal
		Note note = new Note(49, 4.0);
		phr.addNote(note);
				
		// add the phrase to an instrument and that to a score
		inst.addPhrase(phr);
		score.addPart(inst);
		
		// create a MIDI file of the score
		Write.midi(score, "RandomPatterns.mid");
	}
}