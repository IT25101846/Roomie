package mtr.group4.Roomie.exception;

public class RoomNotAvailableException extends RoomieException {
    public RoomNotAvailableException(String roomId) {
        super("Room " + roomId + " is not available for booking.");
    }
}