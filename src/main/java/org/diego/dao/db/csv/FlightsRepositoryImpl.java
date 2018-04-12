package org.diego.dao.db.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.FlightsRepository;
import org.diego.dao.model.FlightsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightsRepositoryImpl implements FlightsRepository {
    
    Logger log = LoggerFactory.getLogger(FlightsRepositoryImpl.class);
    
    public static final String SEPARATOR = ",";
    
    public static final String FILE = "src/main/resources/flights.csv";
    
    public List<FlightsEntity> list = new ArrayList<>();
    
    public FlightsRepositoryImpl() {
        initData();
    }
    
    public void initData() {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(FILE));
            String line = br.readLine();
            FlightsEntity entity;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                entity = new FlightsEntity();
                if (fields[0] != null && (fields[0].length()) > 0) {
                    entity.setOriginCode(fields[0]);
                    entity.setDestinationCode(fields[1]);
                    entity.setAirlineCode(fields[2]);
                    entity.setBasePrice(Double.valueOf(fields[3]));
                    list.add(entity);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            log.error("Error load data flights");
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
