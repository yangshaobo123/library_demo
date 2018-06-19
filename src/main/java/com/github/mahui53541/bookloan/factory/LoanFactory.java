package com.github.mahui53541.bookloan.factory;

import com.github.mahui53541.bookloan.domain.Book;
import com.github.mahui53541.bookloan.domain.Loan;
import com.github.mahui53541.bookloan.domain.Member;

import java.time.LocalDateTime;

/**
 * @author mahui
 * @create 2017-06-11 12:21
 **/
public class LoanFactory {
    public static Loan createLoan(Book book, Member member){
        Loan loan=new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDateForReturn(LocalDateTime.now().plusDays(10l));
        return loan;
    }
}
