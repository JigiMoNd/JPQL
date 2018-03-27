package lesson6_7.khz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args )
    {
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
      EntityManager em = factory.createEntityManager();
      
      
      
      
      
      em.close();
      factory.close();
    		  
    }
}
