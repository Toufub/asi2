package com.asi2.card.tools;

import com.asi2.card.model.Card;
import com.asi2.common.model.CardDTO;

public class CardMapper {

    public static CardDTO FromCardToDTO(Card card){
        CardDTO cardDTO=new CardDTO();
        cardDTO.setAttack(card.getAttack());
        cardDTO.setDefence(card.getDefence());
        cardDTO.setHp(card.getHp());
        cardDTO.setStoreId(card.getStoreId());
        cardDTO.setImgUrl(card.getImgUrl());
        cardDTO.setSmallImgUrl(card.getSmallImgUrl());
        cardDTO.setId(card.getId());
        cardDTO.setAffinity(card.getAffinity());
        cardDTO.setPrice(card.getPrice());
        cardDTO.setDescription(card.getDescription());
        cardDTO.setEnergy(card.getEnergy());
        cardDTO.setName(card.getName());
        cardDTO.setFamily(card.getFamily());
        cardDTO.setUserId(card.getUserId());
        return cardDTO;
    }

    public static Card FromDTOToTransaction(CardDTO cardDTO) {
        Card card=new Card();
        card.setAttack(cardDTO.getAttack());
        card.setDefence(cardDTO.getDefence());
        card.setHp(cardDTO.getHp());
        card.setStoreId(cardDTO.getStoreId());
        card.setImgUrl(cardDTO.getImgUrl());
        card.setSmallImgUrl(cardDTO.getSmallImgUrl());
        card.setId(cardDTO.getId());
        card.setAffinity(cardDTO.getAffinity());
        card.setPrice(cardDTO.getPrice());
        card.setDescription(cardDTO.getDescription());
        card.setEnergy(cardDTO.getEnergy());
        card.setName(cardDTO.getName());
        card.setFamily(cardDTO.getFamily());
        card.setUserId(cardDTO.getUserId());
        return card;
    }

}
