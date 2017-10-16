package nuig.insight.sr.clinSR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Properties;

import be.ugent.idlab.rspservice.common.enumerations.QueryType;
import be.ugent.idlab.rspservice.common.exceptions.RuleSetRegistrationException;
import be.ugent.idlab.rspservice.common.interfaces.Query;
import be.ugent.idlab.rspservice.common.interfaces.RSPEngine;
import be.ugent.idlab.rspservice.common.interfaces.RuleSet;
import be.ugent.idlab.rspservice.common.interfaces.Stream;
import sr.core.atom_based_reasoner.WindowInfo;
import sr.core.triple_based_reasoner.TripleClingoReasoner;

/**
 * 
 * @author thulepham
 *
 */
public class ClinSREngine implements RSPEngine {

    
    private TripleClingoReasoner engine;
    

    public ClinSREngine() {
        this.engine = new TripleClingoReasoner(false);
        this.engine.setClingoUri(getClingoURI());
    }


    @Override
    public void initialize() throws Exception {
        this.engine = new TripleClingoReasoner(false);
        this.engine.setClingoUri(getClingoURI());
    }

    @Override
    public Object getRSPEngine() {
        return engine;
    }

    @Override
    public Stream registerStream(String streamName, String uri) {
    	/*
    	 * streamName is name of stream as declared in query
    	 * Ex: from stream <http://lubm.org#universities> [TIME 3s STEP 2s].
		 * Ex: streamName = http://lubm.org#universities
    	 */
    	ClinSRRDFStream stream = new ClinSRRDFStream(streamName);
    	this.engine.registerStream(stream);

        return stream;

    }
    
    
    public Stream registerStream(ClinSRRDFStream stream) {
    	/*
    	 * streamName is name of stream as declared in query
    	 * Ex: from stream <http://lubm.org#universities> [TIME 3s STEP 2s].
		 * Ex: streamName = http://lubm.org#universities
    	 */
    	
    	this.engine.registerStream(stream);

        return stream;

    }
    
    
    public Observer registerResultObserver(Observer o){
    	this.engine.addObserver(o);
    	return o;
    }

    public Object unregisterStream(String streamName) {
    	try{
    		 ClinSRRDFStream stream  = (ClinSRRDFStream) this.engine.getStreams().get(streamName);
    		 if(stream != null){
    			 this.engine.unregisterStream(stream);
    		 }
    	}catch(Exception e){
    		throw e;
    	}
       
        return null;
    }

    public Object getStream(String streamName) {
        return null;
    }


    @Override
    public Object unregisterQuery(String queryID) {
        return null;
    }

    @Override
    public Object getAllQueries() {
        return null;
    }

    @Override
    public Object stopQuery(String queryID) {
        return null;
    }

    @Override
    public Object startQuery(String queryID) {
        return null;
    }

    @Override
    public void feedRDFStream(Stream rdfStream, String m) {
        rdfStream.feedRDFStream(m);
    }

    @Override
    public void addStaticKnowledge(String iri, String url, Boolean isDefault, String serialization) {

    }

    @Override
    public void deleteStaticKnowledge(String url) {

    }

    @Override
    public void execUpdateQueryOverStaticKnowledge(String query) {

    }


	@Override
	public Query registerQuery(String queryName, QueryType queryType, String queryBody, List<String> streams,
			List<String> graphs, String tbox_location, String rule_set) throws Exception {
		ClinSRQuery query = new ClinSRQuery(queryName, queryBody);
		return query;
	}
	
	public Query registerQuery(String queryName, String queryBody)  {
		ClinSRQuery query = new ClinSRQuery(queryName, queryBody);
		this.engine.registerCbQuery(queryName, queryBody);
		return query;
	}



	@Override
	public RuleSet registerRuleSet(String id, String body) throws RuleSetRegistrationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object unregisterRuleSet(String id) {
		// TODO Auto-generated method stub
		return null;
	}

    public String getClingoURI(){
    	
    	Properties prop = new Properties();
		File in = new File("src/main/resources/config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(in);
			prop.load(fis);
			fis.close();
			return prop.getProperty("clingo");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Configure for Clingo path!!!");
			
		}
    }

}