import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Groupes {
    DBConnection dbConnection = new DBConnection();
    public void createGroup(String groupID , String name ,String adminPhoneNumber){
        dbConnection.makeConnection();
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("INSERT INTO groupes (group_id,name,admin_phone_number)" +
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
    public ResultSet viewGroupProfile(String groupID){
        ResultSet resultSet=null;
        dbConnection.makeConnection();
        try {
            PreparedStatement groupProfile = dbConnection.connection.prepareStatement(
                    "SELECT group_id,name,link FROM groupes WHERE group_id=(?) ");
            groupProfile.setString(1,groupID);
            resultSet = groupProfile.executeQuery();

        }catch (Exception e){
            System.out.println(e);
        }
        return resultSet;
    }
}
