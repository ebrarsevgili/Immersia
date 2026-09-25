import { useEffect, useState } from "react";
import "./Favorites.css";
import MovieCard from "../components/MovieCard";

function Favorites() {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/favorites", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Favoriler alınamadı.");
        }

        return response.json();
      })
      .then(async (data) => {
        console.log("FAVORITES:", data);

        const movies = await Promise.all(
          data.map((favorite) =>
            fetch(`http://localhost:8080/api/movies/${favorite.movieId}`)
              .then((response) => response.json())
          )
        );

        setFavorites(movies);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
  <main className="favorites-page">
    <h1>Favorilerim</h1>

    {favorites.length === 0 ? (
      <p className="empty-favorites">
        Henüz favori filmin yok.
      </p>
    ) : (
      <div className="favorite-movie-grid">
        {favorites.map((movie) => (
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

export default Favorites;