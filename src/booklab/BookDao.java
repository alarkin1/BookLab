package booklab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Instlogin
 */
public class BookDao implements BookDaoStrategy {

    private String driver;
    private String url;
    private String username;
    private String password;
    private DBAccesorStrategy db;

    public BookDao() {

    }

    @Override
    public List<Book> getAllBooks() throws DataAccessException {
        List<Book> bookList = new ArrayList<>();

        try {
            db.openConnection(driver, url, username, password);
            List<Map<String, Object>> rawList = db.getAllRecords("book");
            for (Map<String, Object> rawRec : rawList) {
                Book book = new Book();

                Object obj = rawRec.get("book_id");
                int intValue = obj == null ? 0 : Integer.parseInt(obj.toString());
                book.setBookId(intValue);

                obj = rawRec.get("title");
                String value = obj == null ? "" : obj.toString();
                book.setTitle(value);

                obj = rawRec.get("author");
                value = obj == null ? "" : obj.toString();
                book.setAuthor(value);

                obj = rawRec.get("page_count");
                intValue = obj == null ? 0 : Integer.parseInt(obj.toString());
                book.setPageCount(intValue);

                obj = rawRec.get("publish_date");
                Date date = obj == null ? null : (Date) obj;
                book.setPublishDate(date);

                bookList.add(book);
            }

        } catch (IllegalArgumentException iae) {
            throw new DataAccessException(iae);
        } catch (ClassNotFoundException cnfe) {
            throw new DataAccessException(cnfe);
        } catch (SQLException sqle) {
            throw new DataAccessException(sqle);
        }

        return bookList;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public DBAccesorStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DBAccesorStrategy db) {
        this.db = db;
    }

    public static void main(String[] args) throws DataAccessException, Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/books_db";
        String username = "root";
        String password = "admin";
        
        DBAccesorStrategy db = new DB_Mysql();
        db.openConnection(driver, url, username, password);
        BookDaoStrategy dao = new BookDao();
        
        dao.setDb(db);
        dao.deleteBookViaBookId(2);
    }

    @Override
    public final void deleteBookViaBookId(int bookId) throws Exception {
        db.deleteRecordViaUniqueIntColumn("book", bookId, "book_id");
    }

}
