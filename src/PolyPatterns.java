import jm.JMC;
import jm.music.data.*;
import jm.midi.*;
import jm.util.*;
public final class PolyPatterns implements JMC{

	static double[] pattern = new double[5];
	// Set up the rhythm patterns
	static double[] pattern0 = {1.0, 0.5, 0.5, 1.5, 0.5};
        static double[] pattern1 = {0.5, 0.5, 1.5, 0.5, 1.0};
        static double[] pattern2 = {2.0, 0.5, 0.5, 0.5, 0.5};
        static double[] pattern3 = {1.5, 0.5, 1.0, 0.5, 0.5};
            
    	public static void setPattern() {
        	int x = (int)(Math.random()*4);
            
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
                	    	System.out.println("Switch out of range");
               		    	System.exit(0); // end the program
		}
	}
	public static void main(String[] args){
        	Score score = new Score("JMDemo - Polyphonic Rhythm Patterns");
                Part inst = new Part("Drums", 0, 9);
                Phrase phr = new Phrase(0.0);
                Phrase phr2 = new Phrase(0.25);
                Phrase phr3 = new Phrase(0.5);  

                // create three phrases of eight patterns of four beats each.
                for(short i=0;i<8;i++){
                	// snare patterns
                      setPattern();
              
                      for (short j=0; j<pattern.length; j++) {
                      	Note note = new Note((int)(38), pattern[j]);
                              phr.addNote(note);
                      }

                     	//hihat patterns
                      setPattern();
                    
                      for (short j=0; j<pattern.length; j++) {
                      	Note note = new Note((int)(42), pattern[j]);
                              phr2.addNote(note);
                      }
                         
                      //clap patterns
                      setPattern();
                    
                      for (short j=0; j<pattern.length; j++) {
      			Note note = new Note((int)(39), pattern[j]);
                      	phr3.addNote(note);
                      }
                 }
        		Note note = new Note((int)(49), 4.0);
        		phr.addNote(note);
               	inst.addPhrase(phr);
                inst.addPhrase(phr2);
                inst.addPhrase(phr3);
                score.addPart(inst);
               
                System.out.println(score.toString());
                Write.midi(score, "PolyphonicRandomRhythm.midi");
	}
}
            
