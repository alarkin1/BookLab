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
public class DB_Mysql implements DBAccesorStrategy {

    private Connection connection;

    @Override
    public void openConnection(String driveName, String url, String username, String password)
            throws IllegalArgumentException, ClassNotFoundException, SQLException {
        if (url == null || url.length() == 0) {
            throw new IllegalArgumentException();
        }

        username = username == null ? "" : username;
        password = password == null ? "" : password;

        Class.forName(driveName);
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public List<Map<String, Object>> getAllRecords(String tablename) throws SQLException {
        String sql = "SELECT * FROM " + tablename + ";";
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> record;
        Statement statement = null;
        ResultSet rs = null;
        ResultSetMetaData metadata = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            metadata = rs.getMetaData();
            final int fields = metadata.getColumnCount();

            while (rs.next()) {
                record = new LinkedHashMap();
                for (int i = 1; i < fields; i++) {
                    try {
                        record.put(metadata.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException ne) {

                    }
                }

                list.add(record);
            }
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> getRecordsForOneCriteria(String tablename, String columnName, String searchTerm) throws Exception {
        String sql = "SELECT * FROM " + tablename + " WHERE " + columnName + " = " + searchTerm + ";";
        final List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> record;
        Statement statement = null;
        ResultSet rs = null;
        ResultSetMetaData metadata = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            metadata = rs.getMetaData();
            final int fields = metadata.getColumnCount();
            while (rs.next()) {
                record = new HashMap();
                for (int i = 0; i < fields; i++) {
                    record.put(metadata.getColumnName(i), rs.getObject(i));
                }
                list.add(record);
            }
        } finally {
            statement.close();
            closeConnection();
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        DBAccesorStrategy db = new DB_Mysql();

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/books_db";
        String username = "root";
        String password = "admin";

        db.openConnection(driver, url, username, password);
        db.deleteRecordViaUniqueIntColumn("book", 1, "book_id");
    }

    @Override
    public final void deleteRecordViaUniqueIntColumn(String tablename, int uniqueIntVal, String columnToCompare) throws Exception {
        String sqlQuery = "DELETE FROM " + tablename + " WHERE " + columnToCompare + " = " + uniqueIntVal + ";";
        System.out.println(sqlQuery);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sqlQuery);
        } finally {
            statement.close();
            closeConnection();
        }
    }
}
