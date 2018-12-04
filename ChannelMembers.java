import java.rmi.server.ExportException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChannelMembers {
    DBConnection dbConnection = new DBConnection();
    public void addMember(String phoneNumber , String channelID){
        dbConnection.makeConnection();
        try {
            PreparedStatement ps = dbConnection.connection.prepareStatement("INSERT INTO channel_members(channel_id,member_id) " +
                    "VALUES (?,?)");
            ps.setString(1,channelID);
            ps.setString(2,phoneNumber);
            ps.executeUpdate();
            dbConnection.connection.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void removeMember(String memberPhoneNumber , String channelID){
        dbConnection.makeConnection();
        try {
            PreparedStatement ps = dbConnection.connection.prepareStatement("DELETE  FROM channel_members WHERE channel_id=(?) AND member_id=(?)");
            ps.setString(1,channelID);
            ps.setString(2,memberPhoneNumber);
            ps.executeUpdate();
            dbConnection.connection.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public ResultSet viewMembers(String channelID){
        ResultSet resultSet=null;
        dbConnection.makeConnection();
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement(
                    "SELECT member_id FROM channel_members where channel_id=(?)");
            preparedStatement.setString(1,channelID);
            resultSet = preparedStatement.executeQuery();


        }catch (Exception e){
            System.out.println(e);
        }
        return resultSet;
    }
}
