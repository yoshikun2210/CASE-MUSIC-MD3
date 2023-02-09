package rikkei.academy.service;

import java.sql.SQLException;

public interface IGenericService<T> {
    void save(T t);


}
