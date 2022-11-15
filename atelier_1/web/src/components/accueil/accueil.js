import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";

function Accueil() {
  const [utilisateur, setUtilisateur] = useState([]);

  useEffect(() => {
    fetch("https://asi2-backend-market.herokuapp.com/user/8")
      .then((resp) => resp.json())
      .then((values) => {
        setUtilisateur(values);
      });
  }, []);

  return (
    <div>
      <div className="d-flex justify-content-between align-items-center p-3" style={{ height: "100px", backgroundColor: "rgb(219 218 218)" }}>
        <h2> t</h2>
        <div>
          <FontAwesomeIcon icon="coffee" />
          {utilisateur.surName ? (
            <h2 className="cursor-pointer">
              <a href="/connexion">{utilisateur.surName}</a>
            </h2>
          ) : (
            <h2>
              {" "}
              <a href="/connexion">Login</a>
            </h2>
          )}
        </div>
      </div>
      <Outlet/>
    </div>
  );
}

export default Accueil;
