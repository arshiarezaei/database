import java.sql.PreparedStatement;

public class Groupes {
    DBConnection dbConnection = new DBConnection();
    public void createGroup(String groupID , String name ,String adminPhoneNumber){
        dbConnection.makeConnection();
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("INSERT INTO groupes (group_id,group_id,admin_phone_number)" +
                    "values (?,?,?)");
            preparedStatement.setString(1,groupID);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,adminPhoneNumber);
            preparedStatement.executeUpdate();
           dbConnection.connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
