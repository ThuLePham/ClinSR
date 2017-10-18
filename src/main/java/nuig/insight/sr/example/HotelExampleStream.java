package nuig.insight.sr.example;

import java.util.Random;
import java.util.UUID;

import nuig.insight.sr.clinSR.ClinSRRDFStream;
import sr.core.triple_based_reasoner.TimeStampedTriple;
import sr.core.triple_based_reasoner.Triple;

public class HotelExampleStream extends ClinSRRDFStream implements Runnable{

	public HotelExampleStream(String streamId) {
		super(streamId);
	}

	@Override
	public void run() {
		
		while(true){
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String s = UUID.randomUUID().toString();
			String p = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
			String o = "http://example.org/hotel";
			TimeStampedTriple t = new TimeStampedTriple(new Triple(s,p,o), System.currentTimeMillis());
			this.put(t);
			
			String p1 = "http://example.org/star";
			
			String o1 = String.format("\"%d\"^^xsd:double", new Random().nextInt(6));
			TimeStampedTriple t1 = new TimeStampedTriple(new Triple(s,p1,o1), System.currentTimeMillis());
			this.put(t1);
			
//			if(new Random().nextInt(1000)%2 == 0){
				String p2 = "http://example.org/onStreet";
				String o2 = "\"SmallStreet\"^^http://www.w3.org/2001/XMLSchema#string";
				TimeStampedTriple t2 = new TimeStampedTriple(new Triple(s,p2,o2), System.currentTimeMillis());
				this.put(t2);
//			}
			
			
			String p3 = "http://example.org/cost";
			String o3 = String.format("\"%d\"^^xsd:double", new Random().nextInt(500));
			TimeStampedTriple t3 = new TimeStampedTriple(new Triple(s,p3,o3), System.currentTimeMillis());
			this.put(t3);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
