import React, { useEffect, useState } from 'react';
import AuthService from './AuthService';

function ContentPage() {
    const [books, setBooks] = useState([]);
    const [author, setAuthor] = useState('');
    const [isbn, setIsbn] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            const userId = localStorage.getItem('userId');
            if (userId) {
                const data = await AuthService.getContent(userId);
                setBooks(data.books || []);
                console.log(data);
            }
        };
        fetchData();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const newBook = await AuthService.addBook(author, isbn);
            setBooks([...books, newBook]);
            setAuthor('');
            setIsbn('');
            console.log('Book added successfully');
        } catch (error) {
            console.error('Error adding book:', error.message);
        }
    };

    return (
        <div>
            <h1>Protected Content</h1>
            {books.map((book) => (
                <div key={book.id}>
                    <p>Author: {book.author}</p>
                    <p>ISBN: {book.isbn}</p>
                    <p>ID: {book.id}</p>
                </div>
            ))}

            <form onSubmit={handleSubmit}>
                <label>
                    Author:
                    <input type="text" value={author} onChange={e => setAuthor(e.target.value)} required />
                </label>
                <label>
                    ISBN:
                    <input type="text" value={isbn} onChange={e => setIsbn(e.target.value)} required />
                </label>
                <button type="submit">Add Book</button>
            </form>
        </div>
    );
}

export default ContentPage;
