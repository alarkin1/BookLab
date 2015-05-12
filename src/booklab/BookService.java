package booklab;

import java.util.List;

/**
 *
 * @author Instlogin
 */
public class BookService {

    private BookDaoStrategy bookDao;

    public BookService() {
    }

    public List<Book> getAllBooks() throws DataAccessException {
        return bookDao.getAllBooks();
    }

    public void deleteRecordViaBookId(int bookId) throws DataAccessException, Exception {
        bookDao.deleteBookViaBookId(bookId);
    }

    public BookDaoStrategy getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDaoStrategy bookDao) {
        this.bookDao = bookDao;
    }

    public static void main(String[] args) throws DataAccessException, Exception {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/books_db";
        String username = "root";
        String password = "admin";

        BookService bookService = new BookService();
        bookService.setBookDao(new BookDao());
        bookService.getBookDao().setDb(new DB_Mysql());
        bookService.getBookDao().getDb().openConnection(driver, url, username, password);
        bookService.deleteRecordViaBookId(3);
    }
}
