import { Search } from "lucide-react";
import HeroSlider from "../components/HeroSlider";
import movies from "../data/movie";
import MovieCard from "../components/MovieCard";

function Home(){

    return(
        <main className="home">
            <div className="search-box">
                <input 
                type="text" 
                placeholder="Film ara..."
                />
                <button >
                    <Search size={30} />
                </button>

            </div>
        
            <HeroSlider />

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
            

        </main>

    );
}
export default Home;