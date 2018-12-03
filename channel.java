import java.sql.*;

public class channel {
    private DBConnection dbConnection= new DBConnection();
    public void leave_channel(String ID, String user){
        dbConnection.makeConnection();
        String user_id = null;
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("SELECT USER_ID FROM `USER` WHERE PHONE_NUMBER = (?)");
            preparedStatement.setString(1,user);
            ResultSet res = preparedStatement.executeQuery();
            user_id = res.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user_id == null){
            System.out.println("user not found");
            return;
        }
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("DELETE FROM CHANNEL_MEMBERS WHERE channel_id = (?) AND member_id = (?)");
            preparedStatement.setString(1,ID);
            preparedStatement.setString(2,user_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void view_group(String channelID){
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM channel_messages WHERE group_ID = (?) ORDER BY DATE LIMIT 20");
            preparedStatement.setString(1, channelID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String s = rs.getString(4);
                preparedStatement = dbConnection.connection.prepareStatement("select name FROM PROFILE WHERE PROFILE_ID = (?)");
                preparedStatement.setString(1, s);
                ResultSet rs2 = preparedStatement.executeQuery();
                String senderName = rs2.getString(1);
                System.out.println(rs.getString(1));
                String date = rs.getString(3);
                String text = rs.getString(2);
                if (senderName != null)
                    System.out.printf("Sender:%s Time:\"%s\" Message:\"%s\"\n", senderName, date, text);
                else {
                    preparedStatement = dbConnection.connection.prepareStatement("select phone_number FROM `user` WHERE PROFILE_ID = (?)");
                    preparedStatement.setString(1, s);
                    rs2 = preparedStatement.executeQuery();
                    String phone = rs2.getString(1);
                    System.out.printf("Sender:%s Time:\\\"%s\\\" Message:\\\"%s\\\"\\n\"",phone , date, text);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void view_channel_profile(String channelID){
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM PROFILE WHERE PROFILE_ID = (?)");
            preparedStatement.setString(1, channelID);
            ResultSet rs = preparedStatement.executeQuery();
            String name = rs.getString(3);
            String link = rs.getString(4);
            preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM CHANNELS WHERE CHANNEL_ID = (?)");
            preparedStatement.setString(1, channelID);
            rs = preparedStatement.executeQuery();
            String admin = rs.getString(1);
            preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM PROFILE WHERE PROFILE_ID = (?)");
            preparedStatement.setString(1, admin);
            rs = preparedStatement.executeQuery();
            String creator = rs.getString("name");
            preparedStatement = dbConnection.connection.prepareStatement("SELECT PHONE_NUMBER FROM `USER` WHERE USER_ID = (?)");
            preparedStatement.setString(1, admin);
            rs = preparedStatement.executeQuery();
            String adminPhone = rs.getString("phone_number");
            preparedStatement = dbConnection.connection.prepareStatement("SELECT COUNT(*) FROM CHANNEL_MEMBERS WHERE CHANNEL_ID = (?)");
            preparedStatement.setString(1, channelID);
            rs = preparedStatement.executeQuery();
            String count = rs.getString(1);
            if(creator == null) {
                System.out.printf("Name:\"%s\" MembersCount:%s Link:\"%s\" Creator:\"%s\" Members:", name, count, link, adminPhone);
                preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM CHANNEL_MEMBERS WHERE CHANNEL_ID = (?)");
                preparedStatement.setString(1, channelID);
                rs = preparedStatement.executeQuery();

                while (rs.next()){
                    String memberID = rs.getString("member_id");
                    preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM PROFILE WHERE PROFILE_ID = (?)");
                    preparedStatement.setString(1, admin);
                    rs = preparedStatement.executeQuery();
                    String memName = rs.getString("name");
                    preparedStatement = dbConnection.connection.prepareStatement("SELECT PHONE_NUMBER FROM `USER` WHERE USER_ID = (?)");
                    preparedStatement.setString(1, admin);
                    rs = preparedStatement.executeQuery();
                    String memPhone = rs.getString("phone_number");
                    if (memName != null) {
                        System.out.printf("\"%s\"", memName);
                    }
                    else {
                        System.out.printf("\"%s\"", memPhone);
                    }

                }


            }
            else {

                System.out.printf("Name:\"%s\" MembersCount:%s Link:\"%s\" Creator:\"%s\" Members:", name, count, link, creator);
                preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM CHANNEL_MEMBERS WHERE CHANNEL_ID = (?)");
                preparedStatement.setString(1, channelID);
                rs = preparedStatement.executeQuery();

                while (rs.next()){
                    String memberID = rs.getString("member_id");
                    preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM PROFILE WHERE PROFILE_ID = (?)");
                    preparedStatement.setString(1, admin);
                    rs = preparedStatement.executeQuery();
                    String memName = rs.getString("name");
                    preparedStatement = dbConnection.connection.prepareStatement("SELECT PHONE_NUMBER FROM `USER` WHERE USER_ID = (?)");
                    preparedStatement.setString(1, admin);
                    rs = preparedStatement.executeQuery();
                    String memPhone = rs.getString("phone_number");
                    if (memName != null) {
                        System.out.printf("\"%s\"", memName);
                    }
                    else {
                        System.out.printf("\"%s\"", memPhone);
                    }

                }
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
