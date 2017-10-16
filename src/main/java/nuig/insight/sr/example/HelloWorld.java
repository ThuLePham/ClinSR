package nuig.insight.sr.example;

import nuig.insight.sr.clinSR.ClinSREngine;
import nuig.insight.sr.clinSR.ClinSRResultObserver;

public class HelloWorld {

	public static void main(String[] args) {
		String query = 
				"#input rdf_type/2."
				+ "#input uniben_worksFor/2."
				+ "#prefix rdf : <http://www.w3.org/1999/02/22-rdf-syntax-ns#>."
				+ "#prefix uniben : <http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#>."
				+ "#from stream <http://lubm.org#universities> [TIME 3s STEP 2s]."
				+ "rdf_type(X,\"Profesor\") :- rdf_type(X, \"uniben_FullProfessor\")."
				+ "#show rdf_type/2.";
		
		
		//Start reasoner
		ClinSREngine reasoner = new ClinSREngine();
		
		//register query (before register stream)
		reasoner.registerQuery("lubm_query", query);
		
		
		//create stream
		LubmExampleStream stream = new LubmExampleStream("http://lubm.org#universities");
		
		//register input stream
		reasoner.registerStream(stream);
		
		//register observer to get results
		reasoner.registerResultObserver(new ClinSRResultObserver());
		
		
		//start stream
		new Thread(stream).start();
		
		

	}

}
