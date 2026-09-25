import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import MovieDetail from "./pages/MovieDetail";
import Register from "./pages/Register";
import Login from "./pages/Login";
import { useState } from "react";
import Favorites from "./pages/Favorites";
import Watchlist from "./pages/Watchlist";
import Movies from "./pages/Movies";
import Account from "./pages/Account";

function App() {
  const [isLoggedIn , setIsLoggedIn] = useState(
    !!localStorage.getItem("token")
  );

  return (
    <BrowserRouter>
      <Navbar isLoggedIn = {isLoggedIn}/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/movies/:id" element={<MovieDetail />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login onLogin={setIsLoggedIn}/>} />
        <Route path="/favorites" element={<Favorites />} />
        <Route path="/watchlist" element={<Watchlist />} />
        <Route path="/movies" element={<Movies />} />
        <Route path="/account" element={<Account onLogout= {()=> setIsLoggedIn(false)}/>} />
      </Routes>
    </BrowserRouter>
  );
}
export default App;
