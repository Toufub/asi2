import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card } from "react-bootstrap";

function CardGeneral(props) {
  return (
    <Card style={{width:"350px",height:"500px"}}>
      <Card.Title>
        <div className="d-flex align-items-center justify-content-between">
          <div>
            <FontAwesomeIcon icon="coffee" />
            {props.card.energy}
          </div>
          {props.card.family}
          <div>
            {props.card.hp}
            <FontAwesomeIcon icon="coffee" />
          </div>
        </div>
      </Card.Title>
      <Card.Body>
        <div className="d-flex align-items-center justify-content-center">{props.card.name}</div>
        <img src={props.card.imgUrl} alt="imgCard" style={{width:"100%"}} />
        <div> {props.card.description}</div>
      </Card.Body>
    </Card>
  );
}

export default CardGeneral;
