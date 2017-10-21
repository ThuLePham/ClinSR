package nuig.insight.sr.clinSR;

import be.ugent.idlab.rspservice.common.enumerations.QueryType;
import be.ugent.idlab.rspservice.common.enumerations.RSPComponentStatus;
import be.ugent.idlab.rspservice.common.interfaces.AbstractQueryResultProxy;
import be.ugent.idlab.rspservice.common.interfaces.Query;
import be.ugent.idlab.rspservice.common.interfaces.QueryResultObserverWrapper;
import be.ugent.idlab.rspservice.common.interfaces.RuleSet;
import sr.core.atom_based_reasoner.ClingoRuleSet;

import java.util.Collection;
import java.util.Map;

/**
 * @author thulepham
 */
public class ClinSRQuery implements Query {

    private ClingoRuleSet clinRuleset;
    private String queryBody;
    private ClinSRResultObserver handler;

    public ClinSRQuery(
            String queryId, String body, ClinSRResultObserver handler) {
        this.queryBody = body;
        this.handler = handler;
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

    public ClingoRuleSet getClinRuleset() {
        return this.clinRuleset;
    }


    public boolean addObserver(QueryResultObserverWrapper arg0) {
        this.handler.addObserver(arg0.getObserver());
        return true;
    }

    public void changeQueryStatus(RSPComponentStatus arg0) {
        throw new UnsupportedOperationException();

    }

    public String getName() {
        return "Program";
    }

    public Map<String, QueryResultObserverWrapper> getObservers() {
        throw new UnsupportedOperationException();
    }

    public RSPComponentStatus getQueryStatus() {
        return RSPComponentStatus.RUNNING;
    }

    public AbstractQueryResultProxy getResultProxy() {
        throw new UnsupportedOperationException();
    }

    public RuleSet getRuleSet() {
        throw new UnsupportedOperationException();
    }

    public QueryType getType() {
       return QueryType.STREAM;
    }

    public boolean removeObserver(String arg0) {
        throw new UnsupportedOperationException();
    }


}
