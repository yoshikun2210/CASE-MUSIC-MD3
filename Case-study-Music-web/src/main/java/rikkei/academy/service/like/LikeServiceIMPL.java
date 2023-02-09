package rikkei.academy.service.like;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.like.Like;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeServiceIMPL implements ILikeService{
    Connection connection = ConnectMySQL.getConnection();
    private final String CREATE_LIKE= "INSERT INTO likes(idSong ,idUser) values (?,?);";
    private final String FIND_BY_ID = "SELECT * FROM likes where idSong=?;";
    private final String CHECK_LIKE = "SELECT id FROM likes where idSong=? AND idUser=?;";
    private final String DISLIKE = "DELETE FROM likes where id=?";
    @Override
    public void save(Like like) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LIKE);
            preparedStatement.setInt(1, like.getIdSong());
            preparedStatement.setInt(2, like.getIdUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Like> findByIdSong(int idSong) {
        List<Like> likeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1,idSong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               int id = resultSet.getInt("id");
               int idUser = resultSet.getInt("idUser");
               Like like = new Like(id,idSong,idUser);
               likeList.add(like);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return likeList;
    }

    @Override
    public Like checkLike(int idSong, int idUser) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LIKE);
            preparedStatement.setInt(1,idSong);
            preparedStatement.setInt(2,idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                return new Like(id,idSong,idUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void dislike(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DISLIKE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
