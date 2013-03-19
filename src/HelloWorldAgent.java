import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;


public class HelloWorldAgent extends Agent {

	public void setup() {
		System.out.println("Hello. My name is "+getLocalName());
		
		/*addBehaviour(new CyclicBehaviour() {
			public void action() {
				
				ACLMessage msgRx = receive();
				
				if (msgRx != null) {
					System.out.println(msgRx);
					ACLMessage msgTx = msgRx.createReply();
					msgTx.setContent("Hello!");
					send(msgTx);
					} else {
						block();
						}
				}
			});*/
		
		addBehaviour(new SimpleBehaviour() {
			public void action(){
				System.out.println("d");
			}
			public boolean done(){
				return true;
			}
			
		});
		}
	}