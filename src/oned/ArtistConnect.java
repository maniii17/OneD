/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oned;

import java.sql.*;
public class ArtistConnect {
      Connection conn;
        
    public ArtistConnect(){
         String url = "jdbc:sqlserver://localhost:1433;databaseName=ONE_D;username=sa;password=1234;";
        
       ArtistConnectDb();
       
    }
    
        public boolean ArtistConnectDb(){
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
        public boolean InsertData(int ID,String FirstName,String LastName,String Country,String Genre, String DOB,String JoinedDate,int ManagerID){
        try{
            Statement stmt = conn.createStatement();
            String insertSql = "INSERT INTO Artist (AID,FirstName,LastName,Country,Genre,DOB,Joiningdate,ManagerID) VALUES ("+ID+",'"+FirstName+"','"+LastName+"','"+Country+"','"+Genre+"','"+DOB+"','"+JoinedDate+"','"+ManagerID+"');";
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
            
            String query = "SELECT AID FROM Artist WHERE AID = "+id;
            
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
        try {
            Statement stmt = conn.createStatement();
                
            if(SearchData(id)){ 
                String query = "DELETE FROM Artist WHERE AID = "+id;
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
    
    public boolean UpdateData(int ID,String FirstName,String LastName,String Country,String Genre, String DOB,String JoinedDate,int ManagerID){
        try{
            Statement stmt = conn.createStatement();
        
            if(SearchData(ID)){
                String query = "UPDATE Artist SET FirstName = '"+FirstName+"',LastName = '"+LastName+"',Country = '"+Country+"',Genre = '"+Genre+"',DOB = '"+DOB+"',Joiningdate = '"+JoinedDate+"',ManagerID = '"+ManagerID+"' WHERE AID ="+ID+";";
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