package ua.nure.poliakov.SummaryTask4.utils.validations.user;

import javax.xml.bind.ValidationException;

public interface UserValidate<T, D> {
    boolean name(T name) throws ValidationException;

    boolean login(T login) throws ValidationException;

    boolean email(T email) throws ValidationException;

    boolean password(T password) throws ValidationException;

    boolean score(D score) throws ValidationException;
}
