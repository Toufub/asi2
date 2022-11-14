package com.asi2.card.tools;
import com.asi2.common.model.CardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.asi2.card.model.Card;

@Mapper
public interface CardMapper {
	CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
	Card toModel(CardDTO cardDto);
}

