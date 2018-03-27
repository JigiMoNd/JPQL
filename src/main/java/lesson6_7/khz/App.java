package lesson6_7.khz;

import java.util.ArrayList;
import java.util.Arrays;
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
      
//      List<Comment> comments = em.createQuery("SELECT c FROM Comment c", Comment.class)
//    		  .getResultList();
//      comments.forEach(c -> System.out.println(c));
//      
//      Comment commentById = em.createQuery("SELECT c FROM Comment c WHERE c.id = :any_value", Comment.class)
//    		  .setParameter("any_value", 43).getSingleResult();
//      System.out.println(commentById);
//      
//      List<Post> post = em.createQuery("SELECT p FROM Post p WHERE p.id > :post_id", Post.class)
//    		  .setParameter("post_id", 50).getResultList();
//      post.forEach(p -> System.out.println(p));
//      
//      List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id IN (:ids)", Post.class)
//    		  .setParameter("ids", Arrays.asList(2, 56, 47, 34, 55)).getResultList();
//      posts.forEach(p -> System.out.println(p));
//      
//      List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.title LIKE :p_title", Post.class)
//    		  .setParameter("p_title", "%8_").getResultList();
//      posts.forEach(p -> System.out.println(p));
//      
//      List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id BETWEEN :first AND :last", Post.class)
//    		  .setParameter("first", 76)
//    		  .setParameter("last", 89).getResultList();
//      posts.forEach(p -> System.out.println(p));
      
//      
//      Long count = em.createQuery("SELECT count(c.id) FROM Comment c", Long.class).getSingleResult();
//      System.out.println("Count: " + count);
//      
//      Long sum = em.createQuery("SELECT sum(c.id) FROM Comment c", Long.class).getSingleResult();
//      System.out.println("Sum: " + sum);
//      
//      Double avg = em.createQuery("SELECT avg(c.id) FROM Comment c", Double.class).getSingleResult();
//      System.out.println("Avg " + avg);

      Integer max = em.createQuery("SELECT max(c.id) FROM Comment c", Integer.class).getSingleResult();
      System.out.println("Max " + max);
      
      Integer min = em.createQuery("SELECT min(c.id) FROM Comment c", Integer.class).getSingleResult();
      System.out.println("Min " + min);
      
      
      
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
