package ua.nure.poliakov.dao.user_dao;

import ua.nure.poliakov.dao.entity.User;

import java.util.List;

public interface UserDAO {

    public void add(User user);

    public void update(User user);

    public void delete(String login);

    public User getByLogin(String login);

    public List<User> getAllUsers();

    public void ban(String login, boolean status);

    public boolean contains(String login);

    public void updatePassword(String login, String password);

    public void updateScore(String login, String operation, double score);

    public double getScore(String login);

    public List<User> getAllUsersByRole(String role);
}
