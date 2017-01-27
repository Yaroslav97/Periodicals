package ua.nure.poliakov.SummaryTask4.utils.validations.edition;

import ua.nure.poliakov.SummaryTask4.utils.exceptions.ValidationException;

/**
 *
 * Interface for validation Editions.
 *
 * @param <T>
 * @param <D>
 */

public interface EditionValidation<T, D> {

    boolean name(T name) throws ValidationException;

    boolean subject(T subject) throws ValidationException;

    boolean price(D price) throws ValidationException;
}
