package com.github.mahui53541.bookloan.domain;

import com.github.mahui53541.bookloan.factory.LoanFactory;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author mahui
 * @create 2017-06-11 12:06
 **/
@Alias("member")
public class Member implements Serializable{
    private Integer id;

    private String name;

    private List<Loan> loans=new ArrayList<Loan>();

    public Member() {
    }

    public Member(Integer id, String name, List<Loan> loans) {
        this.id = id;
        this.name = name;
        this.loans = loans;
    }

    /**
     * 通过判断book的loanTo属性判断该书是否可借
     * @param book
     * @return
     */
    public boolean canLoan(Book book){
        HasReachMaxSpecification hasReachMaxSpecification=new HasReachMaxSpecification();
        LoanOnlyOneSpecification loanOnlyOneSpecification=new LoanOnlyOneSpecification(book);

        return book.getLoanTo()==null && hasReachMaxSpecification.isSatisfiedBy(this)&& loanOnlyOneSpecification.isSatisfiedBy(this);
    }


    /**
     * 首先判断是否可借，如果可借则生成借书记录
     * @param book
     * @return
     */
    public Loan loan(Book book){
        Loan loan=null;
        if(canLoan(book)){
            loan=LoanFactory.createLoan(book,this);
            book.setLoanTo(this);
            getLoans().add(loan);
        }
        return loan;
    }

    /**
     * 查询当前所借书籍
     * @param book
     * @return
     */
    public Loan findCurrentLoanFor(Book book){
        Optional<Loan> loanOptional=getLoans().stream()
                .filter(loan -> loan.getReturnDate()==null && loan.getBook().equals(book))
                .findFirst();
        if(loanOptional.isPresent()){
            return loanOptional.get();
        }else{
            return null;
        }

    }

    /**
     * 还书
     * @param book
     */
    public void returnBook(Book book){
        Loan loan=this.findCurrentLoanFor(book);
        if(loan!=null){
            loan.markAsReturned();
            book.setLoanTo(null);
        }
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (!getId().equals(member.getId())) return false;
        return getName().equals(member.getName());
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
