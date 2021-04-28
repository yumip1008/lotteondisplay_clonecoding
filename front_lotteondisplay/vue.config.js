const path = require('path');
module.exports = {
  devServer:{
      proxy:{
        '/api' :{
          target: 'http://localhost:8080',
          changeOrigin: true,
            pathRewrite: {
              '^/api': ''
          }
        },
      }
    },
    configureWebpack: {
      resolve: {
          alias: {
              '~': path.join(__dirname, '/src')
          }
      }
  }
}
