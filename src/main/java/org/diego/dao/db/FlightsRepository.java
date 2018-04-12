package org.diego.dao.db;

import java.util.List;

import org.diego.dao.model.FlightsEntity;

public interface FlightsRepository {
    
    public List<FlightsEntity> getByFlight(String origin, String destination);
    
    public List<FlightsEntity> getAll();
}
