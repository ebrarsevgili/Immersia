import { Heart, Star } from "lucide-react";

function MovieCard({movie}){
    
    return(

        <article className="movie-card">

            <div className="movie-poster">
                <img 
                    src={movie.image} 
                    alt={movie.title} 
                    />
                <button 
                    className="favorite-button"
                    arial-label={`${movie.title} favorilere ekle`}
                >
                    <Heart size={18} />
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