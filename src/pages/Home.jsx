import { Search } from "lucide-react";
import HeroSlider from "../components/HeroSlider";

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
                </div> 

            </section>
            

        </main>

    );
}
export default Home;