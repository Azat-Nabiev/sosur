import React from 'react';
import { Link, useLocation } from 'react-router-dom';

function WelcomePage() {
    const location = useLocation();
    const message = location.state?.message;

    return (
        <div>
            <h1>Welcome</h1>
            {message && <div className="success-message">{message}</div>}
            <Link to="/register">Register</Link> | <Link to="/login">Login</Link>
        </div>
    );
}

export default WelcomePage;