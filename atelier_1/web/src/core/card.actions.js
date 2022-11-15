export const cardActions = {
  setSelectedCardId: "@@cards/SET_SELECTED_CARD_ID",
};
export const setSelectedCardId = (selectedCardId) => {
  return {
    type: cardActions.setSelectedCardId,
    payload: selectedCardId,
  };
};
