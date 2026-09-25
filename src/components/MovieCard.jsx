import { useEffect, useState } from "react";
import { Heart, Star } from "lucide-react";
import "./MovieCard.css";
import { Link } from "react-router-dom";

function MovieCard({ movie }) {
  const [isFavorite, setIsFavorite] = useState(false);
  const [favoriteId, setFavoriteId] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) return;

    fetch("http://localhost:8080/api/favorites", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((favorites) => {
        const favorite = favorites.find(
          (favorite) => Number(favorite.movieId) === Number(movie.id),
        );
        if (favorite) {
          setIsFavorite(true);
          setFavoriteId(favorite.id);
        } else {
          setIsFavorite(false);
          setFavoriteId(null);
        }
      });
  }, [movie.id]);

  console.log(movie.title, "FAVORITE:", isFavorite);

  async function handleFavorite() {
    const token = localStorage.getItem("token");

    if (!token) return;

    if (isFavorite) {
      const response = await fetch(
        `http://localhost:8080/api/favorites/${favoriteId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );

      if (response.ok) {
        setIsFavorite(false);
        setFavoriteId(null);
      }

      return;
    }

    const response = await fetch(
      `http://localhost:8080/api/favorites?movieId=${movie.id}`,
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );

    if (response.ok) {
      const favorite = await response.json();

      setIsFavorite(true);
      setFavoriteId(favorite.id);
    }
  }

  return (
    <article className="movie-card">
      <div className="movie-poster">
        <Link to={`/movies/${movie.id}`} className="movie-card-link">
          <img src={movie.image} alt={movie.title} />
        </Link>

        <button
          className={`favorite-button ${isFavorite ? "favorite-active" : ""}`}
          onClick={handleFavorite}
          aria-label={
            isFavorite
              ? `${movie.title} favorilerde`
              : `${movie.title} favorilere ekle`
          }
        >
          <Heart 
            size={18} 
            fill={isFavorite ? "currentColor" : "none"} 
          />
        </button>
      </div>

      <div className="movie-info">
        <h3>{movie.title}</h3>

        <div className="movie-meta">
          <span>{movie.year}</span>

          <span className="rating">
            <Star size={14} />
            {movie.rating}
          </span>
        </div>
      </div>
    </article>
  );
}

export default MovieCard;
