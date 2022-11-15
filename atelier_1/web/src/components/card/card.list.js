import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { setSelectedCardId } from '../../core/card.actions.js';

function CardList() {
  const [cards, setCards] = useState([]);

  const dispatch = useDispatch();

  const selectCard = (id) => {
    dispatch(setSelectedCardId(id));
  };

  useEffect(() => {
    fetch("https://asi2-backend-market.herokuapp.com/cards")
      .then((resp) => resp.json())
      .then((values) => {
        setCards(values);
      });
  }, []);

  const listItems = cards.map((card) => (
    <tr className="d-flex cursor-pointer" key={card.id}  onClick={() => selectCard(card.id)}>
      <td style={{ flexBasis: "15%" }}>{card.name}</td>
      <td style={{ flexBasis: "20%" }}>{card.description}</td>
      <td style={{ flexBasis: "19%" }}>{card.family}</td>
      <td style={{ flexBasis: "12%" }}>{card.affinity}</td>
      <td style={{ flexBasis: "12%" }}>{card.energy}</td>
      <td style={{ flexBasis: "12%" }}>{card.hp}</td>
      <td style={{ flexBasis: "12%" }}>{card.price}</td>
    </tr>
  ));
  return (
    <table className="table table-striped table-bordered table-hover">
      <thead>
        <tr className="d-flex">
          <th style={{ flexBasis: "15%" }}>Name</th>
          <th style={{ flexBasis: "20%" }}>Description</th>
          <th style={{ flexBasis: "19%" }}>Family</th>
          <th style={{ flexBasis: "12%" }}>Affinity</th>
          <th style={{ flexBasis: "12%" }}>Energy</th>
          <th style={{ flexBasis: "12%" }}>HP</th>
          <th style={{ flexBasis: "12%" }}>Price</th>
        </tr>
      </thead>
      <tbody>{listItems}</tbody>
    </table>
  );
}

export default CardList;
