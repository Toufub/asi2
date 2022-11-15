import { Link } from "react-router-dom";
import Card from "react-bootstrap/Card";

function Menu() {

  return (
    <div>
      <div style={{ fontSize: "400%" }} className="p-5 h-100 w-100">
        <div className="d-flex align-items-center justify-content-around">
          <Card style={{backgroundColor: "rgb(219 218 218)" }}>
            <Card.Text className="p-3">
              <Link to="/buy">Buy</Link>
            </Card.Text>
          </Card>
          <Card style={{backgroundColor: "rgb(219 218 218)" }}>
            <Card.Text className="p-3">
              <Link to="/sell">Sell</Link>
            </Card.Text>
          </Card>
        </div>
        <div className="d-flex align-items-center justify-content-center">
          <Card style={{backgroundColor: "rgb(219 218 218)" }}>
            <Card.Text className="p-3">
              <Link to="/play">Play</Link>
            </Card.Text>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default Menu;
