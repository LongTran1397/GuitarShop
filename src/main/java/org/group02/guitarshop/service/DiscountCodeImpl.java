package org.group02.guitarshop.service;

import org.group02.guitarshop.entity.DiscountCode;
import org.group02.guitarshop.repository.DiscountCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class DiscountCodeImpl implements DiscountCodeService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private DiscountCodeRepository repository;

    @Override
    public DiscountCode getDiscountCodeByCode(String code) {
        try {
            Query query = entityManager.createNativeQuery("SELECT * FROM DISCOUNT_CODE WHERE Code='" + code + "' AND (GETDATE() BETWEEN Start_Date AND End_Date)", DiscountCode.class);
            DiscountCode discountCode = (DiscountCode) query.getSingleResult();
            return discountCode;
        } catch (Exception e){
            return null;
        }
    }
}
