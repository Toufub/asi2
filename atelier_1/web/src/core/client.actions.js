export const clientActions = {
  setSelectedClientId: "@@clients/SET_SELECTED_CLIENT_ID",
  setSelectedCardId: "@@cards/SET_SELECTED_CARD_ID",
};
export const setSelectedClientId = (selectedClientId) => {
  return {
    type: clientActions.setSelectedClientId,
    payload: selectedClientId,
  };
};

export const setSelectedCardId = (selectedCardId) => {
  return {
    type: clientActions.setSelectedCardId,
    payload: selectedCardId,
  };
};
