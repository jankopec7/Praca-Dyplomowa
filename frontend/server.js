const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const path = require('path');
const app = express();

const backendUrl = process.env.BACKEND_URL || 'https://backend-production-c26d.up.railway.app/';

const apiProxy = createProxyMiddleware('/api', {
  target: backendUrl,
  changeOrigin: true,
  pathRewrite: {
    '^/api': '',
  },
});

app.use('/api', apiProxy);
app.use(express.static(path.join(__dirname, 'dist/shop')));

app.get('/*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/shop/index.html'));
});

const port = process.env.PORT || 3000;


app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});