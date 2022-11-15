import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import reportWebVitals from "./reportWebVitals";
import { Provider } from "react-redux";
import { createStore } from "redux";
import { cardReducer } from "./core/card.reducer.js";
import CardAccueil from "./components/card/card.accueil";
import Accueil from "./components/accueil/accueil";
import ConnexionModule from "./components/connexion/connexion";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faCheckSquare, faCoffee } from "@fortawesome/free-solid-svg-icons";
import CardBuy from "./components/card/card.buy";
import Menu from "./components/menu/menu";

library.add(faCheckSquare, faCoffee);
const store = createStore(cardReducer);
const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Accueil />}>
            <Route index element={<Menu />} />
            <Route path="card" element={<CardAccueil />} />
            <Route path="connexion" element={<ConnexionModule />} />
            <Route path="buy" element={<CardBuy />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
