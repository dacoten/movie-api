package funny.movies.models;

import funny.movies.models.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie extends UserDateAudit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private String movieUrl;

	public Movie() {

	}

	public Movie(Long id, String title, String description, String movieUrl) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.movieUrl = movieUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

}
