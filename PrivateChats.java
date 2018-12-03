import java.sql.*;
public class PrivateChats {
    private DBConnection dbConnection= new DBConnection();
//    public void view_group(String groupID){
//        try {
//            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("SELECT USER_ID FROM `USER` WHERE PHONE_NUMBER = (?)");
//            String user_id;
//            preparedStatement.setString(1,user);
//            ResultSet res = preparedStatement.executeQuery();
//            user_id = res.getString(1);
//            preparedStatement = dbConnection.connection.prepareStatement("SELECT * FROM private_chats WHERE receiver_ID = (?) ORDER BY DATE LIMIT 20");
//            preparedStatement.setString(1, groupID);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                String s = rs.getString(4);
//                preparedStatement = dbConnection.connection.prepareStatement("select name FROM PROFILE WHERE PROFILE_ID = (?)");
//                preparedStatement.setString(1, s);
//                ResultSet rs2 = preparedStatement.executeQuery();
//                String senderName = rs2.getString(1);
//                System.out.println(rs.getString(1));
//                String date = rs.getString(3);
//                String text = rs.getString(2);
//                if (senderName != null)
//                    System.out.printf("Sender:%s Time:\"%s\" Message:\"%s\"\n", senderName, date, text);
//                else {
//                    preparedStatement = dbConnection.connection.prepareStatement("select phone_number FROM `user` WHERE PROFILE_ID = (?)");
//                    preparedStatement.setString(1, s);
//                    rs2 = preparedStatement.executeQuery();
//                    String phone = rs2.getString(1);
//                    System.out.printf("Sender:%s Time:\\\"%s\\\" Message:\\\"%s\\\"\\n\"",phone , date, text);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void view_user_profile(String phone){
        try {
            PreparedStatement preparedStatement = dbConnection.connection.prepareStatement("SELECT user_id FROM `user` WHERE phone_number = (?)");
            preparedStatement.setString(1, phone);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.getFetchSize() == 0){
                System.out.println("user not found");
            }
            String user_id = rs.getString(1);
            preparedStatement = dbConnection.connection.prepareStatement("select name FROM PROFILE WHERE PROFILE_ID = (?)");
            preparedStatement.setString(1, user_id);
            rs = preparedStatement.executeQuery();
            String name = rs.getString(1);
            preparedStatement = dbConnection.connection.prepareStatement("SELECT BIO FROM USER_SETTINGS WHERE PHONE_NUMBER = (?)");
            preparedStatement.setString(1, phone);
            rs = preparedStatement.executeQuery();
            String bio = rs.getString(1);
            System.out.printf("Name:%s Bio:%s\n", name, bio);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void count_unread(String phoneNumber){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dbConnection.connection.prepareStatement("SELECT user_id FROM `user` WHERE phone_number = (?)");
            preparedStatement.setString(1, phoneNumber);
            ResultSet rs = preparedStatement.executeQuery();
            String id = null;
            if (rs.next()){
                id = rs.getString("user_id");
            }

            preparedStatement = dbConnection.connection.prepareStatement("SELECT COUNT(*) FROM CHANNEL_MESSAGES CM WHERE " +
                    "exists (SELECT * FROM CHANNEL_MEMBERS WHERE MEMBER_ID = (?)) AND CM.DATE < " +
                    "(SELECT LAST_SEEN FROM CHANNEL_MEMBERS WHERE CHANNEL_ID = CM.CHANNEL_ID AND MEMBER_ID = (?))");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
            int count = 0;
            if (rs.next()){
                count += Integer.parseInt(rs.getString(1));
            }
            preparedStatement = dbConnection.connection.prepareStatement("SELECT COUNT(*) FROM GROUP_MESSAGES CM WHERE " +
                    "exists (SELECT * FROM GROUP_MEMBERS WHERE MEMBER_ID = (?)) AND CM.DATE < " +
                    "(SELECT LAST_SEEN FROM GROUP_MEMBERS WHERE GROUP_ID = CM.GROUP_ID AND MEMBER_ID = (?))");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
            if (rs.next()){
                count += Integer.parseInt(rs.getString(1));
            }
            preparedStatement = dbConnection.connection.prepareStatement("SELECT COUNT(*) FROM PRIVATE_CHATS CM WHERE " +
                    "RECEIVER_ID = (?) AND CM.DATE < " +
                    "(SELECT LAST_SEEN FROM PRIVATE_CHATS WHERE RECEIVER_ID = (?))");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()){
                count += Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
