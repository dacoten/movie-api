package funny.movies.payloads;

import java.time.Instant;

public class UserProfile {
	private Long id;
	
	private String email;
	
	private Instant joinedAt;

	public UserProfile(Long id, String email, Instant joinedAt) {
		this.id = id;
		this.email = email;
		this.joinedAt = joinedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}

}
