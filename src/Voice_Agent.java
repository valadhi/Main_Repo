import java.io.IOException;
import java.util.ArrayList;

import jade.core.behaviours.*;
import jade.core.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jm.music.data.*;
import jm.util.*;

public class Voice_Agent extends Agent{

	Voice_Behaviour ag_beh = new Voice_Behaviour(this);
	Voice_Receive_Message b1 = new Voice_Receive_Message(this);
	SimpleBehaviour b2 = new Melody_Generation(this);
	
	public void setup(){
		
		b1.setDataStore(b2.getDataStore());
		addBehaviour(b1);
		addBehaviour(b2);
	}

}
