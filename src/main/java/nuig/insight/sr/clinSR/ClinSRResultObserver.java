package nuig.insight.sr.clinSR;

import be.ugent.idlab.rspservice.common.enumerations.QueryType;
import be.ugent.idlab.rspservice.common.interfaces.AbstractQueryResultProxy;
import org.apache.jena.rdf.model.*;
import sr.core.triple_based_reasoner.TimeStampedTriple;
import sr.core.triple_based_reasoner.Triple;
import sr.core.triple_based_reasoner.TripleClingoReasoner;

import java.io.ByteArrayOutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class ClinSRResultObserver extends AbstractQueryResultProxy {

    private String queryID;

    public ClinSRResultObserver(String queryID, TripleClingoReasoner engine) {
        this.queryID = queryID;
        engine.addObserver(new ClinSRResultDispatcher());
    }


    @Override
    public String getQueryID() {
        return queryID;
    }

    @Override
    public QueryType getQueryType() {
        return null;
    }


    private class ClinSRResultDispatcher implements Observer {
        public void update(Observable o, Object arg) {

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Model defaultModel = ModelFactory.createDefaultModel();
            for (Object ob : (Set) arg) {
//                System.out.println("Answer set  has " + ((Set) ob).size() + " triples at " + System.currentTimeMillis());
                for (Object temp : (Set) ob) {
                    Triple t = ((TimeStampedTriple) temp).getTriple();
                    defaultModel.add(createStatement(t));
                }
            }
            defaultModel.write(os, "JSON-LD");
            setChanged();
            ClinSRResultObserver.this.notifyObservers(os.toString());
        }

        private Statement createStatement(Triple t) {
            //TODO check literals and blank nodes
            Property property = ResourceFactory.createProperty(t.getPredicate());
            Resource subject = ResourceFactory.createResource(t.getSubject());
            Resource object = ResourceFactory.createResource(t.getObject());
            return ResourceFactory.createStatement(subject, property, object);
        }
    }
}
