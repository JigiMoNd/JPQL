package Entiti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter @Setter
public class Comment extends BaseEntity{
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "author")
	private String author;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", author=" + author + ", getId()=" + getId() + "]";
	}
	
	
}
