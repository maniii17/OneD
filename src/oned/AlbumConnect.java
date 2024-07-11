
package oned;

import java.sql.*;
public class AlbumConnect {
    Connection conn;
        
    public AlbumConnect(){
         String url = "jdbc:sqlserver://localhost:1433;databaseName=ONE_D;username=sa;password=1234;";
        
       AlbumConnectDb();
       
    }
    public boolean AlbumConnectDb(){
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
    public boolean InsertData(int ID,String AlbumName,String RecordedYear,String ReleaseYear ){
        try{
            Statement stmt = conn.createStatement();
            String insertSql = "INSERT INTO Album (AID,Name,recordedyear,releaseyear) VALUES ("+ID+",'"+AlbumName+"','"+RecordedYear+"','"+ReleaseYear+"');";
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

            String query = "SELECT AID FROM Album WHERE AID = "+id;

            ResultSet rs = stmt.executeQuery(query);

    if(rs.next()){
        do{
            System.out.println("AID: "+rs.getString("AID"));
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
    
        try{
            Statement stmt=conn.createStatement();
            if(SearchData(id)){
                String query="DELETE FROM Album WHERE AID= "+id;
                stmt.executeUpdate(query);
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
        }
    
    public boolean UpdateData(int ID,String AlbumName,String ReleaseYear){
        try{
            Statement stmt = conn.createStatement();
        
            if(SearchData(ID)){
                String query = "UPDATE Album SET Name = '"+AlbumName+"',releaseyear = '"+ReleaseYear+"' WHERE AId ="+ID+";";
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
    
 
