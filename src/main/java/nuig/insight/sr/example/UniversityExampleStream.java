package nuig.insight.sr.example;

import java.util.Random;
import java.util.UUID;

import nuig.insight.sr.clinSR.ClinSRRDFStream;
import sr.core.triple_based_reasoner.TimeStampedTriple;
import sr.core.triple_based_reasoner.Triple;

public class UniversityExampleStream extends ClinSRRDFStream implements Runnable{

	public UniversityExampleStream(String streamId) {
		super(streamId);
	}

	@Override
	public void run() {
		
		Random r = new Random();
		while(true){
			
			
			String s = UUID.randomUUID().toString();
			String p = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
			String o = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#FullProfessor";
			TimeStampedTriple t = new TimeStampedTriple(new Triple(s,p,o), System.currentTimeMillis());
//			System.out.println(t.toString());
			this.put(t);
			
			String p1 = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#numberPapers";
			String o1 = "\""+r.nextInt(100)+"\"^^xsd:double";
			TimeStampedTriple t1 = new TimeStampedTriple(new Triple(s,p1,o1), System.currentTimeMillis());
//			System.out.println(t1.toString());
			this.put(t1);
			
			String p2 = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#worksFor";
			String o2 = "\"NUIG\"^^http://www.w3.org/2001/XMLSchema#string";
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
