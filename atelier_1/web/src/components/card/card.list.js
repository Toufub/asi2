import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setSelectedCardId } from "../../core/client.actions.js";
import { selectedClientId } from "../../core/client.selector.js";

function CardList(props) {
  const [cards, setCards] = useState([]);
  const userId = useSelector(selectedClientId);

  const dispatch = useDispatch();

  const selectCard = (id) => {
    dispatch(setSelectedCardId(id));
  };

  useEffect(() => {
    const url = "https://asi2-backend-market.herokuapp.com/cards";
    fetch(url)
      .then((resp) => resp.json())
      .then((values) => {
        setCards([]);
        console.log(userId,props.mine,values.filter((x) => (x.userId = userId)))
        if (props.mine && userId) setCards(values.filter((x) => (x.userId = userId)));
        else setCards(values);
      });
  }, []);

  const listItems = cards.map((card) => (
    <tr className="d-flex cursor-pointer" key={card.id} onClick={() => selectCard(card.id)}>
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
