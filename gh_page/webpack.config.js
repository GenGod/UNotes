const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = (env, args) => {
    const mode = args.mode || "development";

    const config = {
        entry: './src/index.tsx',
        output: {
            path: path.join(__dirname, '/dist'),
            filename: "bundle.js",
        },
        devtool: mode === "development" ? "source-map" : false,
        plugins: [
            new CleanWebpackPlugin(),
        ],
        resolve: {
            extensions: [".ts", ".tsx", ".js", ".jsx"],
        },
        module: {
            rules: [
                {
                    test: /\.ts(x?)$/,
                    exclude: /node_modules/,
                    use: [
                        {
                            loader: "ts-loader",
                            options: {
                                compilerOptions: {
                                    noEmit: false,
                                }
                            },
                        }
                    ],
                },
                {
                    test: /\.css$/i,
                    use: ["style-loader", "css-loader"],
                },
                {
                    enforce: "pre",
                    test: /\.js$/,
                    loader: "source-map-loader",
                },
            ]
        },
        performance: {
            maxEntrypointSize: 244000,
            maxAssetSize: 244000,
        },
        plugins: [
            new HtmlWebpackPlugin({
              template: './public/index.html',
              favicon: './src/favicon.ico',

            }),
            new CopyWebpackPlugin({
            patterns:[
                {from:'src/images',to:'images'}
            ]}),
        ]
    };

    return config;
};
