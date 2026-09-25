import { useEffect, useState } from "react";
import "./Watchlist.css";
import MovieCard from "../components/MovieCard";

function Watchlist() {
  const [movies, setMovies] = useState([]);
  const [error , setError] = useState(() =>
  localStorage.getItem("token")
    ? ""
    : "İzleme listeni görmek için giriş yapmalısın."
);
  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      return;
    }

    fetch("http://localhost:8080/api/watchlist", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("İzleme listesi alınamadı.");
        }

        return response.json();
      })
      .then(async (data) => {

        const movies = await Promise.all(
          data.map((item) =>
            fetch(
              `http://localhost:8080/api/movies/${item.movieId}`
            ).then((response) => response.json())
          )
        );

        setMovies(movies);
      })
      .catch((error) => {
        console.error(error);
        setError("İzleme listesi yüklenirken bir hata oluştu.");
      });
  }, []);

  return (
    <main className="watchlist-page">
      <h1>İzleme Listem</h1>

      {error ? (
        <p className="empty-watchlist">{error}</p>
      ):movies.length === 0 ? (
        <p className="empty-watchlist">
          Henüz izleme listende film yok.
        </p>
      ) : (
        <div className="watchlist-movie-grid">
          {movies.map((movie) => (
            <MovieCard
              key={movie.id}
              movie={movie}
            />
          ))}
        </div>
      )}
    </main>
  );
}

export default Watchlist;