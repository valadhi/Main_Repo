import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.music.tools.*;
import jm.constants.Instruments;
import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Gen_Chords extends Agent{
	
	Gen_Chords_Behaviour beh = new Gen_Chords_Behaviour(this);
	//public reqPart req = new reqPart(this);
	
	public void setup(){
		
		addBehaviour(beh);
		//addBehaviour(req);
		
	}

}
