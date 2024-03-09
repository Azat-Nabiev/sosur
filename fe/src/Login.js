import React, { useState } from 'react';
import AuthService from './AuthService';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const result = await AuthService.login(email, password, navigate);
        if (result && result.error) {
            setError(result.message);
        }
    };

    return (
        <div>
            <h1>Login</h1>
            {error && <div className="error-message">{error}</div>} {}
            <form onSubmit={handleSubmit}>
                <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" required />
                <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" required />
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;
