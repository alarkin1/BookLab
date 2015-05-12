/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklab;

import java.util.List;

/**
 *
 * @author Alex
 */
public interface BookDaoStrategy {

    void deleteBookViaBookId(int bookId) throws Exception;

    List<Book> getAllBooks() throws DataAccessException;

    DBAccesorStrategy getDb();

    String getDriver();

    String getPassword();

    String getUrl();

    String getUsername();

    void setDb(DBAccesorStrategy db);

    void setDriver(String driver);

    void setPassword(String password);

    void setUrl(String url);

    void setUsername(String username);
    
}
