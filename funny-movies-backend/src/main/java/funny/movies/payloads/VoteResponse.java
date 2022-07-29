package funny.movies.payloads;

import java.time.Instant;

import funny.movies.models.VotedName;

public class VoteResponse {
	private Long id;

	private VotedName votedName;

	private Instant createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VotedName getVotedName() {
		return votedName;
	}

	public void setVotedName(VotedName votedName) {
		this.votedName = votedName;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

}
