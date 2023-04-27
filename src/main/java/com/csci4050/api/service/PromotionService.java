package com.csci4050.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.PromotionNotFoundException;
import com.csci4050.api.model.Promotion;
import com.csci4050.api.repository.PromotionRepository;

import jakarta.transaction.Transactional;

@Service
public class PromotionService {
	
	@Autowired
	PromotionRepository promotionRepository;
	
	@Transactional
	public Promotion createPromotion(Promotion promotion) {
		return promotionRepository.save(promotion);
	}
	
	@Transactional
	public Promotion updatePromotion(Promotion promotion) throws PromotionNotFoundException {
		if(!promotionRepository.existsById(promotion.getId())) {
			throw new PromotionNotFoundException(promotion.getId());
		}
		return promotionRepository.save(promotion);
	}

	@Transactional
	public List<Promotion> getAllPromotions() {
		return promotionRepository.findAll();
	}

}
