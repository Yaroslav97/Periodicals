package ua.nure.poliakov.dao.edition_dao;

import ua.nure.poliakov.dao.entity.Edition;

import java.util.List;

public interface EditionDAO {

    public void add(Edition edition);

    public void update(Edition edition);

    public void delete(int id);

    public Edition get(int id);

    public List<Edition> getAllEditions();

    public boolean contains(int id);

    public void subscribe(String login, int idEdition);

    public boolean isSubscribe(String login, int idEdition);

    public List<Edition> getAllSubscribes(String login);
}