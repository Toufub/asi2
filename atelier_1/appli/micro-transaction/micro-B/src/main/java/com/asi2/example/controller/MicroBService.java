package com.asi2.card.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.asi2.card.b.model.BImgDTO;
import com.asi2.card.model.BImg;
import com.asi2.card.tools.BImgMapper;

@Service
public class MicroBService {
	private final MicroBRepository bRepo;
	
	public MicroBService(MicroBRepository bRepo) {
		this.bRepo=bRepo;
	}

	public List<BImgDTO> getAllBImg() {
		List<BImgDTO> bDtoList=new ArrayList<BImgDTO>();
		Iterable<BImg> bList = bRepo.findAll();
		for(BImg b:bList) {
			bDtoList.add(BImgMapper.FromBImgToDTO(b));
		}
		return bDtoList;
	}

	public Integer addBImg(BImgDTO bDTO) {
		BImg b= BImgMapper.FromDTOTOBImg(bDTO);
		
		BImg bInDb=bRepo.save(b);
		return bInDb.getId();
	}

	public BImgDTO getRand() {
		List<BImgDTO> bDTOList =getAllBImg();
		int randomNum = ThreadLocalRandom.current().nextInt(0, bDTOList.size());
		return bDTOList.get(randomNum);
	}

}
