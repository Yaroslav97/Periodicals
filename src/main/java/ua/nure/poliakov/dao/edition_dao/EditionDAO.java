package ua.nure.poliakov.dao.edition_dao;

import ua.nure.poliakov.dao.entity.Edition;
import ua.nure.poliakov.dao.entity.Subscription;

import java.util.List;

public interface EditionDAO {

    public void add(Edition edition);

    public void update(Edition edition);

    public void delete(int id);

    public Edition get(int id);

    public List<Edition> getAllEditions(String sort);

    public boolean isContains(int id);

    public void subscribe(String login, int idEdition);

    public boolean isSubscribe(String login, int idEdition);

    public List<Edition> getAllSubscribes(String login);

    public void unsubscribe(String login, int idEdition);

    public boolean isSame(String name, String subject);

    public List<Edition> search(String name);

    public List<Edition> filter(double from, double to);

    public List<Edition> getEditionInfo(int id);
}