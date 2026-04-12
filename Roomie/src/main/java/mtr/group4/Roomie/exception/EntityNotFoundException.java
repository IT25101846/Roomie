package mtr.group4.Roomie.exception;

public class EntityNotFoundException extends RoomieException {
    public EntityNotFoundException(String entityType, String id) {
        super(entityType + " with id '" + id + "' was not found.");
    }
}