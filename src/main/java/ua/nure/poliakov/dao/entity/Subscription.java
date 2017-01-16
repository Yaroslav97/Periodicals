package ua.nure.poliakov.dao.entity;

public class Subscription {
    private User user;
    private Edition edition;

    public Subscription() {
    }

    public Subscription(User user, Edition edition) {
        this.user = user;
        this.edition = edition;
    }

    public User getUser() {
        return user;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }
}
