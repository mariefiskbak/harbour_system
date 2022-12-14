package entities;

import dtos.BoatDTO;
import dtos.HarbourDTO;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "harbour")
public class Harbour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "capacity")
    private Integer capacity;
    @OneToMany(mappedBy = "harbour")
    private Set<Boat> boats = new LinkedHashSet<>();

    public Harbour() {

    }

    public Harbour(String name, String address, Integer capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public Harbour(String name, String address, Integer capacity, Set<Boat> boats) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.boats = boats;
    }

    public Harbour(HarbourDTO harbourDTO) {
        this.name = harbourDTO.getName();
        this.address = harbourDTO.getAddress();
        this.capacity = harbourDTO.getCapacity();
        Set<BoatDTO> boatDTOS = harbourDTO.getBoats();
        Set<Boat> boats = null;
        if(boatDTOS != null) {
            for (BoatDTO boatDTO : boatDTOS) {
                boats.add(new Boat(boatDTO));
            }
        }
        this.boats = boats;
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

    public Set<Boat> getBoats() {
        return boats;
    }

    public void addBoatsToSet(Boat boat) {
        this.boats.add(boat);
        boat.setHarbour(this);
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
        Harbour harbour = (Harbour) o;
        return id.equals(harbour.id) && Objects.equals(name, harbour.name) && Objects.equals(address, harbour.address) && Objects.equals(capacity, harbour.capacity) && Objects.equals(boats, harbour.boats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, capacity, boats);
    }
}