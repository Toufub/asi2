package com.asi2.card.tools;

import com.asi2.card.b.model.BImgDTO;
import com.asi2.card.model.BImg;

public class BImgMapper {
	
	public static BImgDTO FromBImgToDTO(BImg b){
		BImgDTO bDTO=new BImgDTO();
		bDTO.setId(b.getId());
		bDTO.setImgURL(b.getImgURL());
		bDTO.setTagList(b.getTagList());
		bDTO.setTitle(b.getTitle());
		return bDTO;
	}
	
	public static BImg FromDTOTOBImg(BImgDTO bDTO) {
		BImg b=new BImg();
		b.setId(bDTO.getId());
		b.setImgURL(bDTO.getImgURL());
		b.setTagList(bDTO.getTagList());
		b.setTitle(bDTO.getTitle());
		return b;
	}

}
