package dtos;

import entities.Boat;
import entities.Harbour;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Harbour} entity
 */
public class HarbourDTO implements Serializable {
    private final String name;
    private final String address;
    private final Integer capacity;
    private final Set<BoatDTO> boats;

    public HarbourDTO(String name, String address, Integer capacity, Set<BoatDTO> boats) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.boats = boats;
    }

    public HarbourDTO(Harbour harbour) {
        this.name = harbour.getName();
        this.address = harbour.getAddress();
        this.capacity = harbour.getCapacity();
        Set<BoatDTO> boatDTOS = null;
        Set<Boat> boats = harbour.getBoats();
        for (Boat boat : boats) {
            boatDTOS.add(new BoatDTO(boat));
        }
        this.boats = boatDTOS;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Set<BoatDTO> getBoats() {
        return boats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HarbourDTO entity = (HarbourDTO) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.capacity, entity.capacity) &&
                Objects.equals(this.boats, entity.boats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, capacity, boats);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "address = " + address + ", " +
                "capacity = " + capacity + ", " +
                "boats = " + boats + ")";
    }

}