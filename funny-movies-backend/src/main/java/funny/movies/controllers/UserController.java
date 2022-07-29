package funny.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import funny.movies.exceptions.ResourceNotFoundException;
import funny.movies.models.User;
import funny.movies.payloads.UserIdentityAvailability;
import funny.movies.payloads.UserProfile;
import funny.movies.repositories.UserRepository;
import funny.movies.securities.CurrentUser;
import funny.movies.securities.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users/me")
	public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		User user = userRepository.findByEmail(currentUser.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email", currentUser.getEmail()));

		UserProfile userProfile = new UserProfile(currentUser.getId(), currentUser.getEmail(), user.getCreatedAt());
		return userProfile;
	}

	@GetMapping("/users/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}
}
