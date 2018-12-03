import java.sql.PreparedStatement;

public class ChannelMessage {
    DBConnection dbConnection =new DBConnection();
    public void sendMessage(String channelID , String text){
        dbConnection.makeConnection();
        try {
            PreparedStatement ps = dbConnection.connection.prepareStatement("INSERT INTO channel_messages(channel" +
                    "_id , text) values ( ?,?)");
            ps.setString(1,channelID);
            ps.setString(2,text);
            ps.executeUpdate();

            dbConnection.connection.close();
        }catch (Exception e){
            System.out.println(e);

        }
    }

}
