package ua.nure.poliakov.dao.edition_dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ua.nure.poliakov.dao.close.Close;
import ua.nure.poliakov.dao.connection.PoolConnection;
import ua.nure.poliakov.dao.entity.Edition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditionDAOImplement implements EditionDAO {

    private static final String INSERT_INTO_EDITIONS = "INSERT INTO editions (`name`, subject, price) VALUES(?,?,?)";
    private static final String INSERT_INTO_SUBSCRIBES = "INSERT INTO subscribes (login, edition) VALUES(?,?)";
    private static final String UPDATE_EDITIONS = "UPDATE editions SET name=?, subject=?, price=? WHERE id=?";
    private static final String DELETE_EDITIONS = "DELETE FROM editions WHERE id=?";
    private static final String SELECT_EDITIONS = "SELECT * FROM editions WHERE id=?";
    private static final String SELECT_ALL_EDITIONS = "SELECT * FROM editions";
    private static final String SELECT_SUBSCRIBE = "SELECT login, id FROM subscribes WHERE login=? AND id=?";
    private static final String SELECT_ID = "SELECT id FROM editions WHERE id=?";
    private static final String SELECT_ALL_SUBSCRIBES_BY_LOGIN = "SELECT * FROM subscribes WHERE login=?";

    ComboPooledDataSource dataSource = PoolConnection.getPool();

    @Override
    public void add(Edition edition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_INTO_EDITIONS);
            preparedStatement.setString(1, edition.getName());
            preparedStatement.setString(2, edition.getSubject());
            preparedStatement.setDouble(3, edition.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public void update(Edition edition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_EDITIONS);
            preparedStatement.setString(1, edition.getName());
            preparedStatement.setString(2, edition.getSubject());
            preparedStatement.setDouble(3, edition.getPrice());
            preparedStatement.setInt(4, edition.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_EDITIONS);
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public Edition get(int id) {
        Edition edition = new Edition();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_EDITIONS);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                edition.setId(resultSet.getInt("id"));
                edition.setName(resultSet.getString("name"));
                edition.setSubject(resultSet.getString("subject"));
                edition.setPrice(resultSet.getDouble("price"));

                edition = new Edition(edition.getId(), edition.getName(), edition.getSubject(), edition.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }
        return edition;
    }

    @Override
    public List<Edition> getAllEditions() {
        Edition edition = new Edition();
        List<Edition> editionList = new ArrayList<>();
        ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_EDITIONS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                edition.setId(resultSet.getInt("id"));
                edition.setName(resultSet.getString("name"));
                edition.setSubject(resultSet.getString("subject"));
                edition.setPrice(resultSet.getDouble("price"));

                editionList.add(new Edition(edition.getId(), edition.getName(), edition.getSubject(), edition.getPrice()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }
        return editionList;
    }

    @Override
    public boolean contains(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isContain = false;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    isContain = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }
        return isContain;
    }

    @Override
    public void subscribe(String login, int idEdition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_INTO_SUBSCRIBES);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, idEdition);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(preparedStatement);
            Close.close(connection);
        }
    }

    @Override
    public boolean isSubscribe(String login, int idEdition) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isSubscribe = false;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SUBSCRIBE);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, idEdition);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(login) &&
                        resultSet.getInt("id") == idEdition) {
                    isSubscribe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }
        return isSubscribe;
    }

    @Override
    public List<Edition> getAllSubscribes(String login) {
        List<Edition> editionList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIBES_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                editionList.add(new Edition(get(resultSet.getInt("edition")).getId(),
                        get(resultSet.getInt("edition")).getName(),
                        get(resultSet.getInt("edition")).getSubject(),
                        get(resultSet.getInt("edition")).getPrice()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close.close(resultSet);
            Close.close(preparedStatement);
            Close.close(connection);
        }
        return editionList;
    }
}
