
package oned;

import java.sql.*;
public class ManagerConnect {
    Connection conn;
        
    public ManagerConnect(){
         String url = "jdbc:sqlserver://localhost:1433;databaseName=ONE_D;username=sa;password=1234;";
        
       ManagerConnectDb();
       
    }
    public boolean ManagerConnectDb(){
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
    
    public boolean InsertData(int ID,String ManagerName){
        try{
            Statement stmt = conn.createStatement();
            String insertSql = "INSERT INTO Manager (ManagerID,Name) VALUES ("+ID+",'"+ManagerName+"');";
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
            
            String query = "SELECT ManagerID FROM Manager WHERE ManagerID = "+id;
            
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next()){
                do{
                    System.out.println("ManagerID: "+rs.getString("ManagerID"));
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
                String query = "DELETE FROM Manager WHERE ManagerID = "+id;
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
    
    public boolean UpdateData(int ID,String ManagerName){
        try{
            Statement stmt = conn.createStatement();
        
            if(SearchData(ID)){
                String query = "UPDATE Manager SET Name = '"+ManagerName+"' WHERE ManagerId ="+ID+";";
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
