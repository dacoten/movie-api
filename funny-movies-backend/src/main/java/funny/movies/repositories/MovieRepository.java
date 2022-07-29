package funny.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import funny.movies.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
