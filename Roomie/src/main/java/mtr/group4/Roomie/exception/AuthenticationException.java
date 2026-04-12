package mtr.group4.Roomie.exception;

public class AuthenticationException extends RoomieException {
    public AuthenticationException() {
        super("Invalid email or password.");
    }
}