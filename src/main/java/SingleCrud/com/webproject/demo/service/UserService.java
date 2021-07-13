package SingleCrud.com.webproject.demo.service;

import SingleCrud.com.webproject.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByName(String account);

    User findById(String id);

    User login(User user);

    User updatePass(User user, String newPass);

    User update(User user, User newUser);

    User addUser(User newUser);

    User updateStatus(User user, String status);

    List<User> search(String truong, String giatri);

    boolean comparePass(String oldPass, String enCode);

}
