package funny.movies.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import funny.movies.models.Movie;
import funny.movies.payloads.MovieRequest;
import funny.movies.payloads.MovieResponse;
import funny.movies.payloads.PagedResponse;
import funny.movies.payloads.VoteRequest;
import funny.movies.securities.CurrentUser;
import funny.movies.securities.UserPrincipal;
import funny.movies.services.MovieService;
import funny.movies.utils.AppConstants;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping
	public PagedResponse<MovieResponse> getAllMovies(@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
		return movieService.getAllMovies(currentUser, page, size);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createMovie(@Valid @RequestBody MovieRequest movieRequest) {
		Movie movie = movieService.createMovie(movieRequest);

		return new ResponseEntity(movie, HttpStatus.OK);
	}
	
	@PostMapping("/{movieId}/votes")
	@PreAuthorize("hasRole('USER')")
	public MovieResponse castVoted(@CurrentUser UserPrincipal currentUser, @PathVariable Long movieId, @Valid @RequestBody VoteRequest voteRequest) {
		return movieService.castVoteAndGetUpdatedMovie(movieId, voteRequest, currentUser);
	}
}
