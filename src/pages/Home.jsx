import "./Home.css";
import { Search } from "lucide-react";
import HeroSlider from "../components/HeroSlider";
import MovieCard from "../components/MovieCard";
import { useEffect, useState } from "react";

function Home() {
  const [movies, setMovies] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [isSearchMode, setIsSearchMode] = useState(false);

 
  useEffect(() => {
  fetch("http://localhost:8080/api/movies")
    .then((response) => response.json())
    .then((data) => {
      setMovies(data);
    })
    .catch((error) => {
      console.error("Film verisi alınamadı:", error);
    });
}, []);

function handleSearch() {
  fetch(`http://localhost:8080/api/movies/search?query=${searchQuery}`)
    .then((response) => response.json())
    .then((data) => {
      setSearchResults(data);
      setIsSearchMode(true);
      setSearchQuery("");
    })
    .catch((error) => {
      console.error("Arama hatası:", error);
    });
}

  function handleBackToHome() {
    setIsSearchMode(false);
    setSearchResults([]);
  }

  return (
    <main className="home">
        <div
                className="search-box"
                style={{
                    position: "relative",
                    zIndex: 9999
                }}
        >
            <input
              type="text"
              placeholder="Film ara..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              onKeyDown={(e) => e.key === "Enter" && handleSearch()}
            />

            <button
                onClick={() => {
                    handleSearch();
                }}
            >
                <Search size={30} />
            </button>

      </div>

      {!isSearchMode ? (
        <>
          <HeroSlider movies={movies} />

          <section className="popular-section">
            <div className="section-header">
              <h2>Popüler Filmler</h2>

              <a href="#" className="see-all-link">
                Tümünü Gör
              </a>
            </div>

            <div className="movie-grid">
              {movies.map((movie) => (
                <MovieCard
                  key={movie.id}
                  movie={movie}
                />
              ))}
            </div>
          </section>
        </>
      ) : (
        <section className="search-results-section">
          <div className="section-header">
            <h2>Arama Sonuçları</h2>
            <button onClick={handleBackToHome}>Ana Sayfaya Dön</button>
          </div>

          <div className="movie-grid">
            {searchResults.map((movie) => (
              <MovieCard
                key={movie.id}
                movie={movie}
              />
            ))}
          </div>
        </section>
      )}

    </main>
  );
}
export default Home;
