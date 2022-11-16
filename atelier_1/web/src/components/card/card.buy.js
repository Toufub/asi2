import React, { useEffect, useState } from "react";
import CardGeneral from "./card.general.js";
import CardList from "./card.list.js";
import { selectedCardId } from "../../core/client.selector";
import { selectedClientId } from "../../core/client.selector";
import { useSelector } from "react-redux";

function CardBuy() {
  const [cardSelected, selectCard] = useState(null);
  const cardSelectedId = useSelector(selectedCardId);
  const userId = useSelector(selectedClientId);

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
    fetch(`https://asi2-backend-market.herokuapp.com/store/buy`, {
      method: "POST",
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        card_id: cardSelectedId,
        user_id: userId,
      }),
    }).then((resp) => resp.json())
    .then((value) => {
      if(value === false) alert("Vous n'avez pas assez d'argent pour acheter cette carte")
    });
  };

  return (
    <div className="w-100 d-flex p-3">
      <div style={{ flexBasis: "50%" }} className="flex-grow-1">
        <h3>Market</h3>
        <CardList />
      </div>
      {cardSelected && (
        <div className="d-flex justify-content-center align-items-center w-100" style={{ flexBasis: "50%" }}>
          <div  className="mt-5">
            <CardGeneral card={cardSelected} />
            <div className="text-center mt-2">
              <button className="btn btn-success" onClick={buy}>
                Buy
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default CardBuy;
