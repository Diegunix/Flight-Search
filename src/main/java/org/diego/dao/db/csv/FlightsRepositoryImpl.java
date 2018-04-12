package org.diego.dao.db.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.FlightsRepository;
import org.diego.dao.model.FlightsEntity;

public class FlightsRepositoryImpl implements FlightsRepository {
    public static final String SEPARATOR = ",";
    
    public List<FlightsEntity> list = new ArrayList<>();
    
    public FlightsRepositoryImpl() {
        initData();
    }
    
    public void initData() {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader("src/main/resources/flights.csv"));
            String line = br.readLine();
            FlightsEntity entity;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                entity = new FlightsEntity();
                if (fields[0] != null || (fields[0].length()) != 0) {
                    entity.setOriginCode(fields[0]);
                    entity.setDestinationCode(fields[1]);
                    entity.setAirlineCode(fields[2]);
                    entity.setBasePrice(Double.valueOf(fields[3]));
                    list.add(entity);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public List<FlightsEntity> getByFlight(String origin, String destination) {
        List<FlightsEntity> entityList = new ArrayList<>();
        for (FlightsEntity fly : list) {
            if (fly.getOriginCode().equals(origin) && fly.getDestinationCode().equals(destination))
                entityList.add(fly);
        }
        return entityList;
    }
    
    @Override
    public List<FlightsEntity> getAll() {
        return list;
    }
    
}
