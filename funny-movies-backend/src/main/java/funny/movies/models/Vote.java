package funny.movies.models;

import javax.persistence.*;

import funny.movies.models.audit.DateAudit;

@Entity
@Table(name = "votes", uniqueConstraints = { @UniqueConstraint(columnNames = { "movie_id", "user_id" }) })
public class Vote extends DateAudit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "movie_id", nullable = false)
	private Movie movie;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(length = 60)
	private VotedName votedName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VotedName getVotedName() {
		return votedName;
	}

	public void setVotedName(VotedName votedName) {
		this.votedName = votedName;
	}

}
