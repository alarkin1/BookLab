/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklab;

import java.util.List;

/**
 *
 * @author Instlogin
 */
public interface BookDaoStrategyOld {

    List<Book> getAllBooks() throws DataAccessException;

    public abstract void deleteBookViaBookId(int bookId) throws Exception;
}
