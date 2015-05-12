/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklab;

/**
 *
 * @author Alex
 */
public class Startup {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/books_db";
        String username = "root";
        String password = "admin";
        BookService newBooksService = new BookService();
        newBooksService.setBookDao(new BookDao());
        newBooksService.getBookDao().setDb(new DB_Mysql());
        try {
            newBooksService.getBookDao().getDb().openConnection(driver, url, username, password);
            newBooksService.deleteRecordViaBookId(4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
