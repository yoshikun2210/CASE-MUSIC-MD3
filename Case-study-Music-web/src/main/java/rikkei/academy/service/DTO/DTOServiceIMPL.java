package rikkei.academy.service.DTO;

import rikkei.academy.config.ConnectMySQL;
import rikkei.academy.model.DTO.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DTOServiceIMPL implements IDTOService{
    Connection connection = ConnectMySQL.getConnection();
    private final String SELECT_ALL = "";
    @Override
    public List<DTO> findAll() {
        List<DTO> dtoList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
