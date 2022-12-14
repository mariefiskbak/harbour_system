package dtos;

import entities.Boat;
import entities.Owner;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Owner} entity
 */
public class OwnerDTO implements Serializable {
    private final String name;
    private final String address;
    private final String phone;
    private final Set<BoatDTO> boatSet;

    public OwnerDTO(String name, String address, String phone, Set<BoatDTO> boatSet) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.boatSet = boatSet;
    }

    public OwnerDTO(Owner owner) {
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
        Set<BoatDTO> boatDTOS = null;
        Set<Boat> boats = owner.getBoatSet();
        if (boats != null) {
            for (Boat boat : boats) {
                boatDTOS.add(new BoatDTO(boat));
            }
        }
        this.boatSet = boatDTOS;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Set<BoatDTO> getBoatSet() {
        return boatSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDTO entity = (OwnerDTO) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.boatSet, entity.boatSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone, boatSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "address = " + address + ", " +
                "phone = " + phone + ", " +
                "boatSet = " + boatSet + ")";
    }


}