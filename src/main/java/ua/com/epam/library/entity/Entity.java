package ua.com.epam.library.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Entity<T> implements Serializable {

    private long id;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Entity() {
    }

    public Entity(long id,
                  LocalDateTime created,
                  LocalDateTime updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
