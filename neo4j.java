


import org.neo4j.driver.v1.*;
import org.neo4j.*;

class neo4j{
    Driver driver = GraphDatabase.driver("bolt://localhost:11004",AuthTokens.basic("neo4j","1234"));
    Session session = driver.session();
    void addMember(String phoneNumber){

        //TODO check if user doesn't exists add a new node else do nothing.
        session.run("create(:person{phone_number:\'"+phoneNumber+"\'})");
        session.close();
        driver.close();

    }
    void sendMessage(String senderPhoneNumber , String receiverPhoneNumber){
        session.run("match (s:person{phone_number:\'"+senderPhoneNumber+"\'})match(r:person{phone_number:\'"+receiverPhoneNumber+"\'})" +
                "merge (s)-[:chat]-(r)");
        session.close();
        driver.close();


    }
    void nodeWithHighestDegree(){
        StatementResult result = session.run("MATCH (p1:person)--() \n" +
                "RETURN p1.phone_number,count(*) as degree \n" +
                "ORDER BY degree DESC LIMIT 10");
        while (result.hasNext()){
            Record record = result.next();
            System.out.println(record.get(0) +"\t"+record.get(1));
        }



    }




}
