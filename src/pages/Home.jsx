import "./Home.css";
import { Search } from "lucide-react";
import HeroSlider from "../components/HeroSlider";
import MovieCard from "../components/MovieCard";
import { useEffect, useState } from "react";

function Home(){
    const [movies , setMovies] = useState([]);

/*     useEffect(()=>{
        fetch("http://localhost:8080/api/movies")
        .then(response => response.json())
        .then(data =>{
            setMovies(data);
        });
    },[]); */

        useEffect(() => {
        fetch("http://localhost:8080/api/movies")
            .then(response => {
                console.log("STATUS:", response.status);
                return response.json();
            })
            .then(data => {
        console.log("API DATA:", data);
        console.log("IS ARRAY:", Array.isArray(data));
        setMovies(data);
    });
    }, []);

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
            

        </main>

    );
}
export default Home;