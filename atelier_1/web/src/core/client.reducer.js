import { setCookie } from "../utils/cookie.js";
import { clientActions } from "./client.actions.js";

const initStateValue = {
  selectedClientId: null,
  selectedCardId: null,
};
export const clientReducer = (
  state = {
    state: initStateValue,
  },
  action
) => {
  if (action.type === clientActions.setSelectedClientId) {
    setCookie("clientId", action.payload);
    return {
      ...state,
      selectedClientId: action.payload,
    };
  }

  if (action.type === clientActions.setSelectedCardId)
    return {
      ...state,
      selectedCardId: action.payload,
    };
  return state;
};
