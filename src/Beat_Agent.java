import java.io.IOException;

import jade.core.behaviours.*;
import jade.core.*;
import jade.lang.acl.ACLMessage;
import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.music.tools.*;

public class Beat_Agent extends Agent{

	Beat_Behaviour beh = new Beat_Behaviour(this);
	
	public void setup(){
		
		addBehaviour(beh);
		
	}
	
	

}
