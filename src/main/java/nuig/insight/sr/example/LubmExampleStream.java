package nuig.insight.sr.example;

import java.util.UUID;

import nuig.insight.sr.clinSR.ClinSRRDFStream;
import sr.core.triple_based_reasoner.TimeStampedTriple;
import sr.core.triple_based_reasoner.Triple;

public class LubmExampleStream extends ClinSRRDFStream implements Runnable{

	public LubmExampleStream(String streamId) {
		super(streamId);
	}

	@Override
	public void run() {
		
		while(true){
			
			
			String s = UUID.randomUUID().toString();
			String p = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
			String o = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#FullProfessor";
			TimeStampedTriple t = new TimeStampedTriple(new Triple(s,p,o), System.currentTimeMillis());
			this.put(t);
			
			String p1 = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#numberPapers";
			String o1 = "\"100.3\"^^xsd:double";
			TimeStampedTriple t1 = new TimeStampedTriple(new Triple(s,p1,o1), System.currentTimeMillis());
			this.put(t1);
			
			String p2 = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#name";
			String o2 = "\"Steve\"^^http://www.w3.org/2001/XMLSchema#string";
			TimeStampedTriple t2 = new TimeStampedTriple(new Triple(s,p2,o2), System.currentTimeMillis());
			this.put(t2);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}

}
