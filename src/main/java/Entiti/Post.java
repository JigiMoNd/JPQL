package Entiti;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import Entiti_enum.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter @Setter
public class Post extends BaseEntity{

	@Column(name = "title", length = 100)
	private String title;
	
	@Column(name = "content", length = 1000)
	private String content;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<Comment>();
	
	@ManyToMany
	@JoinTable(name = "post_tag",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<Tag>();
	
	@OneToOne(cascade = { 
			CascadeType.DETACH, 
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH},
			fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", status=" + status + ", getId()=" + getId() + "]";
	}
	
	
	
	
	
	
	
}
