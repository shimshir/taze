var webpack = require('webpack');
var WebpackDevServer = require('webpack-dev-server');
var config = require('./webpack.dev.config.js');

var port = 18080;

new WebpackDevServer(webpack(config), {
    publicPath: config.output.publicPath,
    hot: false,
    historyApiFallback: true,
}).listen(port, 'localhost', function (err, result) {
    if (err) {
        return console.log(err);
    }

    console.log(`Listening at http://localhost:${port}/`);
});
