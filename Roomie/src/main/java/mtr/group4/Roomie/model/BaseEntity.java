package mtr.group4.Roomie.model;

public abstract class BaseEntity {

    private String createdDate;

    protected BaseEntity() {
        this.createdDate = java.time.LocalDate.now().toString();
    }

    protected BaseEntity(String createdDate) {
        this.createdDate = createdDate;
    }

    public abstract String getEntityType();

    public String getCreatedDate()            { return createdDate; }
    public void   setCreatedDate(String date) { this.createdDate = date; }
}