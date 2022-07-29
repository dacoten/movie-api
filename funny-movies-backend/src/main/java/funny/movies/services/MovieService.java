package funny.movies.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import funny.movies.exceptions.BadRequestException;
import funny.movies.exceptions.ResourceNotFoundException;
import funny.movies.models.Movie;
import funny.movies.models.User;
import funny.movies.models.Vote;
import funny.movies.models.VotedName;
import funny.movies.payloads.MovieRequest;
import funny.movies.payloads.MovieResponse;
import funny.movies.payloads.PagedResponse;
import funny.movies.payloads.UserProfile;
import funny.movies.payloads.VoteRequest;
import funny.movies.payloads.VoteResponse;
import funny.movies.repositories.MovieRepository;
import funny.movies.repositories.UserRepository;
import funny.movies.repositories.VoteRepository;
import funny.movies.securities.UserPrincipal;
import funny.movies.utils.Helpers;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private UserRepository userRepository;

	public PagedResponse<MovieResponse> getAllMovies(UserPrincipal currentUser, int page, int size) {

		// Retrieve Movies
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		Page<Movie> movies = movieRepository.findAll(pageable);

		if (movies.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.emptyList(), movies.getNumber(), movies.getSize(),
					movies.getTotalElements(), movies.getTotalPages(), movies.isLast());
		}

		List<MovieResponse> movieResponses = movies.map(movie -> {
			return mapMovieToMovieResponse(movie, currentUser);
		}).getContent();

		return new PagedResponse<>(movieResponses, movies.getNumber(), movies.getSize(), movies.getTotalElements(),
				movies.getTotalPages(), movies.isLast());
	}

	public Movie createMovie(MovieRequest movieRequest) {
		Movie movie = new Movie();

		movie.setTitle(movieRequest.getTitle());
		movie.setDescription(movieRequest.getDescription());
		movie.setMovieUrl(movieRequest.getMovieUrl());

		return movieRepository.save(movie);
	}

	public MovieResponse castVoteAndGetUpdatedMovie(Long movieId, VoteRequest voteRequest, UserPrincipal currentUser) {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

		User user = userRepository.getReferenceById(currentUser.getId());

		Vote vote = new Vote();
		vote.setMovie(movie);
		vote.setUser(user);
		vote.setVotedName(voteRequest.getVotedName());

		try {
			vote = voteRepository.save(vote);
		} catch (DataIntegrityViolationException ex) {
			throw new BadRequestException("Sorry! You have already cast your vote in this movie");
		}

		// -- Vote Saved, Return the updated Movie Response now --
		return mapMovieToMovieResponse(movie, currentUser);
	}

	private MovieResponse mapMovieToMovieResponse(Movie movie, UserPrincipal currentUser) {
		MovieResponse response = new MovieResponse();

		Helpers.copy(movie, response);

		User user = userRepository.getReferenceById(movie.getCreatedBy());
		UserProfile createdBy = new UserProfile(user.getId(), user.getEmail(), user.getCreatedAt());
		response.setShareBy(createdBy);

		Long totalVotedUp = voteRepository.countByMovieIdAndVotedName(movie.getId(), VotedName.VOTED_UP);
		Long totalVotedDown = voteRepository.countByMovieIdAndVotedName(movie.getId(), VotedName.VOTED_DOWN);

		response.setTotalVotedUp(totalVotedUp);
		response.setTotalVotedDown(totalVotedDown);

		if (currentUser != null) {
			Vote userVote = voteRepository.findByUserIdAndMovieId(currentUser.getId(), movie.getId());

			if (userVote != null) {
				VoteResponse voteResponse = new VoteResponse();
				voteResponse.setId(userVote.getId());
				voteResponse.setVotedName(userVote.getVotedName());
				voteResponse.setCreatedAt(userVote.getCreatedAt());

				response.setVotedMovie(voteResponse);

			}
		}

		return response;
	}

}
