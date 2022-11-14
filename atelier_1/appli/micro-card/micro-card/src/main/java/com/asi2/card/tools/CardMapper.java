package com.asi2.card.tools;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.asi2.card.model.Card;
import com.asi2.card.model.CardDto;

@Mapper
public interface CardMapper {
	CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
	Card toModel(CardDto cardDto);
}

