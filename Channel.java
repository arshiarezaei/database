import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Channel {
    DBConnection dbConnection = new DBConnection();
    public void createChannel(String ID , String name ,String adminPhoneNumber){
        dbConnection.makeConnection();
        try {
            PreparedStatement ps =dbConnection.connection.prepareStatement("INSERT INTO channels(channel_id,name,admin_phone_number) VALUES " +
                    "(?,?,?)");
            ps.setString(1,ID);
            ps.setString(2,name);
            ps.setString(3,adminPhoneNumber);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean isAdmin(String phoneNumber,String channelID){
        dbConnection.makeConnection();

        try {
          //  System.out.println("try block");
            PreparedStatement ps=dbConnection.connection.prepareStatement("SELECT channel_id,admin_phone_number FROM channels where channel_id=(?) AND admin_phone_number=(?)");
            ps.setString(1,channelID);
            ps.setString(2,phoneNumber);
            //System.out.println("rs");
            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println("while block");
//                System.out.println(rs.getString("channel_id")+"\t"+rs.getString("admin_phone_number"));
//            }
            if(!rs.first()){
              //  System.out.println("if block");
                return false;
            }
            else {
               // System.out.println("else block");
                return true;
            }

            }catch (Exception e){
            System.out.println(e);

        }
       // System.out.println("return false");
     return false;
    }
    public void setChannelLink(String id,String link){
        dbConnection.makeConnection();
        try {
            PreparedStatement ps =dbConnection.connection.prepareStatement("UPDATE channels SET chanel_link=(?) WHERE channel_id=(?) ");
            ps.setString(1,link);
            ps.setString(2,id);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
