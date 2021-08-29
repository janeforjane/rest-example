package service;

import model.Cat;

import java.util.List;

public interface CatService {

    void create (Cat cat);
    List<Cat> readAll();
    Cat read(int id);
    boolean update(Cat cat, int id);
    boolean delete(int id);



}
