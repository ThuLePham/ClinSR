package nuig.insight.sr.example;

import nuig.insight.sr.clinSR.ClinSREngine;
import nuig.insight.sr.clinSR.ClinSRResultObserver;

public class HelloWorld {

	public static void main(String[] args) {
		/**
		 * 1. ClinSREngine using clingo for performing reasoning. Therefore, clingo has to be install first. 
		 * 2. Change clingo path at config.properties file in /src/main/resources
		 */
		
		
		
		String query = 
				"#input rdf_type/2."
				+ "#input uniben_worksFor/2."
				+ "#prefix rdf : <http://www.w3.org/1999/02/22-rdf-syntax-ns#>."
				+ "#prefix uniben : <http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#>."
				+ "#from stream <http://lubm.org#universities1> [TIME 3s STEP 2s]"
				+ "#from stream <http://lubm.org#universities2> [TIME 5s STEP 4s]."
				+ "rdf_type(X,\"uniben_Professor\") :- rdf_type(X, \"uniben_FullProfessor\")."
				+ "#show rdf_type/2.";
		
		
		//Start reasoner
		ClinSREngine reasoner = new ClinSREngine();
		
		//create stream
		LubmExampleStream stream1 = new LubmExampleStream("http://lubm.org#universities1");
		
		LubmExampleStream stream2 = new LubmExampleStream("http://lubm.org#universities2");


		
		
		//register input stream
		reasoner.registerStream(stream1);
		reasoner.registerStream(stream2);
		
		
		//register query (before register stream)
		reasoner.registerQuery("lubm_query", query);
		
		//register observer to get results
		reasoner.registerResultObserver(new ClinSRResultObserver());
		
		
		//start stream
		new Thread(stream1).start();
		new Thread(stream2).start();
		
		
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		reasoner.unregisterStream("http://lubm.org#universities1");
//		reasoner.unregisterStream("http://lubm.org#universities2");
//		
		

	}

}
