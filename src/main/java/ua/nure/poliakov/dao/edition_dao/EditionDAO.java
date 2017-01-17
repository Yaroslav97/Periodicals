package ua.nure.poliakov.dao.edition_dao;

import ua.nure.poliakov.dao.entity.Edition;
import ua.nure.poliakov.dao.entity.Subscription;

import java.util.List;

public interface EditionDAO {

    void addEdition(Edition edition);

    void updateEdition(Edition edition);

    void deleteEdition(int id);

    Edition getEdition(int id);

    List<Edition> getAllSortEditions(String sort);

    boolean isContains(int id);

    void subscribe(String login, int idEdition);

    boolean isSubscribe(String login, int idEdition);

    List<Edition> getAllSubscribes(String login);

    void unsubscribe(String login, int idEdition);

    boolean isSameEdition(String name, String subject);

    List<Edition> searchByName(String name);

    List<Edition> filterByPrice(double from, double to);

    List<Edition> getEditionInfo(int id);
}