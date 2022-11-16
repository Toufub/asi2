import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, Outlet, useLocation } from "react-router-dom";
import { setSelectedClientId } from "../../core/client.actions";
import { selectedClientId } from "../../core/client.selector";
import { getCookie } from "../../utils/cookie";

function Accueil() {
  
  const [utilisateur, setUtilisateur] = useState([]);
  const sampleLocation = useLocation();

  
  const clientCookie = getCookie("clientId");
  const userId = useSelector(selectedClientId);

  const dispatch = useDispatch();


  useEffect(() => {
    if (userId) {
      fetch(`https://asi2-backend-market.herokuapp.com/user/${userId}`)
        .then((resp) => resp.json())
        .then((values) => {
          setUtilisateur(values);
        });
    }
  }, [userId]);

  useEffect(() => {
    if(clientCookie)
    fetch(`https://asi2-backend-market.herokuapp.com/user/${clientCookie}`)
    .then((resp) => resp.json())
    .then((values) => {
      setUtilisateur(values);
      dispatch(setSelectedClientId(clientCookie));
    });
    
  }, [clientCookie]);

  return (
    <div className="h-100">
      <div className="d-flex justify-content-between align-items-center p-3" style={{ height: "100px", backgroundColor: "rgb(219 218 218)" }}>
        <h2>
          <Link to="/">
            <FontAwesomeIcon icon="home" />
          </Link>
        </h2>
        {sampleLocation.pathname.split("/")[1] === "buy" && <div>Buy</div>}
        {sampleLocation.pathname.split("/")[1] === "sell" && <div>Sell</div>}
        <div className="d-flex align-items-center">
          <Link to={"/connexion"}>
            <FontAwesomeIcon icon="user" className="me-3" style={{ fontSize: "200%" }} />
          </Link>
          <div>
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
            <div className="d-flex align-items-center">
              {utilisateur.account}
              <FontAwesomeIcon icon="euro-sign" className="ms-1" />
            </div>
          </div>
        </div>
      </div>
      <Outlet />
    </div>
  );
}

export default Accueil;
