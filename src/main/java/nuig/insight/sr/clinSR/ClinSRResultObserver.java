package nuig.insight.sr.clinSR;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import sr.core.triple_based_reasoner.TimeStampedTriple;
import sr.core.triple_based_reasoner.Triple;

public class ClinSRResultObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		for(Object ob : (Set)arg){
			System.out.println("Answer set  has " + ((Set)ob).size() + " triples at "  +System.currentTimeMillis());
			for(Object temp : (Set)ob){
				Triple t = ((TimeStampedTriple)temp).getTriple();
				System.out.println(t.getSubject() +"\t" + t.getPredicate() + "\t" + t.getObject() + "\t" + ((TimeStampedTriple)temp).getTimestamp());
			}
		}	
	}
}
