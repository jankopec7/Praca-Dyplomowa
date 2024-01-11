const express = require('express');
const path = require('path');
const app = express();

const apiProxy = proxy.createProxyMiddleware("/api", {
        target: process.env.BACKEND_URL,
        changeOrigin: true,
        pathRewrite: {
            '^/api':''
        }
});

app.use(apiProxy);
app.use(express.static(__dirname + "/dist/shop"));

app.get("/*", (req, res) => {
    res.sendFile(__dirname + "/dist/shop/index.html")
});

app.listen(process.env.PORT || 3000);
