package entities;

import dtos.BoatDTO;
import dtos.OwnerDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;

    @JoinTable(name = "owner_boats", joinColumns = {
            @JoinColumn(name = "owner_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "boat_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<Boat> boatSet = new HashSet<>();

    public Owner() {
    }

    public Owner(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Owner(OwnerDTO ownerDTO) {
        this.name = ownerDTO.getName();
        this.address = ownerDTO.getAddress();
        this.phone = ownerDTO.getPhone();
        Set<Boat> boats = null;
        Set<BoatDTO> boatDTOS = ownerDTO.getBoatSet();
        if (boatDTOS != null) {
            for (BoatDTO boatDTO : boatDTOS) {
                boats.add(new Boat(boatDTO));
            }
        }
        this.boatSet = boats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Boat> getBoatSet() {
        return boatSet;
    }
}