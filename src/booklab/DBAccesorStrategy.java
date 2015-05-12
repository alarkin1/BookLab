/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklab;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Instlogin
 */
public interface DBAccesorStrategy {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> getAllRecords(String tablename) throws SQLException;
    public abstract void deleteRecordViaUniqueIntColumn(String tablename, int bookId, String columnToCompare) throws Exception;
    List<Map<String, Object>> getRecordsForOneCriteria(String tablename, String columnName, String searchTerm) throws Exception;   

    void openConnection(String driveName, String url, String username, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException;
    
}
