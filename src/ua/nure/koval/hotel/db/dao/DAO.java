package ua.nure.koval.hotel.db.dao;

import java.util.List;

public interface DAO<T> {
    
    T getById(Long id);
     
    List<T> getAll();
     
    boolean save(T t);
     
    boolean update(T t);
     
    boolean delete(T t);
}