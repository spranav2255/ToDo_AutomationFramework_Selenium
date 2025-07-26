const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const port = 4000; // Changed from 3000 to 4000

// Middleware
app.use(bodyParser.json());

// CORS setup so frontend can access the backend
app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*"); // Adjust for production
  res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
  res.header("Access-Control-Allow-Headers", "Content-Type");
  next();
});

// In-memory data storage
let items = [];

// Routes
app.get('/api/items', (req, res) => {
  res.status(200).json(items);
});

app.get('/api/items/:id', (req, res) => {
  const item = items.find(i => i.id === parseInt(req.params.id));
  if (item) {
    res.status(200).json(item);
  } else {
    res.status(404).json({ message: 'Item not found' });
  }
});

app.post('/api/items', (req, res) => {
  const newItem = {
    id: items.length + 1,
    name: req.body.name,
    description: req.body.description
  };
  items.push(newItem);
  res.status(201).json(newItem);
});

app.put('/api/items/:id', (req, res) => {
  const index = items.findIndex(i => i.id === parseInt(req.params.id));
  if (index !== -1) {
    items[index] = {
      id: parseInt(req.params.id),
      name: req.body.name,
      description: req.body.description
    };
    res.status(200).json(items[index]);
  } else {
    res.status(404).json({ message: 'Item not found' });
  }
});

app.delete('/api/items/:id', (req, res) => {
  const index = items.findIndex(i => i.id === parseInt(req.params.id));
  if (index !== -1) {
    const deletedItem = items.splice(index, 1);
    res.status(200).json(deletedItem);
  } else {
    res.status(404).json({ message: 'Item not found' });
  }
});

// Start server
app.listen(port, () => {
  console.log(`Backend server running at http://localhost:${port}`);
});
