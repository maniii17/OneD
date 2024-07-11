/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oned;

  import java.sql.*;
public class StudioConnect {
    private final String url;
    private Connection conn;
    
    public StudioConnect(){
        this.url = "jdbc:sqlserver://localhost:1433;databaseName=ONE_D;username=sa;password=1234;";
        DBConnection();
    }
    
    public boolean DBConnection(){
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(url);
                System.out.println("Sucess"); 

                return true;
            }
            catch(ClassNotFoundException | SQLException e){
                System.err.println(e);


                return false;
            }
    }
    
    public boolean InsertData(int ID,String StudioName,String StudioLocation,String StudioOpen,String StudioClose){
        try{
            Statement stmt = conn.createStatement();
            String insertSql = "INSERT INTO Studio(StudioID,Studio_Name,Studio_Location,StudioOpen,StudioClose) VALUES ("+ID+",'"+StudioName+"','"+StudioLocation+"','"+StudioOpen+"','"+StudioClose+"');";
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
            
            String query = "SELECT StudioID FROM Studio WHERE StudioID = "+id;
            
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                do{
                    System.out.println("StudioID: "+rs.getString("StudioID"));
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
                String query = "DELETE FROM Studio WHERE StudioID = "+id;
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
   
    public boolean UpdateData(int ID,String StudioName,String StudioLocation,String StudioOpen,String StudioClose ){
        try{
            Statement stmt = conn.createStatement();
        
            if(SearchData(ID)){
                String query = "UPDATE Studio SET Studio_Name = '"+StudioName+"',Studio_Location = '"+StudioLocation+"',StudioOpen = '"+StudioOpen+"',StudioClose = '"+StudioClose+"' WHERE StudioId ="+ID+";";
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

