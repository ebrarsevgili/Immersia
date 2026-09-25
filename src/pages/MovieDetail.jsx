import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Plus, Heart, Star, Minus } from "lucide-react";
import "./MovieDetail.css";
import MovieCard from "../components/MovieCard";

function MovieDetail() {
  const { id } = useParams();

  const [movie, setMovie] = useState(null);
  const [similarMovies, setSimilarMovies] = useState([]);

  const [isFavorite, setIsFavorite] = useState(false);
  const [favoriteId, setFavoriteId] = useState(null);

  const [isInWatchlist, setInWatchlist] = useState(false);
  const [watchlistId, setWatchlistId] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/api/movies/${id}`)
      .then((response) => response.json())
      .then((data) => {
        console.log("MOVIE DETAIL:", data);
        setMovie(data);
      });

    fetch(`http://localhost:8080/api/movies/${id}/similar`)
      .then((response) => response.json())
      .then((data) => {
        console.log("SIMILAR MOVIES:", data);
        setSimilarMovies(data);
      });
  }, [id]);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token || !movie) return;

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
  }, [movie]);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token || !movie) return;

    fetch("http://localhost:8080/api/watchlist", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((watchlist) => {
        const item = watchlist.find(
          (item) => Number(item.movieId) === Number(movie.id),
        );
        if (item) {
          setInWatchlist(true);
          setWatchlistId(item.id);
        } else {
          setInWatchlist(false);
          setWatchlistId(null);
        }
      });
  }, [movie]);

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

  async function handleWatchlist() {
    const token = localStorage.getItem("token");
    if (!token) return;
    if (isInWatchlist) {
      const response = await fetch(
        `http://localhost:8080/api/watchlist/${watchlistId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );
      if (response.ok) {
        (setInWatchlist(false), setWatchlistId(null));
      }
      return;
    }
    const response = await fetch(
      `http://localhost:8080/api/watchlist?movieId=${movie.id}`,
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    if (response.ok) {
      const watchlistItem = await response.json();

      setInWatchlist(true);
      setWatchlistId(watchlistItem.id);
    }
  }

  if (!movie) {
    return <p>Film yükleniyor...</p>;
  }

  return (
    <main className="movie-detail">
      <div
        className="detail-background"
        style={{
          backgroundImage: `url(${movie.backdropImage})`,
        }}
      />

      <div className="detail-overlay" />

      <div className="detail-content">
        <div className="breadcrumb">
          <span>Ana Sayfa</span>
          <span>›</span>
          <span>Filmler</span>
          <span>›</span>
          <span>{movie.title}</span>
        </div>

        <section className="movie-detail-main">
          <div className="detail-poster">
            <img src={movie.image} alt={movie.title} />
          </div>

          <div className="detail-info">
            <h1>{movie.title}</h1>

            <div className="detail-meta">
              <span>{movie.year}</span>
              <span>•</span>
              <span>{movie.duration} dk</span>
              <span>•</span>

              <span className="detail-rating">
                <Star size={16} />
                {movie.rating}
              </span>
            </div>

            <div className="genre-list">
              {movie.genres?.map((genre) => (
                <span key={genre} className="genre-tag">
                  {genre}
                </span>
              ))}
            </div>

            <p className="detail-description">{movie.description}</p>

            <div className="people-info">
              <div className="person-info">
                <span className="person-label">Yönetmen</span>

                <span className="person-value">{movie.director}</span>
              </div>

              <div className="person-info">
                <span className="person-label">Oyuncular</span>

                <span className="person-value">{movie.cast?.join(", ")}</span>
              </div>
            </div>

            <div className="detail-buttons">
              <button
                className={`watchlist-button ${
                  isInWatchlist ? "watchlist-active" : ""
                }`}
                onClick={handleWatchlist}
              >
              
                {isInWatchlist 
                ? <Minus size={20} /> 
                : <Plus size={20} />}
                {isInWatchlist
                  ? "İzleme Listesinden Çıkar"
                  : "İzleme Listeme Ekle"}
                  
              </button>

              <button
                className={`favorite-detail-button ${
                  isFavorite ? "favorite-active" : ""
                }`}
                onClick={handleFavorite}
              >
                <Heart size={20} fill={isFavorite ? "currentColor" : "none"} />

                {isFavorite ? "Favorilerden Çıkar" : "Favorilere Ekle"}
              </button>
            </div>
          </div>
        </section>

        <section className="similar-movies">
          <h2>Benzer Filmler</h2>

          <div className="similar-movie-grid">
            {similarMovies.map((movie) => (
              <MovieCard key={movie.id} movie={movie} />
            ))}
          </div>
        </section>
      </div>
    </main>
  );
}

export default MovieDetail;
