package oned;

import java.sql.*;
public class SongConnect {
    Connection conn;
        
    public SongConnect(){
         String url = "jdbc:sqlserver://localhost:1433;databaseName=ONE_D;username=sa;password=1234;";
        
       SongConnectDb();
       
    }


    
    public boolean SongConnectDb(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ONE_D;username=sa;password=1234");
            System.out.println("Connection Successfull!!!");
            return true;
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
     public boolean InsertData(int id,String SongName,String TrackName,String Duration,int AlbumID){
        try{
            Statement stmt = conn.createStatement();
            String insertSql = "INSERT INTO Song(songid,name,tracknumber,duration,AID) VALUES ("+id+",'"+SongName+"','"+TrackName+"','"+Duration+"','"+AlbumID+"');";
            stmt.execute(insertSql); 
                     
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean SearchData(int id){
        try{
            Statement stmt = conn.createStatement();
            
            String query = "SELECT songid FROM Song WHERE Songid = "+id;
            
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                do{
                    System.out.println("songid: "+rs.getString("songid"));
                    System.out.println(" ");
                }while(rs.next());
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException e){
            return false;
        }
    }
    
    public boolean DeleteData(int id){
        try {
            Statement stmt = conn.createStatement();
                
            if(SearchData(id)){ 
                String query = "DELETE FROM Song WHERE songid = "+id;
                stmt.executeUpdate(query);
                return true;
                }
            else{
                return false; 
                }
        } 
        catch (SQLException e) {
            return false;
        }
    }
    
    public boolean UpdateData(int ID,String SongName,String TrackName,String Duration,int AID){
        try{
            Statement stmt = conn.createStatement();
        
            if(SearchData(ID)){
                String query = "UPDATE Song SET name = '"+SongName+"',tracknumber = '"+TrackName+"',duration = '"+Duration+"',AID = '"+AID+"' WHERE songid ="+ID+";";
                stmt.executeUpdate(query);
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
