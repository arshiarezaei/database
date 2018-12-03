import java.sql.PreparedStatement;

public class GroupMember {
    DBConnection dbConnection = new DBConnection();
    public void removeMember(String groupID,String memberID){
        dbConnection.makeConnection();
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("DELETE FROM group_members WHERE " +
                    "group_id=(?)AND member_id=(?)");
            preparedStatement.setString(1,groupID);
            preparedStatement.setString(2,memberID);
            preparedStatement.executeUpdate();
            dbConnection.connection.close();

        }catch (Exception e){
            System.out.println(e);
        }


    }
}
