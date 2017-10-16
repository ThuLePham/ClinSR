package nuig.insight.sr.clinSR;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import sr.core.triple_based_reasoner.TimeStampedTriple;

public class ClinSRResultObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Number of answer sets = " + ((Set)arg).size());
		for(Object ob : (Set)arg){
			System.out.println("Answer set  has " + ((Set)ob).size() + " triples");
			for(Object temp : (Set)ob){
				System.out.println(((TimeStampedTriple)temp).toString());
			}
		}	
	}
}
