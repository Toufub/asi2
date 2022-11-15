import React, { useEffect, useState } from "react";
import CardGeneral from "./card.general.js";
import CardList from "./card.list.js";
import { selectedCardId } from "../../core/card.selector";
import { useSelector } from "react-redux";

function CardBuy() {
  const [cardSelected, selectCard] = useState(null);
  const cardSelectedId = useSelector(selectedCardId);

  useEffect(() => {
    if (cardSelectedId) {
      fetch(`https://asi2-backend-market.herokuapp.com/card/${cardSelectedId}`)
        .then((resp) => resp.json())
        .then((values) => {
          selectCard(values);
        });
    }
  }, [cardSelectedId]);

  const buy = () => {
     fetch(`https://asi2-backend-market.herokuapp.com/store/buy`,{
          method: "POST",
          body: JSON.stringify({
               "card_id": cardSelectedId,
               "user_id": userId
          })
     });
  }

  return (
    <div className="w-100 d-flex p-3">
      <div style={{ flexBasis: "50%" }} className="flex-grow-1">
        <h3>Market</h3>
        <CardList />
      </div>
      {cardSelected && (
        <div className="d-flex justify-content-center">
          <div style={{ flexBasis: "50%" }} className="mt-5">
            <CardGeneral card={cardSelected} />
          </div>
          <button className="btn btn-success" onClick={buy}>Buy</button>
        </div>
      )}
    </div>
  );
}

export default CardBuy;
