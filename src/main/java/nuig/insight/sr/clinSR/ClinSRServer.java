package nuig.insight.sr.clinSR;

import be.ugent.idlab.rspservice.common.RSPServer;

public class ClinSRServer extends RSPServer{

    public static void main(String[] args) throws Exception {
    	new ClinSRServer().start(new ClinSREngine(), args[0]);
    }
}
