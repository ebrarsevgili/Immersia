import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Heart,
  List,
  Settings,
  LogOut,
  User,
} from "lucide-react";
import "./Account.css";

function Account() {
  const [user, setUser] = useState(null);
  const [favorites, setFavorites] = useState([]);
  const [watchlist, setWatchlist] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      navigate("/login");
      return;
    }

    async function loadAccountData() {
      try {
        const userResponse = await fetch(
          "http://localhost:8080/api/auth/me",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (!userResponse.ok) {
          throw new Error("Kullanıcı bilgileri alınamadı.");
        }

        const userData = await userResponse.json();
        setUser(userData);

        const favoritesResponse = await fetch(
          "http://localhost:8080/api/favorites",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (!favoritesResponse.ok) {
          throw new Error("Favoriler alınamadı.");
        }

        const favoritesData = await favoritesResponse.json();
        setFavorites(favoritesData);

        const watchlistResponse = await fetch(
          "http://localhost:8080/api/watchlist",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (!watchlistResponse.ok) {
          throw new Error("İzleme listesi alınamadı.");
        }

        const watchlistData = await watchlistResponse.json();
        setWatchlist(watchlistData);
      } catch (error) {
        console.error(error);
      }
    }

    loadAccountData();
  }, [navigate]);

  function handleLogout() {
    localStorage.removeItem("token");
    navigate("/login");
  }

  if (!user) {
    return (
      <main className="account-page">
        <p className="account-loading">Yükleniyor...</p>
      </main>
    );
  }

  return (
    <main className="account-page">
      <div className="account-container">

        <aside className="account-sidebar">

          <div className="profile-section">
            <div className="profile-avatar">
              <User size={52} />
            </div>

            <h1>{user.username}</h1>
            <p>{user.email}</p>
          </div>

          <div className="account-menu">

            <button
              className="account-menu-item"
              onClick={() => navigate("/favorites")}
            >
              <Heart size={20} />
              <span>Favorilerim</span>
            </button>

            <button
              className="account-menu-item"
              onClick={() => navigate("/watchlist")}
            >
              <List size={20} />
              <span>İzleme Listem</span>
            </button>

            <div className="menu-divider"></div>

            <button className="account-menu-item">
              <Settings size={20} />
              <span>Hesap Ayarları</span>
            </button>

            <div className="menu-divider"></div>

            <button
              className="account-menu-item logout-button"
              onClick={handleLogout}
            >
              <LogOut size={20} />
              <span>Çıkış Yap</span>
            </button>

          </div>

        </aside>

        <section className="account-content">

          <h2>İstatistiklerim</h2>

          <div className="stats-grid">

            <div className="stat-card">
              <Heart size={24} />
              <strong>{favorites.length}</strong>
              <span>Favori Film</span>
            </div>

            <div className="stat-card">
              <List size={24} />
              <strong>{watchlist.length}</strong>
              <span>İzleme Listesi</span>
            </div>

          </div>

          <div className="account-actions">

            <div
              className="action-card"
              onClick={() => navigate("/favorites")}
            >
              <Heart size={24} />
              <div>
                <h3>Favorilerim</h3>
                <p>
                  Favorilerine eklediğin filmleri görüntüle.
                </p>
              </div>
            </div>

            <div
              className="action-card"
              onClick={() => navigate("/watchlist")}
            >
              <List size={24} />
              <div>
                <h3>İzleme Listem</h3>
                <p>
                  Daha sonra izlemek istediğin filmleri görüntüle.
                </p>
              </div>
            </div>

          </div>

        </section>

      </div>
    </main>
  );
}

export default Account;