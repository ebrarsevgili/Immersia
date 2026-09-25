import { User , Heart} from "lucide-react";
import { useNavigate } from "react-router-dom";
import "./Navbar.css";


function Navbar({isLoggedIn}) {
  const navigate = useNavigate();

  return (
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

      <div className="auth-area">
        <button 
            className="favorite-nav-button"
            onClick={() => navigate("/favorites")}
            aria-label="Favorilerim"
        >
            <Heart size={20} />
        </button>

        {isLoggedIn ? (
          <button
            className="account-button"
            onClick={() => navigate("/account")}
          >
            Hesabım
          </button>
        ) : (
          <div className="auth-links">
            <button onClick={() => navigate("/login")}>Giriş Yap</button>

            <span>|</span>

            <button onClick={() => navigate("/register")}>Kayıt Ol</button>
          </div>
        )}

        <button
          className="profile-button"
          onClick={() => {
            if (isLoggedIn) {
              navigate("/account");
            } else {
              navigate("/login");
            }
          }}
        >
          <User size={18} />
        </button>
      </div>
    </nav>
  );
}
export default Navbar;
