package com.github.mahui53541.bookloan.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author mahui
 * @create 2017-06-11 12:03
 **/
@Alias("book")
public class Book implements Serializable{
    private Integer id;

    private String ISBN;

    private String title;

    private Member loanTo;

    public Book() {
    }

    public Book(Integer id, String ISBN, String title, Member loanTo) {
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.loanTo = loanTo;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Member getLoanTo() {
        return loanTo;
    }

    public void setLoanTo(Member loanTo) {
        this.loanTo = loanTo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ",借阅人："+loanTo+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!getId().equals(book.getId())) return false;
        if (!getISBN().equals(book.getISBN())) return false;
        return getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getISBN().hashCode();
        result = 31 * result + getTitle().hashCode();
        return result;
    }
}
