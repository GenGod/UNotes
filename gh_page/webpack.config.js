const { CleanWebpackPlugin } = require('clean-webpack-plugin');
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
        // Enable sourcemaps for debugging webpack's output.
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
                // All output '.js' files will have any sourcemaps re-processed by 'source-map-loader'.
                {
                    enforce: "pre",
                    test: /\.js$/,
                    loader: "source-map-loader",
                },
            ]
        },
        // Default max size is 244000.
        performance: {
            maxEntrypointSize: 244000,
            maxAssetSize: 244000,
        },
        plugins: [
            new HtmlWebpackPlugin({
              template: './public/index.html',
              favicon: './src/favicon.ico'
            })
        ]
    };

    return config;
};
