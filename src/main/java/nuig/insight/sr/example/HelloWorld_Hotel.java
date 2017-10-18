package nuig.insight.sr.example;

import nuig.insight.sr.clinSR.ClinSREngine;
import nuig.insight.sr.clinSR.ClinSRResultObserver;

public class HelloWorld_Hotel {

	public static void main(String[] args) {
		/**
		 * 1. ClinSREngine using clingo for performing reasoning. Therefore, clingo has to be install first. 
		 * 2. Change clingo path at config.properties file in /src/main/resources
		 */
		
		
		
		String query = 
				  "#input rdf_type/2;"
				+ "#input uniben_worksFor/2;"
				+ "#prefix rdf : <http://www.w3.org/1999/02/22-rdf-syntax-ns#>;"
				+ "#prefix ex : <http://example.org/>;"
				+ "#from stream <http://hotel.org#stream1> [TIME 5s STEP 3s];"
				+ "#from stream <http://hotel.org#stream2> [TIME 3s STEP 3s];"
				+ "1 { hotel(X):rdf_type(X,\"ex_hotel\")} 1;"
				+ "smallStreetHotel(X) :- ex_onStreet(X,Y), (@getStringValue(Y)) = \"SmallStreet\";"
				+ "noisyHotel(X) :- hotel(X), not smallStreetHotel(X);"
				+ "quiteHotel(X) :- hotel(X), smallStreetHotel(X);"
				+ ":- noisyHotel(X);"
				+ "#maximize {(@getIntegerValue(Y))@1 : ex_star(X,Y), hotel(X)};"
				+ "#minimize {(@getIntegerValue(Y))@2 : ex_cost(X,Y), hotel(X)};"
				+ "ex_optHotelStar(X,Y):- hotel(X), ex_star(X,Y);"
				+ "ex_optHotelCost(X,Y) :- hotel(X), ex_cost(X,Y);"
				+ "#show ex_optHotelStar/2;"
				+ "#show ex_optHotelCost/2;";
		
		
				
			
		
		//Start reasoner
		ClinSREngine reasoner = new ClinSREngine();
		
		//create stream
		HotelExampleStream stream1 = new HotelExampleStream("http://hotel.org#stream1");
		HotelExampleStream stream2 = new HotelExampleStream("http://hotel.org#stream2");
		

		//register input stream
		reasoner.registerStream(stream1);
		reasoner.registerStream(stream2);
	
		
		
		//register query (before register stream)
		reasoner.registerQuery("hotel_query", query);
		
		//register observer to get results
		reasoner.registerResultObserver(new ClinSRResultObserver());
		
		
		//start stream
		new Thread(stream1).start();
		new Thread(stream2).start();
	
		

//		
		

	}

}
