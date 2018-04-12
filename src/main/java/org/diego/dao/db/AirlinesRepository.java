package org.diego.dao.db;

import java.util.List;

import org.diego.dao.model.AirlinesEntity;

public interface AirlinesRepository {
    
    public AirlinesEntity getByIATA(String code);
    
    public List<AirlinesEntity> getAll();
    
}
