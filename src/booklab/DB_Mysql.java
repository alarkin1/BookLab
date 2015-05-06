/*
 * Emma Kordik
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklab;

import java.sql.*;
import java.util.*;

/**
 *
 * @author emmakordik
 */
public class DB_Mysql {
    private Connection connection;
    
    
    public void openConnection(String driveName, String url, String username, String password)
            throws IllegalArgumentException, ClassNotFoundException, SQLException {
       if(url == null || url.length() == 0){
           throw new IllegalArgumentException();
       }
       
       username = username==null ? "" : username;
       password = password==null ? "" : password;
       
       Class.forName(driveName);
       connection = DriverManager.getConnection(url, username, password);
    }


    public void closeConnection() throws SQLException {
        connection.close();
    }


    public List<Map<String, Object>> getAllRecords(String tablename) throws SQLException, Exception {
        String sql = "SELECT * FROM " + tablename + ";";
        final List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> record;
        Statement statement = null;
        ResultSet rs = null;
        ResultSetMetaData metadata = null;
        
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            metadata = rs.getMetaData();
            final int fields = metadata.getColumnCount();
            
            while(rs.next()){
                record = new LinkedHashMap();
                for(int i = 1; i<fields; i++){
                    try{
                        record.put(metadata.getColumnName(i), rs.getObject(i));
                    }catch(NullPointerException ne){
                        
                    }
                }
                
                list.add(record);
            }
        }finally{
            closeConnection();
        }
        
        return list;
    }
    
    public static void main(String[] args) throws Exception{
        DB_Mysql db = new DB_Mysql();
        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book_db";
        String username = "root";
        String password = "";
        
        db.openConnection(driver, url, username, password);
        List<Map<String,Object>> records = db.getAllRecords("book");
        
        
        for(Map r: records){
            Set keySet = r.keySet();
            List headers = new ArrayList();
            headers.addAll(keySet);
           
            for(int i = 0; i<r.size(); i++){
                System.out.println(headers.get(i) + " " + r.get(headers.get(i)));
            }
            System.out.println("");
        }
    }
}
