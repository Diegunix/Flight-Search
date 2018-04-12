package org.diego.dao.db;

import java.util.List;

import org.diego.dao.model.PricingRulesEntity;

public interface PricingRulesRepository {
    
    public PricingRulesEntity getByDepartoure(int departureFrom);
    
    public List<PricingRulesEntity> getAll();
}
