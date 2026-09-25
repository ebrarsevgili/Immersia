import { useEffect, useState } from "react";
import "./Movies.css";
import MovieCard from "../components/MovieCard";

function Movies() {
  const [movies, setMovies] = useState([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);

  async function loadMovies(pageNumber) {
    setLoading(true);

    try {
      const response = await fetch(
        `http://127.0.0.1:8080/api/movies?page=${pageNumber}`
      );

      if (!response.ok) {
        throw new Error("Filmler alınamadı.");
      }

      const data = await response.json();

      setMovies((previousMovies) => [
        ...previousMovies,
        ...data,
      ]);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadMovies(1);
  }, []);

  function handleLoadMore() {
    const nextPage = page + 1;

    setPage(nextPage);
    loadMovies(nextPage);
  }

  return (
    <main className="movies-page">
      <h1>Filmler</h1>

      <div className="movies-grid">
        {movies.map((movie) => (
          <MovieCard
            key={movie.id}
            movie={movie}
          />
        ))}
      </div>

      <div className="load-more-container">
        <button
          className="load-more-button"
          onClick={handleLoadMore}
          disabled={loading}
        >
          {loading ? "Filmler yükleniyor..." : "Daha Fazla Film"}
        </button>
      </div>
    </main>
  );
}

export default Movies;