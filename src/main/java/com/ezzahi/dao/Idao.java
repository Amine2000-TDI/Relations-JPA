package com.ezzahi.dao;

import java.util.List;

public interface Idao<U,T> {
    T save(T t);
    List<T> getAll();
    T getById(U id);
    void remove(U id);
}
