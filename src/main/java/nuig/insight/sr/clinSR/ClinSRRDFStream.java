package nuig.insight.sr.clinSR;

import be.ugent.idlab.rspservice.common.enumerations.RSPComponentStatus;
import be.ugent.idlab.rspservice.common.interfaces.Stream;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.core.RDFDataset;
import com.github.jsonldjava.utils.JsonUtils;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import sr.core.triple_based_reasoner.TimeStampedTriple;
import sr.core.triple_based_reasoner.Triple;
import sr.core.triple_based_reasoner.TripleStream;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by riccardo on 17/08/2017.
 */
public class ClinSRRDFStream extends TripleStream implements Stream {

    private String wsuri, tbox, abox;
    private Session session;

    public ClinSRRDFStream(String streamId) {
        super(streamId);
    }

    public void feedRDFStream(String dataSerialization) {
        try {
            Model model = deserializizeAsJsonSerialization(dataSerialization, null);
            StmtIterator stmtIterator = model.listStatements();

            while (stmtIterator.hasNext()) {
                Statement statement = stmtIterator.nextStatement();
                Triple triple = new Triple(statement.getSubject().toString(), statement.getPredicate().toString(), statement.getObject().toString());
                TimeStampedTriple stTriple = new TimeStampedTriple(triple, System.currentTimeMillis());
                this.put(stTriple);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonLdError jsonLdError) {
            jsonLdError.printStackTrace();
        }
    }

    private Model deserializizeAsJsonSerialization(String asJsonSerialization, JsonLdOptions options) throws IOException, JsonLdError {

        Model model = ModelFactory.createDefaultModel();
        Statement statement = null;
        Object jsonObject = JsonUtils.fromString(asJsonSerialization);
        RDFDataset rd = (RDFDataset) JsonLdProcessor.toRDF(jsonObject);
        Set<String> graphNames = rd.graphNames();
        for (String graphName : graphNames) {
            List<RDFDataset.Quad> l = rd.getQuads(graphName);
            for (RDFDataset.Quad q : l) {
                Property property = ResourceFactory.createProperty(q.getPredicate().getValue());
                Resource subject;
                if (!q.getObject().isBlankNode()) {
                    subject = ResourceFactory.createResource(q.getSubject().getValue());
                } else {
                    subject = new ResourceImpl(new AnonId(q.getSubject().getValue()));
                }

                Resource obj;
                if (!q.getObject().isLiteral()) {
                    if (!q.getObject().isBlankNode()) {
                        statement = ResourceFactory.createStatement(subject, property, ResourceFactory.createResource(q.getObject().getValue()));
                    } else {
                        obj = new ResourceImpl(new AnonId(q.getObject().getValue()));
                        statement = ResourceFactory.createStatement(subject, property, obj);
                    }
                } else {
                    Literal typedLiteral = ResourceFactory.createTypedLiteral(q.getObject().getValue(), NodeFactory.getType(q.getObject().getDatatype()));
                    statement = ResourceFactory.createStatement(subject, property, typedLiteral);
                }

                model.add(statement);
            }

        }
        return model;
    }

    public String getStreamID() {
        return this.id;
    }

    public Object getStream() {
        throw new UnsupportedOperationException();
    }

    public RSPComponentStatus getStatus() {
        return RSPComponentStatus.RUNNING;
    }

    public void setTBox(String graph_uri) {
        this.tbox = graph_uri;
    }

    public void setStaticABox(String graph_uri) {
        this.abox = graph_uri;
    }

    public void setWsSession(Session session) {
        this.session = session;
    }

    public String getSourceURI() {
        return wsuri;
    }

    public void setSourceURI(String wsUrl) {
        this.wsuri = wsUrl;
    }
}
