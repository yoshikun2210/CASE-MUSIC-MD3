package rikkei.academy.service.category;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.music.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService{
    private Connection connection = ConnectMySQL.getConnection();
    private final String CREATE_CATEGORY = "INSERT INTO category(name) values (?);";
    private final String SHOW_CATEGORY = "SELECT * FROM category";
    private final String UPDATE_CATEGORY = "UPDATE category SET name=? WHERE id=?";
    private final String CATEGORY_BY_ID = "SELECT * FROM category WHERE id=?;";
    private final String DELETE_BY_ID ="DELETE FROM category WHERE id=?;";


    @Override
    public void save(Category category) {
        try {
            if (findById(category.getId())==null){
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATEGORY);
                preparedStatement.setString(1,category.getName());
                preparedStatement.executeUpdate();
            }else {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY);
                preparedStatement.setString(1,category.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id,name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CATEGORY_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                category = new Category(id,name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Category> findByName(String name) {
        return null;
    }
}
