package dtos;

import entities.Boat;
import entities.Harbour;
import entities.Owner;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Boat} entity
 */
public class BoatDTO implements Serializable {
    private final String brand;
    private final String name;
    private final String imageUrl;
    private final Set<String> ownerSet;
//    private final HarbourDTO harbourDTO;
    private final SimpleHarbourDTO harbour;

    public BoatDTO(String brand, String name, String imageUrl, Set<String> ownerSet, SimpleHarbourDTO harbour) {
        this.brand = brand;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ownerSet = ownerSet;
//        this.harbourDTO = harbourDTO;
        this.harbour = harbour;
    }

    public BoatDTO(Boat boat) {
        this.brand = boat.getBrand();
        this.name = boat.getName();
        this.imageUrl = boat.getImageUrl();
        this.ownerSet = boat.getOwnersAsStrings();
//        this.harbourDTO = new HarbourDTO(boat.getHarbour());
        this.harbour = new SimpleHarbourDTO(boat.getHarbour());
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Set<String> getOwnerSet() {
        return ownerSet;
    }

    public SimpleHarbourDTO getHarbour() {
        return harbour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatDTO entity = (BoatDTO) o;
        return Objects.equals(this.brand, entity.brand) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.imageUrl, entity.imageUrl) &&
                Objects.equals(this.ownerSet, entity.ownerSet) &&
                Objects.equals(this.harbour, entity.harbour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, name, imageUrl, ownerSet, harbour);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "brand = " + brand + ", " +
                "name = " + name + ", " +
                "imageUrl = " + imageUrl + ", " +
                "ownerSet = " + ownerSet + ", " +
                "harbour = " + harbour + ")";
    }

    public static class SimpleHarbourDTO implements Serializable {
        private final Long id;
        private final String name;
        private final String address;
        private final Integer capacity;

        public SimpleHarbourDTO(Harbour harbour) {
            this.id = harbour.getId();
            this.name = harbour.getName();
            this.address = harbour.getAddress();
            this.capacity = harbour.getCapacity();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SimpleHarbourDTO that = (SimpleHarbourDTO) o;
            return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(capacity, that.capacity);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, address, capacity);
        }
    }



}