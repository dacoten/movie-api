package funny.movies.payloads;

import java.time.Instant;

public class MovieResponse {
	private Long id;

	private String title;

	private String description;

	private String movieUrl;

	private UserProfile shareBy;

	private Instant createdAt;

	private VoteResponse votedMovie;

	private Long totalVotedUp;

	private Long totalVotedDown;

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

	public UserProfile getShareBy() {
		return shareBy;
	}

	public void setShareBy(UserProfile shareBy) {
		this.shareBy = shareBy;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public VoteResponse getVotedMovie() {
		return votedMovie;
	}

	public void setVotedMovie(VoteResponse votedMovie) {
		this.votedMovie = votedMovie;
	}

	public Long getTotalVotedUp() {
		return totalVotedUp;
	}

	public void setTotalVotedUp(Long totalVotedUp) {
		this.totalVotedUp = totalVotedUp;
	}

	public Long getTotalVotedDown() {
		return totalVotedDown;
	}

	public void setTotalVotedDown(Long totalVotedDown) {
		this.totalVotedDown = totalVotedDown;
	}

}
