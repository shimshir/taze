var path = require('path');
var webpack = require('webpack');

module.exports = {
    entry: [
        './reactsrc/index.js'
    ],
    output: {
        path: __dirname,
        filename: 'bundle.js'
    },
    //devtool: 'inline-source-map',
    module: {
        loaders: [
            {
                test: /\.js$/,
                loaders: ['react-hot', 'babel'],
                include: path.join(__dirname, 'reactsrc')
            }
        ]
    }
};
