package mtr.group4.Roomie.exception;

public class DuplicateEmailException extends RoomieException {
    public DuplicateEmailException(String email) {
        super("An account with email '" + email + "' already exists.");
    }
}