import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
     Connection connection;
   public void makeConnection(){
       try {
           Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/phase_2?autoReconnect=true&useSSL=false",
                   "root","1135971374");

       }catch (Exception e){
           System.out.println(e);
       }
   }
}

