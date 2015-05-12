package booklab;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Instlogin
 */
public class MockBookDao implements BookDaoStrategy {
    private String driver;
    private String url;
    private String username;
    private String password;
    private DBAccesorStrategy db;

    List<Map<String,Object>> rawList;

    public MockBookDao() {
        rawList = new ArrayList<>();
        Map<String,Object> rawRec = new HashMap<>();
        rawRec.put("book_id", 1);
        rawRec.put("title", "War of the Worlds");
        rawRec.put("author", "John Smith");
        rawRec.put("page_count", 400);
        rawRec.put("publish_date", new Date());
        rawList.add(rawRec);
    }
    
    @Override
    public List<Book> getAllBooks() throws DataAccessException {
        List<Book> bookList = new ArrayList<>();
        
        try {
            for(Map<String,Object> rawRec : rawList) {
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
                Date date = obj == null ? null : (Date)obj;
                book.setPublishDate(date);
                
                bookList.add(book);
            }
            
        } catch(IllegalArgumentException iae) {
            throw new DataAccessException(iae);
        }
        
        return bookList;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DBAccesorStrategy getDb() {
        return db;
    }

    public void setDb(DBAccesorStrategy db) {
        this.db = db;
    }

    public List<Map<String, Object>> getRawList() {
        return rawList;
    }

    public void setRawList(List<Map<String, Object>> rawList) {
        this.rawList = rawList;
    }
    
    
    
    public static void main(String[] args) throws DataAccessException {
        BookDaoStrategy dao = new MockBookDao();
        List<Book> books = dao.getAllBooks();
        for(Book b : books) {
            System.out.println(b);
        }
    }

    @Override
    public void deleteBookViaBookId(int bookId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
