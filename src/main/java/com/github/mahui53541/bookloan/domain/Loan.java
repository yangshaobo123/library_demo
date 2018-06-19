package com.github.mahui53541.bookloan.domain;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mahui
 * @create 2017-06-11 12:07
 **/
@Alias("loan")
public class Loan implements Serializable {
    private Integer id;
    private LocalDateTime loanDate;
    private LocalDateTime dateForReturn;
    private LocalDateTime returnDate;
    private Book book;
    private Member member;

    public Loan() {
    }

    public Loan(Integer id, LocalDateTime loanDate, LocalDateTime dateForReturn, LocalDateTime returnDate, Book book, Member member) {
        this.id = id;
        this.loanDate = loanDate;
        this.dateForReturn = dateForReturn;
        this.returnDate = returnDate;
        this.book = book;
        this.member = member;
    }

    /**
     * 判断图书是否归还
     * @return
     */
    public boolean hasNotBeenReturned(){
        return returnDate==null;
    }

    /**
     * 归还图书
     */
    public void markAsReturned(){
        returnDate=LocalDateTime.now();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDateTime getDateForReturn() {
        return dateForReturn;
    }

    public void setDateForReturn(LocalDateTime dateForReturn) {
        this.dateForReturn = dateForReturn;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanDate=" + loanDate +
                ", dateForReturn=" + dateForReturn +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", 借阅人=" + member.getName() +
                '}';
    }
}
