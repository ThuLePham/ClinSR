%%%%% data %%%%%%%%%%%%%
rdf_type("e8f1a67f-d475-449a-bdee-f2242ea9b962","ex_hotel").
ex_star("e8f1a67f-d475-449a-bdee-f2242ea9b962","4^^xsd:double").
ex_onStreet("e8f1a67f-d475-449a-bdee-f2242ea9b962","SmallStreet^^http://www.w3.org/2001/XMLSchema#string").
ex_cost("e8f1a67f-d475-449a-bdee-f2242ea9b962","79^^xsd:double").

rdf_type("2ca64da8-dd07-4041-aaba-c7af74080119","ex_hotel").
ex_star("2ca64da8-dd07-4041-aaba-c7af74080119","3^^xsd:double").
ex_onStreet("2ca64da8-dd07-4041-aaba-c7af74080119","SmallStreet^^http://www.w3.org/2001/XMLSchema#string").
ex_cost("2ca64da8-dd07-4041-aaba-c7af74080119","440^^xsd:double").


rdf_type("e8f1a67f-d475-449a-bdee-f2242ea9b962","ex_hotel").
ex_star("e8f1a67f-d475-449a-bdee-f2242ea9b962","4^^xsd:double").
ex_onStreet("e8f1a67f-d475-449a-bdee-f2242ea9b962","SmallStreet^^http://www.w3.org/2001/XMLSchema#string").
ex_cost("e8f1a67f-d475-449a-bdee-f2242ea9b962","79^^xsd:double").

rdf_type("2ca64da8-dd07-4041-aaba-c7af74080119","ex_hotel").
ex_star("2ca64da8-dd07-4041-aaba-c7af74080119","3^^xsd:double").
ex_onStreet("2ca64da8-dd07-4041-aaba-c7af74080119","SmallStreet^^http://www.w3.org/2001/XMLSchema#string").
ex_cost("2ca64da8-dd07-4041-aaba-c7af74080119","440^^xsd:double").


%%%%% rules %%%%%%%%%%%%%
smallStreetHotel(X) :- ex_onStreet(X,Y), (@getStringValue(Y)) = "SmallStreet".
ex_optHotelStar(X,Y):- hotel(X), ex_star(X,Y).
#show ex_optHotelCost/2.#show ex_optHotelStar/2.
ex_optHotelCost(X,Y) :- hotel(X), ex_cost(X,Y).
:- noisyHotel(X).noisyHotel(X) :- hotel(X), not smallStreetHotel(X).
quiteHotel(X) :- hotel(X), smallStreetHotel(X).

#maximize {(@getIntegerValue(Y))@1 : ex_star(X,Y), hotel(X)}.
#minimize {(@getIntegerValue(Y))@2 : ex_cost(X,Y), hotel(X)}.
1 { hotel(X):rdf_type(X,"ex_hotel")} 1.