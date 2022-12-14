package dtos;

import entities.Boat;
import entities.Owner;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link entities.Boat} entity
 */
public class BoatDTO implements Serializable {
    private final String brand;
    private final String name;
    private final String imageUrl;
    private final Set<OwnerDTO> ownerSet;
    private final HarbourDTO harbourDTO;

    public BoatDTO(String brand, String name, String imageUrl, Set<OwnerDTO> ownerSet, HarbourDTO harbourDTO) {
        this.brand = brand;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ownerSet = ownerSet;
        this.harbourDTO = harbourDTO;
    }

    public BoatDTO(Boat boat) {
        this.brand = boat.getBrand();
        this.name = boat.getName();
        this.imageUrl = boat.getImageUrl();
        Set<OwnerDTO> ownerDTOS = null;
        Set<Owner> owners = boat.getOwnerSet();
        if (owners != null) {
            for (Owner owner : owners) {
                ownerDTOS.add(new OwnerDTO(owner));
            }
        }
        this.ownerSet = ownerDTOS;
        this.harbourDTO = new HarbourDTO(boat.getHarbour());
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

    public Set<OwnerDTO> getOwnerSet() {
        return ownerSet;
    }

    public HarbourDTO getHarbourDTO() {
        return harbourDTO;
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
                Objects.equals(this.harbourDTO, entity.harbourDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, name, imageUrl, ownerSet, harbourDTO);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "brand = " + brand + ", " +
                "name = " + name + ", " +
                "imageUrl = " + imageUrl + ", " +
                "ownerSet = " + ownerSet + ", " +
                "harbourDTO = " + harbourDTO + ")";
    }

}