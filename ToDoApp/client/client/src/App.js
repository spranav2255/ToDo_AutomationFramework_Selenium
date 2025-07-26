import React, { useState } from 'react';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState('');
  const [editingIndex, setEditingIndex] = useState(-1);
  const [editText, setEditText] = useState('');

  const handleLogin = () => {
	if (username === 'admin' && password === 'admin') {
	  setIsLoggedIn(true);
	} else {
	  console.log("Triggering alert for invalid login...");
	  setTimeout(() => {
	    alert('Invalid credentials');
	  }, 200); // slight delay helps Selenium catch it
	}

  };

  const addTodo = () => {
    if (newTodo.trim() !== '') {
      setTodos([...todos, newTodo]);
      setNewTodo('');
    }
  };

  const deleteTodo = (index) => {
    const updated = [...todos];
    updated.splice(index, 1);
    setTodos(updated);
  };

  const startEdit = (index) => {
    setEditingIndex(index);
    setEditText(todos[index]);
  };

  const saveEdit = (index) => {
    const updated = [...todos];
    updated[index] = editText;
    setTodos(updated);
    setEditingIndex(-1);
  };

  if (!isLoggedIn) {
    return (
      <div className="login-container">
        <h2>Login</h2>
        <input
          id="username"
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Username"
        />
        <input
          id="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
        />
        <button id="loginBtn" onClick={handleLogin}>Login</button>
      </div>
    );
  }

  return (
    <div className="app-container">
      <h2>ToDo Dashboard</h2>
      <input
        placeholder="New ToDo"
        value={newTodo}
        onChange={(e) => setNewTodo(e.target.value)}
      />
      <button onClick={addTodo}>Add</button>
      <ul>
        {todos.map((todo, index) => (
          <li key={index}>
            {editingIndex === index ? (
              <>
                <input
                  value={editText}
                  onChange={(e) => setEditText(e.target.value)}
                />
                <button onClick={() => saveEdit(index)}>Save</button>
              </>
            ) : (
              <>
                <span>{todo}</span>
                <button onClick={() => startEdit(index)}>Edit</button>
                <button onClick={() => deleteTodo(index)}>Delete</button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
