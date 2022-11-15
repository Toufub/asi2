import {cardActions} from './card.actions.js'

const initStateValue = {
     selectedCardId:null
}
export const cardReducer = (state = {
     state: initStateValue
   },action) => {
     if(action.type === cardActions.setSelectedCardId) return {
          ...state,
          selectedCardId: action.payload
        };
     return state;
   }