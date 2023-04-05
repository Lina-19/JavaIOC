package dao;

import java.util.List;

public interface IDao<T,ID>{
    T trouverParId(ID id);
    List<T> findAll();
    T save(T t);
    T update(T t);
    Boolean delete(T t);
    Boolean deleteById(ID id);
}
