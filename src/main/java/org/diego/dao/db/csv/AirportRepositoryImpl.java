package org.diego.dao.db.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.AirportRepository;
import org.diego.dao.model.AirportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirportRepositoryImpl implements AirportRepository {
    
    Logger log = LoggerFactory.getLogger(AirportRepositoryImpl.class);
    
    public static final String SEPARATOR = ",";
    
    public static final String FILE = "src/main/resources/airports.csv";
    
    public List<AirportEntity> list = new ArrayList<>();
    
    public AirportRepositoryImpl() {
        initData();
    }
    
    public void initData() {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(FILE));
            String line = br.readLine();
            AirportEntity entity;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                entity = new AirportEntity();
                if (fields[0] != null && (fields[0].length()) > 0) {
                    entity.setCode(fields[0]);
                    entity.setName(fields[1]);
                    list.add(entity);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            log.error("Error load data airport");
        }
        
    }
    
    @Override
    public AirportEntity getByCode(String code) {
        AirportEntity entity = null;
        for (AirportEntity air : list) {
            if (air.getCode().equals(code))
                entity = air;
        }
        return entity;
    }
    
    @Override
    public List<AirportEntity> getAll() {
        return list;
    }
}
