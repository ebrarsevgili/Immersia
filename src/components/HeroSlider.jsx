import { useEffect , useState } from "react";
import { ChevronLeft ,ChevronRight } from "lucide-react";
import "./HeroSlider.css";

function HeroSlider({ movies }){

    const [currentIndex , setCurrentIndex] = useState(0);

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

    if (movies.length <= 1) {
        return;
    }

    const interval = setInterval(() => {
        nextSlide();
    }, 5000);

    return () => {
        clearInterval(interval);
    };

}, [movies.length]);

    useEffect(() => {
            setCurrentIndex(0);
    }, [movies]);

    if (movies.length === 0) {
        return null;
    }

    const currentMovie = movies[currentIndex];
    
        
        

    return(
        <section className="hero">
                <div 
                    className="hero-background" 
                    style={{
                        backgroundImage:`url(${currentMovie.backdropImage})`
                    
                    }}
                />

            <div className="hero-overlay" />

            <div className="hero-content">
                <h1>{currentMovie.title}</h1> 

                <div className="hero-meta">
                    <span>{currentMovie.year}</span>
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