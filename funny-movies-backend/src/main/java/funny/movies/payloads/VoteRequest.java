package funny.movies.payloads;

import funny.movies.models.VotedName;

public class VoteRequest {
	private VotedName votedName;

	public VotedName getVotedName() {
		return votedName;
	}

	public void setVotedName(VotedName votedName) {
		this.votedName = votedName;
	}

}
