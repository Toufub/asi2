package com.asi2.card.tools;

import com.asi2.card.a.model.ACollectionDTO;
import com.asi2.card.model.ACollection;

public class ACollectionMapper {
	
	public static ACollectionDTO FromACollectionToDTO(ACollection a){
		ACollectionDTO aDTO=new ACollectionDTO();
		aDTO.setId(a.getId());
		aDTO.setTitle(a.getTitle());
		aDTO.setDescription(a.getDescription());
		aDTO.setbImgRef(a.getbImgRef());
		return aDTO;
	}
	
	public static ACollection FromDTOTOACollection(ACollectionDTO aDTO) {
		ACollection a=new ACollection();
		a.setId(aDTO.getId());
		a.setTitle(aDTO.getTitle());
		a.setDescription(aDTO.getDescription());
		a.setbImgRef(aDTO.getbImgRef());
		return a;
	}

}
