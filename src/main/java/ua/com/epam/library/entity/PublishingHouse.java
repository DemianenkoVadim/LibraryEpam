package ua.com.epam.library.entity;

import java.util.Objects;

public class PublishingHouse extends Entity<PublishingHouse> implements Identifiable {

    private String name;

    public PublishingHouse(String name) {
        this.name = name;
    }

    public PublishingHouse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublishingHouse that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
