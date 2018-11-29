import java.sql.*;
import java.util.Scanner;

public class Main {
    static boolean loginStat = false;
    static String userPhoneNumber ;

    public static void main(String[] args) {

        while (true){
            System.out.println("enter a command");
            Scanner input = new Scanner(System.in);
            String inputs = input.nextLine();
            System.out.println("next line"+inputs);
            if (inputs.startsWith("create_user")){
                create_user(inputs.substring(12,inputs.length()));
            }
            else if (inputs.startsWith("logout")){
                loginStat = false;

            }
            else if(inputs.startsWith("set_bio")){


            }
            else if (inputs.startsWith("")){

            }

        }

    }

    public static void create_user (String phone){
        String sql = "INSERT INTO user(phone) VALUES('"+phone+"')";
        connection newConnection = new connection();
        newConnection.makeConnection();
        try {
          Statement stmt= newConnection.connection.createStatement();
          stmt.executeUpdate(sql);
          newConnection.connection.close();
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
