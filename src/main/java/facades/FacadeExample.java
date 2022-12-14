package facades;

import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import dtos.RenameMeDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public HarbourDTO createHarbour(HarbourDTO harbourDTO){
        Harbour harbour = new Harbour(harbourDTO);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(harbour);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HarbourDTO(harbour);
    }

    public BoatDTO createBoat(BoatDTO boatDTO) {
        Boat boat = new Boat(boatDTO);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }

    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = new Owner(ownerDTO);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new OwnerDTO(owner);
    }
    public RenameMeDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        RenameMe rm = em.find(RenameMe.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new RenameMeDTO(rm);
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = getEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }
    
    public List<RenameMeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<RenameMe> query = em.createQuery("SELECT r FROM RenameMe r", RenameMe.class);
        List<RenameMe> rms = query.getResultList();
        return RenameMeDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }


}
