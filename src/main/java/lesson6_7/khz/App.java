package lesson6_7.khz;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entiti.Comment;
import Entiti.Post;
import Entiti.Tag;
import Entiti_enum.Status;

public class App 
{
    public static void main( String[] args )
    {
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
      EntityManager em = factory.createEntityManager();
      
      em.getTransaction().begin();
      
      List<Comment> comments = em.createQuery("SELECT c FROM Comment c", Comment.class)
    		  .getResultList();
//      comments.forEach(c -> System.out.println(c));
      
      Comment commentById = em.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class)
    		  .setParameter("id", 43).getSingleResult();
      System.out.println(commentById);
      
//      addTags(em);
//      addPost(em);
//      addComment(em);
      
      
      
      
      em.getTransaction().commit();
      em.close();
      factory.close();
    		  
    }
    
    private static void addTags(EntityManager em) {
    	List<String> tags = new ArrayList<>(); //змінив версію на 1.7
    	tags.add("Java");
    	tags.add("SQL");
    	tags.add("ORM");
    	tags.add("JPA");
    	tags.add("STS");
    	
    	for (int i = 0; i < tags.size(); i++) {
    		Tag tag = new Tag();
    		tag.setName(tags.get(i));
			
    		em.persist(tag);
		}
    }
    
    private static void addPost(EntityManager em) {
    	for (int i = 0; i < 100; i++) {
			Post post = new Post();
			post.setTitle("Post title#" + i);
    		post.setContent("Long post content#" + i);
    		
    		if(i % 2 == 0) post.setStatus(Status.DRAFT);
    		if(i % 2 == 1) post.setStatus(Status.PUBLISH);
    		
    		em.persist(post);
    		
    		List<Tag> tags = em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    		post.setTags(tags);
    		
    		
		}
    }
    
    private static void addComment(EntityManager em) {
    	for (int i = 1; i < 100; i++) {
    		Post post = em.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class)
    				.setParameter("id", i).getSingleResult();
    		
    		Comment comments = new Comment();
    		comments.setAuthor("Author #" + i);
    		comments.setComment("text o comment #" + i);
    		comments.setPost(post);
    		
    		em.persist(comments);
    	}
    } 
}
