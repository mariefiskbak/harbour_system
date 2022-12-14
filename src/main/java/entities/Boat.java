package entities;

import dtos.BoatDTO;
import dtos.OwnerDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "name")
    private String name;
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(mappedBy = "boatSet")
    private Set<Owner> ownerSet;

    @ManyToOne
    @JoinColumn(name = "harbour_id", nullable = false)
    private Harbour harbour;

    public Boat() {
    }

    public Boat(String brand, String name, String imageUrl) {
        this.brand = brand;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Boat(BoatDTO boatDTO) {
        this.brand = boatDTO.getBrand();
        this.name = boatDTO.getName();
        this.imageUrl = boatDTO.getImageUrl();
        Set<Owner> owners = null;
        Set<OwnerDTO> ownerDTOS = boatDTO.getOwnerSet();
        if (ownerDTOS != null) {
            for (OwnerDTO ownerDTO : ownerDTOS) {
                owners.add(new Owner(ownerDTO));
            }
        }
        this.ownerSet = owners;
        this.harbour = new Harbour(boatDTO.getHarbourDTO());

    }


    public Set<Owner> getOwnerSet() {
        return ownerSet;
    }

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boat boat = (Boat) o;
        return Objects.equals(id, boat.id) && Objects.equals(brand, boat.brand) && Objects.equals(name, boat.name) && Objects.equals(imageUrl, boat.imageUrl) && Objects.equals(ownerSet, boat.ownerSet) && Objects.equals(harbour, boat.harbour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, name, imageUrl, ownerSet, harbour);
    }
}