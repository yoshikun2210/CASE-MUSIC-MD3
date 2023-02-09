package rikkei.academy.service.song;

import rikkei.academy.model.music.Category;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static rikkei.academy.config.ConnectMySQL.getConnection;

public class SongServiceIMPL implements ISongService {
    private Connection connection = getConnection();
    Statement stmt;
    private int noOfRecords;
    private final String LIST_SONG = "SELECT * FROM songs;";
    private final String ADD_SONG = "INSERT INTO songs (name, listen,img , audio)values (?,?,?,?);";
    private final String UPDATE_SONG = "UPDATE songs SET name = ?, listen = ? , img = ? WHERE id=?;";
    private final String SONG_SEARCH = "SELECT * FROM songs WHERE name LIKE ?;";
    private final String SONG_BY_ID = "SELECT * FROM songs WHERE id=?;";
    private final String DELETE_SONG = "DELETE FROM songs WHERE id=?;";
    private final String INSET_SONG_CATEGORY = "INSERT INTO songs_category VALUES (?, ?);";
    private final String INSET_SONG_SINGER = "INSERT INTO songs_singer VALUES (?,?);";
    private final String INSET_SONG_BAND = "INSERT INTO songs_singer VALUES (?,?);";
    private final String UPDATE_LISTEN = "UPDATE songs SET listen = ? WHERE id=?;";


    @Override
    public void save(Song song) {
        try {
            if (findById(song.getId())==null){
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_SONG, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, song.getName());
                preparedStatement.setInt(2, song.getListen());
                preparedStatement.setString(3, song.getImg());
                preparedStatement.setString(4, song.getAudio());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                while (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
                PreparedStatement preparedStatement1 = connection.prepareStatement(INSET_SONG_CATEGORY);
                Set<Category> categories = song.getCategories();
                List<Category> categoryList = new ArrayList<>(categories);
                for (int i = 0; i < categoryList.size(); i++) {
                    preparedStatement1.setInt(1,id);
                    preparedStatement1.setInt(2,categoryList.get(i).getId());
                    preparedStatement1.executeUpdate();
                }
                PreparedStatement preparedStatement2 = connection.prepareStatement(INSET_SONG_SINGER);
                List<Integer> singerId = song.getSingerIdList();
                for (int i = 0; i < singerId.size(); i++) {
                    preparedStatement2.setInt(1,id);
                    preparedStatement2.setInt(2,singerId.get(i));
                    preparedStatement2.executeUpdate();
                }
                PreparedStatement preparedStatement3 = connection.prepareStatement(INSET_SONG_BAND);
                List<Integer> bandId = song.getBandIdList();
                for (int i = 0; i < bandId.size(); i++) {
                    preparedStatement3.setInt(1,id);
                    preparedStatement3.setInt(2,bandId.get(i));
                    preparedStatement3.executeUpdate();
                }
                connection.commit();
            }else {
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SONG);
                preparedStatement.setString(1, song.getName());
                preparedStatement.setInt(2, song.getListen());
                preparedStatement.setString(3, song.getImg());
                preparedStatement.setInt(4, song.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Song> findByName(String songSearch) {
        List<Song> songListSearch = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SONG_SEARCH);
            preparedStatement.setString(1, '%'+songSearch+'%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int listen = resultSet.getInt("listen");
                String img = resultSet.getString("img");
                String audio = resultSet.getString("audio");
                Song song = new Song(id,name,listen,img, audio);
                songListSearch.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songListSearch;
    }

    @Override
    public List<Song> findAll() {
        List<Song> songList = new ArrayList<Song>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_SONG);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             int id =   resultSet.getInt("id");
             String name=  resultSet.getString("name");
             int listen = resultSet.getInt("listen");
             String img = resultSet.getString("img");
             Song song = new Song(id, name, listen,img);
             songList.add(song);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songList;
    }

    @Override
    public Song findById(int id) {
        Song song = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SONG_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int listen = resultSet.getInt("listen");
                String img = resultSet.getString("img");
                String audio = resultSet.getString("audio");
                song = new Song(id, name, listen,img, audio);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return song;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SONG);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Song> findAllSongs(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from songs limit "
                + offset + ", " + noOfRecords;
        List<Song> list = new ArrayList<Song>();
        Song song = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                song = new Song();
                song.setId(rs.getInt("id"));
                song.setName(rs.getString("name"));
                song.setListen(rs.getInt("listen"));
                song.setImg(rs.getString("img"));
                song.setAudio(rs.getString("audio"));
                list.add(song);
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
        return list;
    }

    @Override
    public void listen(Song song) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LISTEN);
            preparedStatement.setInt(1,song.getListen()+1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

}
