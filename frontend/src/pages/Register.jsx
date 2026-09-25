import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Register.css";

function Register() {

    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    async function handleSubmit(event) {
        event.preventDefault();
        setErrorMessage("");

        const userData = {
            username,
            email,
            password
        };

        try {
            const response = await fetch(
                "http://localhost:8080/api/auth/register",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(userData)
                }
            );

            if (!response.ok) {
                throw new Error("Kayıt başarısız");
            }

            setUsername("");
            setEmail("");
            setPassword("");

            navigate("/login");

        } catch (error) {
            console.error("Kayıt sırasında hata:", error);
            setErrorMessage("Kayıt sırasında bir hata oluştu. Lütfen tekrar deneyin.");
        }
    }

    return (
        <main className="register-page">
            <div className="register-card">
                <h1>Kayıt Ol</h1>

                {errorMessage && (
                    <p className="error-message">{errorMessage}</p>
                )}

                <form onSubmit={handleSubmit}>

                    <label>Kullanıcı Adı</label>
                    <input
                        type="text"
                        placeholder="kullaniciadi"
                        value={username}
                        onChange={(event) => setUsername(event.target.value)}
                    />

                    <label>E-posta</label>
                    <input
                        type="email"
                        placeholder="ornek@mail.com"
                        value={email}
                        onChange={(event) => setEmail(event.target.value)}
                    />

                    <label>Şifre</label>
                    <input
                        type="password"
                        placeholder="••••••••••"
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                    />

                    <button type="submit">
                        Kayıt Ol
                    </button>
                </form>

                <p>
                    Zaten hesabın var mı?{" "}
                    <span onClick={() => navigate("/login")}>Giriş Yap</span>
                </p>
            </div>
        </main>
    );
}

export default Register;