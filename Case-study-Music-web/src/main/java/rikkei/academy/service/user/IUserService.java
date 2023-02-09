package rikkei.academy.service.user;

import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;
import rikkei.academy.model.account.User;
import rikkei.academy.service.IGenericService;

import java.util.List;

import java.sql.SQLException;


public interface IUserService extends IGenericService<User> {
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    User findById(int id);
    User findByUsernameAndPassword(String username, String password);
    void changeAvatar(String avatar, int id);
    void deleteById(int id);
    List<User> findAll();
    void update(User user) throws SQLException;


}
