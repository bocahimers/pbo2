package appjaket;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class AppJaket {

    public static void main(String[] args) {
        // Admin
        Admin objJkt = new Admin();
    
        objJkt.setPassword("12345");
        objJkt.setNama("Marwanto");
        //tambah admin
       AppJaket tambah = new AppJaket();
        tambah.Add(objJkt);
        tambah.show(); 
    
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AppJaketPU");
        EntityManager em = emf.createEntityManager();
        //update admin
        em.getTransaction().begin();
        em.createQuery("UPDATE Admin e " + 
                        "SET e.nama = ?1 " + 
                        "WHERE e.password = ?2 ")
                .setParameter(1,"Maranto")
                .setParameter(2,"12345")
                .executeUpdate();
        em.getTransaction().commit();  
        //hapus admin
        em.getTransaction().begin();
        String HpHapus = "12345";
        int hasilDel = 
                em.createQuery("DELETE FROM Admin c WHERE c.password = :par")
                .setParameter("par",HpHapus).executeUpdate();
        em.getTransaction().commit();        
    }
    public void Add(Object object) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AppJaketPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public void show() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("AppJaketPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(" SELECT e FROM Admin e");
        List<Admin> result = query.getResultList();
        for (Admin e : result) {
            System.out.print(e.getId() + "/" + e.getPassword()+ "/" +e.getNama());
        }
    }
}    