package com.github.mahui53541.bookloan.domain;

/**
 * @author mahui
 * @create 2017-06-11 13:23
 **/
public class HasReachMaxSpecification implements ISPecification<Member> {
    @Override
    public boolean isSatisfiedBy(Member member) {
        return member.getLoans().stream().filter(loan -> loan.hasNotBeenReturned()).count()<3;
    }
}
