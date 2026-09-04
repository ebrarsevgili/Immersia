

function Navbar(){

    return(
        <nav className="navbar">
            <div className="logo">
                <span className="logo-icon">◉</span>
                <span>Immersia</span>
             </div>

            <div className="nav-links">
                <a href="#">Ana Sayfa</a>
                <a href="#">Filmler</a>
                <a href="#">Diziler</a>
                <a href="#">Listelerim</a>
            </div>         

            <div className="profile">
                <span>👤</span>
            </div>
            
        </nav>
    );
}
export default Navbar;