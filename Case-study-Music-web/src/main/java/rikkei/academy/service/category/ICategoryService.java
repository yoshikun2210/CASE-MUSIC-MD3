package rikkei.academy.service.category;

import rikkei.academy.model.music.Category;
import rikkei.academy.service.IGenericService;

import java.util.List;

public interface ICategoryService extends IGenericService<Category> {
    List<Category> findAll();
    Category findById(int id);
    void deleteById(int id);
    List<Category> findByName(String name);
}
