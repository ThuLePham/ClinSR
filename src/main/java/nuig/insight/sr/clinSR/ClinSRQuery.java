package nuig.insight.sr.clinSR;

import java.util.Collection;
import java.util.Map;

import be.ugent.idlab.rspservice.common.enumerations.QueryType;
import be.ugent.idlab.rspservice.common.enumerations.RSPComponentStatus;
import be.ugent.idlab.rspservice.common.interfaces.AbstractQueryResultProxy;
import be.ugent.idlab.rspservice.common.interfaces.Query;
import be.ugent.idlab.rspservice.common.interfaces.QueryResultObserverWrapper;
import be.ugent.idlab.rspservice.common.interfaces.RuleSet;
import sr.core.atom_based_reasoner.ClingoRuleSet;

/**
 * 
 * @author thulepham
 *
 */
public class ClinSRQuery implements Query{

    private ClingoRuleSet clinRuleset; 
    private String queryBody;

    public ClinSRQuery(
            String queryId, String body) {
    	this.queryBody = body;
    	this.clinRuleset = new ClingoRuleSet(queryId, this.queryBody);
 
    }
    
    public String getQueryBody() {
		return this.queryBody;
	}

	public String getQueryID() {
		return this.clinRuleset.getId();
	}
	
	public Collection<String> getStreams() {
		return this.clinRuleset.getStreamWindowMap().keySet();
	}
	
	public ClingoRuleSet getClinRuleset(){
		return this.clinRuleset;
	}

	
	public boolean addObserver(QueryResultObserverWrapper arg0) {
		throw new UnsupportedOperationException();
	}

	public void changeQueryStatus(RSPComponentStatus arg0) {
		throw new UnsupportedOperationException();
		
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public Map<String, QueryResultObserverWrapper> getObservers() {
		throw new UnsupportedOperationException();
	}

	public RSPComponentStatus getQueryStatus() {
		throw new UnsupportedOperationException();
	}

	public AbstractQueryResultProxy getResultProxy() {
		throw new UnsupportedOperationException();
	}

	public RuleSet getRuleSet() {
		throw new UnsupportedOperationException();
	}

	public QueryType getType() {
		throw new UnsupportedOperationException();
	}

	public boolean removeObserver(String arg0) {
		throw new UnsupportedOperationException();
	}
	

}
