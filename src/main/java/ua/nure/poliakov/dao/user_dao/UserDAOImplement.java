package ua.nure.poliakov.dao.user_dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ua.nure.poliakov.dao.close.Close;
import ua.nure.poliakov.dao.connection.PoolConnection;
import ua.nure.poliakov.dao.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplement implements UserDAO {

    private static final String INSERT_USERS = "INSERT INTO users (fullName, login, email, ban, password) " +
            "VALUES(?, ?, ?, ?, ?)";
    private static final String INSERT_SCORE = "INSERT INTO user_score (login, score) VALUES(?, ?)";
    private static final String INSERT_ROLE = "INSERT INTO user_role (login, role) VALUES(?, ?)";
    private static final String INSERT_SETTING = "INSERT INTO settings (login, notification) VALUES(?, ?)";
    private static final String UPDATE_USERS = "UPDATE users SET fullName=?, email=?, password=? WHERE login=?";
    private static final String UPDATE_BAN_STATUS = "UPDATE users SET ban=? WHERE login=?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password=? WHERE login=?";
    private static final String UPDATE_SCORE_REFILL = "UPDATE user_score SET score=score+? WHERE login=?";
    private static final String UPDATE_SCORE_WITHDRAW = "UPDATE user_score SET score=score-? WHERE login=?";
    private static final String UPDATE_SETTING = "UPDATE settings SET notification=?  WHERE login=?";
    private static final String DELETE_USERS = "DELETE FROM users WHERE login=?";
    private static final String DELETE_SCORE = "DELETE FROM user_score WHERE login=?";
    private static final String DELETE_ROLE = "DELETE FROM user_role WHERE login=?";
    private static final String DELETE_SETTING = "DELETE FROM settings WHERE login=?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT users.*, user_score.score, user_role.role " +
            "FROM users, user_role, user_score WHERE users.login=? AND users.login = user_role.login " +
            "AND users.login = user_score.login";
    private static final String SELECT_ALL_USERS = "SELECT users.*, user_score.score, user_role.role " +
            "FROM users, user_role, user_score WHERE users.login = user_role.login " +
            "AND users.login = user_score.login";
    private static final String SELECT_ALL_USERS_BY_ROLE = "SELECT users.*, user_score.score, user_role.role " +
            "FROM users, user_role, user_score WHERE role=? AND users.login = user_role.login " +
            "AND users.login = user_score.login";
    private static final String SELECT_LOGIN = "SELECT login FROM users WHERE login=?";
    private static final String SELECT_SCORE = "SELECT score FROM user_score WHERE login=?";
    private static final String SELECT_SETTING = "SELECT notification FROM settings WHERE login=?";
    private static final String SELECT_SUBSCRIBERS = "SELECT users.fullName, users.login, users.email, users.ban " +
            "FROM users, subscribes WHERE subscribes.edition = ? AND users.login = subscribes.login";
    private static final String SELECT_USER_BY_NAME = "SELECT users.*, user_score.score, user_role.role " +
            "FROM users, user_role, user_score WHERE fullName LIKE ? AND users.login = user_role.login " +
            "AND users.login = user_score.login ORDER BY fullName";

    ComboPooledDataSource dataSource = PoolConnection.getPool();

    @Override
    public void add(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_USERS);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getBan() ? 1 : 0);
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(INSERT_SCORE);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setDouble(2, user.getScore());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(INSERT_ROLE);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(INSERT_SETTING);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, "1");
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.err.println("RollbackException");
            }
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public void update(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USERS);

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getLogin());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.printf("Exception: update profile for - %s", user.getLogin());
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public void delete(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_USERS);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_SCORE);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_ROLE);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DELETE_SETTING);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public User getByLogin(String login) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setFullName(resultSet.getString("fullName"));
                user.setLogin(resultSet.getString("users.login"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setScore(resultSet.getDouble("score"));
                user.setRole(resultSet.getString("role"));

                if (resultSet.getInt("ban") == 0) {
                    user.setBan(false);
                } else {
                    user.setBan(true);
                }

                user = new User(user.getFullName(), user.getLogin(), user.getEmail(), user.getScore(),
                        user.getRole(), user.getBan(), user.getPassword());

            }
        } catch (SQLException e) {
            System.err.println("Exception: getByLogin " + login);
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        User user = new User();
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setFullName(resultSet.getString("fullName"));
                user.setLogin(resultSet.getString("users.login"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setScore(resultSet.getDouble("score"));
                user.setRole(resultSet.getString("role"));
                if (resultSet.getInt("ban") == 0) {
                    user.setBan(false);
                } else {
                    user.setBan(true);
                }
                userList.add(new User(user.getFullName(), user.getLogin(), user.getEmail(), user.getScore(),
                        user.getRole(), user.getBan(), user.getPassword()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return userList;
    }

    @Override
    public void ban(String login, boolean status) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_BAN_STATUS);
            preparedStatement.setInt(1, status ? 1 : 0);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public boolean contains(String login) {
        Boolean isContains = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login)) {
                    isContains = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return isContains;
    }

    @Override
    public void updatePassword(String login, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception: update password ==> " + login);
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public void updateScore(String login, String operation, double score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();

            switch (operation) {
                case "refill":
                    preparedStatement = connection.prepareStatement(UPDATE_SCORE_REFILL);
                    break;
                case "withdraw":
                    preparedStatement = connection.prepareStatement(UPDATE_SCORE_WITHDRAW);
                    break;
                default:
                    throw new SQLException();
            }

            preparedStatement.setDouble(1, score);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Exception: updateScore ==> " + login);
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public double getScore(String login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double score = 0;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SCORE);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                score = resultSet.getDouble("score");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return score;
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_BY_ROLE);
            preparedStatement.setString(1, role);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setFullName(resultSet.getString("fullName"));
                user.setLogin(resultSet.getString("users.login"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setScore(resultSet.getDouble("score"));
                user.setRole(resultSet.getString("role"));
                user.setBan(resultSet.getInt("ban") == 0 ? false : true);
                userList.add(new User(user.getFullName(), user.getLogin(), user.getEmail(),
                        user.getScore(), user.getRole(), user.getBan(), user.getPassword()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return userList;
    }

    @Override
    public void updateSettings(String login, Boolean sendEmail) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SETTING);
            preparedStatement.setString(2, login);
            preparedStatement.setInt(1, sendEmail ? 1 : 0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public boolean getSettings(String login) {
        boolean notification = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SETTING);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("notification") == 1) {
                    notification = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return notification;
    }

    @Override
    public List<User> getSubscribers(int idEdition) {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SUBSCRIBERS);
            preparedStatement.setInt(1, idEdition);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString("fullName"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getInt("ban") == 0 ? false : true));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return userList;
    }

    @Override
    public List<User> search(String fullName) {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME);
            preparedStatement.setString(1, "%"+ fullName + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getString("fullName"),
                        resultSet.getString("users.login"),
                        resultSet.getString("email"),
                        resultSet.getDouble("score"),
                        resultSet.getString("role"),
                        resultSet.getInt("ban") == 0 ? false : true,
                        resultSet.getString("password")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }

        return userList;
    }
}