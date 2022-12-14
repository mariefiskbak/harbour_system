/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Harbour;

import javax.persistence.EntityManagerFactory;

import entities.Owner;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populateHarbours(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.createHarbour(new HarbourDTO(new Harbour("Rønne", "Fiskerivej 1, 3700 Rønne", 200)));
        fe.createHarbour(new HarbourDTO(new Harbour("Nexø", "Sdr Hammer 10, 3730 Nexø", 100)));
        fe.createHarbour(new HarbourDTO(new Harbour("Hasle", "Havnen 23, 3780 Hasle", 50)));
    }

    public static void populateBoats(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.createBoat(new BoatDTO(new Boat("Stingray", "Dreamboat", "")));
        fe.createBoat(new BoatDTO(new Boat("", "", "")));
        fe.createBoat(new BoatDTO(new Boat("", "", "")));
    }

    public static void populateOwners(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.createOwner(new OwnerDTO(new Owner("Marie Fiskbæk", "Sdr Landevej 19 B, 3730 Nexø", "88888888")));
        fe.createOwner(new OwnerDTO(new Owner("Jon Toft-Jensen", "Sdr Landevej 19 B, 3730 Nexø", "77777777")));
        fe.createOwner(new OwnerDTO(new Owner("Per Andersen", "Rønnevej 1, 3700 Rønne", "11111111")));
    }



    public static void main(String[] args) {
        populateOwners();
    }
}
