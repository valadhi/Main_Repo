import jm.JMC;
import jm.music.data.*;
import jm.util.Write;

/**
 * A short example which generates a random snare drum rhythm
 * and writes to a MIDI file called randomRhythm.mid
 * @author Andrew Brown
 */
public final class RandomRhythm implements JMC{
    public static void main(String[] args){
        Score score = new Score("JMDemo - Random Rhythm");
        Part inst = new Part("Snare", 0, 9);
        Phrase phr = new Phrase(0.0); 
        
        // create a phrase of random durations up to a semibreve in length.
        for(int i=0; i<24; i++){
                Note note = new Note(D2, Math.random()*4.0);
                phr.addNote(note);
        }
        
        // add the phrase to an instrument and that to a score
        inst.addPhrase(phr);
        score.addPart(inst);
        
        // create a MIDI file of the score
        Write.midi(score, "randomRhythm.mid");
    }
}