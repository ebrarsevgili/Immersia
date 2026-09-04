import { useEffect , useState } from "react";
import movies from "../data/movie";
import { ChevronLeft ,ChevronRight } from "lucide-react";

function HeroSlider(){

    const [currentIndex , setCurrentIndex] = useState(0);

    const currentMovie = movies[currentIndex];
    console.log(currentMovie);

    function nextSlide(){
        setCurrentIndex((currentIndex) => {
            if(currentIndex === movies.length -1 ){
                return 0;
            }
            return currentIndex + 1 ;
        });
    }

    function previousSlide(){
        setCurrentIndex((currentIndex )=>{
            if(currentIndex === 0){
                return movies.length -1;
            }
            return currentIndex - 1 ;
        })
    }

    useEffect(() => {

        const interval = setInterval(() => {
            nextSlide();
        } , 5000);

        return () => {
            clearInterval(interval);
        }

    }, []);

    return(
        <section className="hero">
                <div 
                    className="hero-background" 
                    style={{
                        backgroundImage:`url(${currentMovie.image})`
                    
                    }}
                />

            <div className="hero-overlay" />

            <div className="hero-content">
                <h1>{currentMovie.title}</h1> 

                <div className="hero-meta">
                    <span>{currentMovie.year}</span>
                    <span>•</span>
                    <span>{currentMovie.duration}</span>
                    <span>•</span>

                    <span className="hero-rating">
                        ⭐ {currentMovie.rating}
                    </span>
                </div>

                <p>
                    {currentMovie.description}
                </p>
                <button className="detail-button">Detayları Gör </button>

            </div>
                
            
        
            <button
                className="slider-button slider-prev"
                onClick={previousSlide}
            >
                <ChevronLeft />
            </button>

            <button
                    className="slider-button slider-next"
                    onClick={nextSlide}
                    
            >
                <ChevronRight />
            </button>

            <div className="hero-dots">

                    {movies.map((movie, index) => (

                        <button
                            key={movie.id}
                            className={
                                index === currentIndex
                                    ? "dot active"
                                    : "dot"
                            }
                            onClick={() => setCurrentIndex(index)}
                            aria-label={`${movie.title} filmine git`}
                        />

                    ))}

                </div>



        </section>

    );
}
export default HeroSlider;