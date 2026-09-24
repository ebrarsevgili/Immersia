import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import MovieDetail from "./pages/MovieDetail";
import Register from "./pages/Register";
import Login from "./pages/Login";
import { useState } from "react";

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
      </Routes>
    </BrowserRouter>
  );
}
export default App;
