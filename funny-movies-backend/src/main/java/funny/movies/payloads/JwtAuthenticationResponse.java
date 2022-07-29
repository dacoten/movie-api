package funny.movies.payloads;

public class JwtAuthenticationResponse {
	private String accessToken;

	private String tokenType = "Bearer";

	private UserProfile user;

	public JwtAuthenticationResponse(String accessToken, UserProfile user) {
		this.accessToken = accessToken;
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

}