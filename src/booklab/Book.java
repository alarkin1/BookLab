package booklab;

import java.util.Date;

/**
 *
 * @author Instlogin
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private int pageCount;
    private Date publishDate;

    public Book() {
    }

    public Book(int bookId, String title, String author, int pageCount, Date publishDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.publishDate = publishDate;
    }
    
    

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.bookId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", title=" + title + ", author=" + author + ", pageCount=" + pageCount + ", publishDate=" + publishDate + '}';
    }
    
    
}
