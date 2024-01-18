const express = require('express');
const path = require('path');
const cors = require('cors');
const app = express();

app.use(cors());

const backendUrl = 'https://backend-production-c26d.up.railway.app/';  

app.use(express.static(path.join(__dirname, 'dist/shop')));

app.use('/api', (req, res) => {
  apiProxy.web(req, res, { target: backendUrl });
});

app.get('/*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/shop/index.html'));
});

const port = process.env.PORT || 3000;

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});

