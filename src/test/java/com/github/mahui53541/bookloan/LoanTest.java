package com.github.mahui53541.bookloan;

import com.github.mahui53541.bookloan.domain.Book;
import com.github.mahui53541.bookloan.domain.Loan;
import com.github.mahui53541.bookloan.domain.Member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author mahui
 * @create 2017-06-11 13:37
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml","classpath:spring-mvc-config.xml"})
public class LoanTest {

    private final static Logger logger = LoggerFactory.getLogger(LoanTest.class);

    private static Member member1=null;
    private static Member member2=null;
    private static Member member3=null;
    private static Book book1=null;
    private static Book book2=null;
    private static Book book3=null;
    private static Book book4=null;
    private static Book book5=null;
    private static Book book6=null;
    private static Book book7=null;
    private static Book book8=null;
    private static Book book9=null;

    public void generateData(){
        logger.info("初始化数据");
        //构造人员
        member1=new Member(9143690,"zhangsan",new ArrayList<Loan>());
        member2=new Member(9143691,"lisi",new ArrayList<Loan>());
        member3=new Member(9143692,"wangwu",new ArrayList<Loan>());
        //构造书籍
        book1=new Book(1,"0001","鸟哥的Linux私房菜(副本1)",member3);
        book2=new Book(2,"0001","鸟哥的Linux私房菜(副本2)",null);
        book3=new Book(3,"0001","鸟哥的Linux私房菜(副本3)",member1);

        book4=new Book(4,"0002","揭秘Angular2(副本1)",member2);
        book5=new Book(5,"0002","揭秘Angular2(副本2)",null);
        book6=new Book(6,"0002","揭秘Angular2(副本3)",member1);

        book7=new Book(7,"0003","Java并发编程实战(副本1)",member2);
        book8=new Book(8,"0003","Java并发编程实战(副本2)",null);
        book9=new Book(9,"0003","Java并发编程实战(副本3)",member1);

        //借书记录
        LocalDateTime now=LocalDateTime.now();
        //已经还书
        Loan loan1=new Loan(1, now.minusDays(30),now.minusDays(20),now.minusDays(25),book1,member1);
        Loan loan2=new Loan(2, now.minusDays(42),now.minusDays(32),now.minusDays(33),book2,member2);
        Loan loan3=new Loan(3, now.minusDays(24),now.minusDays(14),now.minusDays(15),book9,member3);
        Loan loan4=new Loan(4, now.minusDays(30),now.minusDays(20),now.minusDays(25),book3,member1);
        Loan loan5=new Loan(5, now.minusDays(42),now.minusDays(32),now.minusDays(33),book6,member2);
        Loan loan6=new Loan(6, now.minusDays(24),now.minusDays(14),now.minusDays(15),book7,member3);
        //未还
        Loan loan7=new Loan(7, now.minusDays(4),now.plusDays(6),null,book3,member1);
        Loan loan8=new Loan(8, now.minusDays(5),now.plusDays(5),null,book6,member1);
        Loan loan9=new Loan(9, now.minusDays(1),now.plusDays(9),null,book9,member1);
        Loan loan10=new Loan(10, now.minusDays(3),now.plusDays(7),null,book4,member2);
        Loan loan11=new Loan(11, now.minusDays(2),now.plusDays(8),null,book7,member2);
        Loan loan12=new Loan(12, now.minusDays(1),now.plusDays(9),null,book1,member3);

        //关联
        member1.getLoans().add(loan1);
        member1.getLoans().add(loan4);
        member1.getLoans().add(loan7);
        member1.getLoans().add(loan8);
        member1.getLoans().add(loan9);

        member2.getLoans().add(loan2);
        member2.getLoans().add(loan5);
        member2.getLoans().add(loan10);
        member2.getLoans().add(loan11);

        member3.getLoans().add(loan2);
        member3.getLoans().add(loan6);
        member3.getLoans().add(loan12);

        logger.info("初始化数据完成");
    }
    @Test
    public void loanBook(){
        generateData();

        //张三不可借：已经借满三本
        logger.info("");
        logger.info("张三申请借书："+member1.toString());
        member1.getLoans().stream().forEach(loan -> {
            logger.info("张三借书历史："+loan.toString());
        });
        logger.info("所借书籍："+book2);
        logger.info("是否可借："+member1.canLoan(book2));

        //李四不可借：不可借同一本书
        logger.info("");
        logger.info("李四申请借书："+member2.toString());
        member2.getLoans().stream().forEach(loan -> {
            logger.info("李四借书历史："+loan.toString());
        });
        logger.info("所借书籍："+book5);
        logger.info("是否可借："+member2.canLoan(book5));

        //王五借书
        logger.info("");
        logger.info("王五申请借书："+member3.toString());
        member3.getLoans().stream().forEach(loan -> {
            logger.info("王五借书历史："+loan.toString());
        });
        logger.info("所借书籍："+book5);
        logger.info("是否可借："+member3.canLoan(book5));
        if(member3.canLoan(book5)){
            Loan loan=member3.loan(book5);
            logger.info("生成借书记录："+loan.toString());
            logger.info("所借图书状态："+book5.toString());
        }

        //王五还书
        logger.info("");
        logger.info("王五还书："+member3.toString());
        member3.getLoans().stream().forEach(loan -> {
            logger.info("王五借书历史："+loan.toString());
        });
        logger.info("要还书籍："+book5);
        member3.returnBook(book5);
        logger.info("已还图书状态："+book5.toString());
    }
}
