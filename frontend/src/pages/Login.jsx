import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login({onLogin}) {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  async function handleSubmit(event) {
    event.preventDefault();
    setErrorMessage("");

    const loginData = {
      username,
      email,
      password,
    };

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });
      if (!response.ok) {
        throw new Error("Giriş başarısız");
      }
      const token = await response.text();
      localStorage.setItem("token" , token);
      onLogin(true);
      navigate("/");
    } catch (error) {
      setErrorMessage("E-posta veya şifre hatalı.");
    }
  }

  return (
    <main className="login-page">
      <div className="login-card">
        <h1>Giriş Yap</h1>

        {errorMessage && <p className="error-message">{errorMessage}</p>}

        <form onSubmit={handleSubmit}>
          <label>Kullanıcı Adı</label>

          <input
            type="text" 
            placeholder="kullaniciadi" 
            value={username} 
            onChange={(event) => setUsername(event.target.value)} 
          />
          <label>E-Posta</label>
          <input
            type="email"
            placeholder="ornek@mail.com"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
          />

          <label>Şifre</label>
          <input
            type="password"
            placeholder="*************"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />

          <button className="submit">Giriş Yap</button>
        </form>

        <p>
          Hesabın yok mu?{" "}
          <span onClick={() => navigate("/register")}>Kayıt ol</span>
        </p>
      </div>
    </main>
  );
}
export default Login;
