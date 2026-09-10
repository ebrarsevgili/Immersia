import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Plus, Heart, Star } from "lucide-react";
import "./MovieDetail.css";
import MovieCard from "../components/MovieCard";

function MovieDetail() {
  const { id } = useParams();

  const [movie, setMovie] = useState(null);
  const [similarMovies, setSimilarMovies] = useState([]);

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
              <button className="watchlist-button">
                <Plus size={20} />
                İzleme Listeme Ekle
              </button>

              <button className="favorite-detail-button">
                <Heart size={20} />
                Favorilere Ekle
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
