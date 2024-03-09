import React, { useState } from 'react';
import AuthService from './AuthService'
import { useNavigate } from 'react-router-dom';

function Register() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [role, setRole] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        AuthService.register(email, password, firstName, lastName, role, navigate);
    };

    return (
        <div>
            <h1>Register</h1>
            <form onSubmit={handleSubmit}>
                <input type="email" value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" required />
                <input type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" required />
                <input type="firstName" value={firstName} onChange={e => setFirstName(e.target.value)} placeholder="FirstName" required />
                <input type="lastName" value={lastName} onChange={e => setLastName(e.target.value)} placeholder="LastName" required />
                <input type="role" value={role} onChange={e => setRole(e.target.value)} placeholder="Role" required />
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;
