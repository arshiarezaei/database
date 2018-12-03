import java.sql.PreparedStatement;


public class PrivateChats {
    DBConnection dbConnection = new DBConnection();

    public void sendMessage(String sender, String receiver, String text) {
        dbConnection.makeConnection();
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement(" INSERT  INTO private_chats (sender,receiver,text,state)" +
                    "VALUES ((?),(?),(?),0)");
            preparedStatement.setString(1,sender);
            preparedStatement.setString(2,receiver);
            preparedStatement.setString(3,text);
            preparedStatement.executeUpdate();
            dbConnection.connection.close();

        } catch (Exception e) {
            System.out.println(e);

        }
    }
}