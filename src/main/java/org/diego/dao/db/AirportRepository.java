package org.diego.dao.db;

import java.util.List;

import org.diego.dao.model.AirportEntity;

public interface AirportRepository {
    
    public AirportEntity getByCode(String code);
    
    public List<AirportEntity> getAll();
}
