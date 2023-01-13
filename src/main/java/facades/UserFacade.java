package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public void populate() {
        EntityManager em = emf.createEntityManager();
        User user = new User("user", "As123456");
        User admin = new User("admin", "JK123456");
        User both = new User("user_admin", "DQ123456");

        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test"))
            throw new UnsupportedOperationException("You have not changed the passwords");
        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();
//        System.out.println("PW: " + user.getUserPass());
//        System.out.println("Testing user with OK password: " + user.verifyPassword("As123456"));
//        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
        System.out.println("Created TEST Users");


    }


    public User createUser(User user) {
        User createdUser = user;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role userRole = new Role("user");
        user.addRole(userRole);
        em.persist(user);
        em.getTransaction().commit();
        return createdUser;
    }

    public List<BoatDTO.SimpleHarbourDTO> getAllHarbours() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Harbour> query = em.createQuery("select h from Harbour h", entities.Harbour.class);
            List<Harbour> harbours = query.getResultList();
            //TODO lav som metode inde i Harbour
            List<BoatDTO.SimpleHarbourDTO> harbourDTOList = new ArrayList<>();
            for (Harbour harbour : harbours) {
                BoatDTO.SimpleHarbourDTO harbourDTO = new BoatDTO.SimpleHarbourDTO(harbour);
                harbourDTOList.add(harbourDTO);
            }
            return harbourDTOList;
        } finally {
            em.close();
        }
    }

    public List<BoatDTO> getBoatsFromHarbour(int harbourId) {
        EntityManager em = emf.createEntityManager();
        try {
            Harbour harbour = em.createQuery("select h from Harbour h where h.id = :harbourId", entities.Harbour.class)
                    .setParameter("harbourId", harbourId).getSingleResult();
            List<Boat> boats = em.createQuery("select b from Boat b where b.harbour = :harbour", entities.Boat.class)
                    .setParameter("harbour", harbour).getResultList();
            //TODO lav som metode inde i Boats
            List<BoatDTO> boatDTOList = new ArrayList<>();
            for (Boat boat : boats) {
                BoatDTO boatDTO = new BoatDTO(boat);
                boatDTOList.add(boatDTO);
            }
            return boatDTOList;
        } finally {
            em.close();
        }
    }

    public List<OwnerDTO> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Owner> query = em.createQuery("select o from Owner o", entities.Owner.class);
            List<Owner> owners = query.getResultList();
            //TODO lav som metode inde i Owner
            List<OwnerDTO> ownerDTOList = new ArrayList<>();
            for (Owner owner : owners) {
                OwnerDTO ownerDTO = new OwnerDTO(owner);
                ownerDTOList.add(ownerDTO);
            }
            return ownerDTOList;
        } finally {
            em.close();
        }
    }
}
