const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
  mode: "development",
  entry: {
    login: [
      "@babel/polyfill",
      path.resolve(__dirname, "./frontend/logicPages/authLogic.js"),
    ],
    registration: [
      "@babel/polyfill",
      path.resolve(__dirname, "./frontend/logicPages/registrLogic.js"),
    ],
    main: [
      "@babel/polyfill",
      path.resolve(__dirname, "./frontend/logicPages/mainLogic.js"),
    ],
  },
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "[name].js",
  },
  devServer: {
    contentBase: path.join(__dirname, "dist"),
    compress: true,
    port: 5000,
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "frontend/client/index.html",
      filename: "index.html",
      chunks: ["login"],
    }),
    new HtmlWebpackPlugin({
      template: "frontend/client/mainPage.html",
      filename: "mainPage.html",
      chunks: ["main"],
    }),
    new HtmlWebpackPlugin({
      template: "frontend/client/registrPage.html",
      filename: "registrPage.html",
      chunks: ["registration"],
    }),
    new MiniCssExtractPlugin({
      filename: "[name].css",
    }),
    new CopyWebpackPlugin({
      patterns: [{ from: "frontend/style/img/", to: "dist/style/img" }],
    }),
  ],
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
        },
      },
      {
        test: /\.(ttf|woff|woff2|eot)/,
        use: ["file-loader"],
      },
      {
        test: /\.(png|gif|jpg|jpeg|jfif)/,
        use: ["file-loader"],
      },
      {
        test: /\.css$/i,
        use: [MiniCssExtractPlugin.loader, "css-loader"],
      },
      {
        test: /\.scss$/,
        use: [MiniCssExtractPlugin.loader, "css-loader", "sass-loader"],
      },
    ],
  },
};
