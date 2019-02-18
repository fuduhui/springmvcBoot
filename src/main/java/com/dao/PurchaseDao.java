package com.dao;

import com.model.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDao {
    public int insertPurchase(Purchase purchase);
}
