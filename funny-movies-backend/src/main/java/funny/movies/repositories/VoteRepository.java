package funny.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import funny.movies.models.Vote;
import funny.movies.models.VotedName;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	@Query("SELECT COUNT(v.id) from Vote v where v.movie.id = :movieId and votedName = :votedName")
	long countByMovieIdAndVotedName(@Param("movieId") Long movieId, @Param("votedName") VotedName votedName);

	@Query("SELECT v FROM Vote v where v.user.id = :userId and v.movie.id = :movieId")
	Vote findByUserIdAndMovieId(@Param("userId") Long userId, @Param("movieId") Long movieId);
}
