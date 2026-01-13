package service;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public boolean register(String username, String rawPassword) throws Exception {

        String hashedPassword = PasswordUtil.hash(rawPassword);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);

        return userDAO.save(user);
    }

    public boolean login(String username, String rawPassword) throws Exception {

        User user = userDAO.findByUsername(username);

        if (user == null) return false;

        return PasswordUtil.verify(rawPassword, user.getPassword());
    }
}

