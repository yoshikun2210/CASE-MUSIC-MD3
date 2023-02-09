package rikkei.academy.service.band;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;

import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandServiceIMPL implements IBandService{
    private Connection connection = ConnectMySQL.getConnection();
    private static final String LIST_BAND = "SELECT * FROM bands;";

    private static final String CREATE_BAND = "INSERT INTO bands (nameBand,year)VALUES(?,?);";
    private static final String UPDATE_BAND = "UPDATE bands SET nameBand=?, year=?, WHERE idBand=?;";
    private static final String BAND_BY_ID = "SELECT * FROM bands WHERE idBand=?;";
    private static final String DELETE_BAND = "DELETE FROM bands WHERE idBand=?";
    private static final String SEARCH_BY_NAME_BAND = "SELECT * FROM bands WHERE nameBand LIKE ?";
   @Override
    public void save(Band band)  {
       try {
           connection.setAutoCommit(false);
           if (findById(band.getId())==null){
               PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BAND);
               preparedStatement.setString(1, band.getName());
               preparedStatement.setInt(2, band.getYear());
               preparedStatement.executeUpdate();
           }else {
               PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BAND);
               preparedStatement.setString(1, band.getName());
               preparedStatement.setInt(2, band.getYear());
               preparedStatement.executeUpdate();
           }
    connection.commit();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


   }


    @Override
    public List<Band> findAll() {
       List<Band> bands = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LIST_BAND);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("idBand");
                String name =resultSet.getString("nameBand");
                int year = resultSet.getInt("year");
                Band band = new Band(id,name,year);
                bands.add(band);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bands;
    }

    @Override
    public Band findById(int id) {
        Band band = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BAND_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("nameBand");
                int year = resultSet.getInt("year");
                band = new Band(id,name,year);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return band;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BAND);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Band> findByName(String nameBandSearch) {
        List<Band> bandListSearch = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_BAND);
            preparedStatement.setString(1, '%'+nameBandSearch+'%');
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("nameBand");
                int year = resultSet.getInt("year");
                Band band = new Band(id,name,year);
                bandListSearch.add(band);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bandListSearch;
    }
}
