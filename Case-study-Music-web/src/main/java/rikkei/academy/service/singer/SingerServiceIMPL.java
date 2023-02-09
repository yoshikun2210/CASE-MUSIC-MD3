package rikkei.academy.service.singer;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static rikkei.academy.config.ConnectMySQL.getConnection;

public class SingerServiceIMPL implements ISingerService{
    private Connection connection = ConnectMySQL.getConnection();
    Statement stmt;
    private int noOfRecords;
    private final String CREATE_SINGER = "INSERT INTO singer(name,birthday,gender,img) VALUES (?,?,?,?)";
    private final String LIST_SINGER = "SELECT * FROM singer";
    private final String SINGER_BY_ID = "SELECT * FROM singer WHERE id=?;";
    private final String DETAIL_BY_ID = "SELECT * FROM singer join songs_singer ss on singer.id = ss.idSinger join songs s on ss.idSong = s.id where s.id=?;";
    private final String UPDATE_SINGER = "UPDATE singer SET name=?, birthDay=?,gender=? WHERE id=?;";
    private final String DELETE_SINGER = "DELETE FROM singer WHERE id=?";
    private final String SEARCH_BY_NAME_SINGER = "SELECT * FROM singer WHERE name LIKE ?";
    @Override
    public List<Singer> findByName(String nameSingerSearch) {
        List<Singer> singerListSearch = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_SINGER);
            preparedStatement.setString(1, '%'+nameSingerSearch+'%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                int birthday = resultSet.getInt("birthDay");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");

                Singer singer = new Singer(id,name,birthday,gender);
                singerListSearch.add(singer);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singerListSearch;
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> singerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_SINGER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int birthday= resultSet.getInt("birthDay");
                String gender = resultSet.getString("gender");
                Singer singer = new Singer(id, name, birthday,gender);
                singerList.add(singer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singerList;
    }

    @Override
    public void save(Singer singer) {
        try {
            if (findById(singer.getId())==null){
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SINGER);
                preparedStatement.setString(1, singer.getName());
                preparedStatement.setInt(2, singer.getBirthDay());
                preparedStatement.setString(3, singer.getGender());
                preparedStatement.setString(4,singer.getImg());
                preparedStatement.executeUpdate();
            }else {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SINGER);
                preparedStatement.setString(1, singer.getName());
                preparedStatement.setInt(2, singer.getBirthDay());
                preparedStatement.setString(3, singer.getGender());
                preparedStatement.setInt(4, singer.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Singer findById(int id) {
        Singer singer = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SINGER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int birthday = resultSet.getInt("birthday");
                String gender = resultSet.getString("gender");

                singer = new Singer(id, name, birthday,gender);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return singer;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SINGER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Singer> findAllSinger(int offset, int noOfRecords) {
    String query = "select SQL_CALC_FOUND_ROWS * from singer limit "
            + offset + ", " + noOfRecords;
    List<Singer> listSinger = new ArrayList<>();
    Singer singer = null;
        try {
        connection = getConnection();
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            singer = new Singer();
            singer.setId(rs.getInt("id"));
            singer.setName(rs.getString("name"));
            singer.setImg(rs.getString("img"));
            listSinger.add(singer);
        }
        rs.close();
        rs = stmt.executeQuery("SELECT FOUND_ROWS()");
        if(rs.next())
            this.noOfRecords = rs.getInt(1);
    } catch (SQLException e) {
        e.printStackTrace();
    } finally
    {
        try {
            if(stmt != null)
                stmt.close();
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return listSinger;
}

    @Override
    public Singer detailById(int id) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(DETAIL_BY_ID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
