package com.github.mahui53541.bookloan.domain;

/**
 * Created by mahui on 2017/6/11.
 */
public interface ISPecification<T> {
    boolean isSatisfiedBy(T entity);
}
