package org.diego.dao.db.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.AirlinesRepository;
import org.diego.dao.model.AirlinesEntity;

public class AirlinesRepositoryImpl implements AirlinesRepository {
    
    public static final String SEPARATOR = ",";
    
    public List<AirlinesEntity> list = new ArrayList<>();
    
    public AirlinesRepositoryImpl() {
        initData();
    }
    
    public void initData() {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader("src/main/resources/airlines.csv"));
            String line = br.readLine();
            AirlinesEntity entity;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                entity = new AirlinesEntity();
                if (fields[0] != null || (fields[0].length()) != 0) {
                    entity.setCode(fields[0]);
                    entity.setName(fields[1]);
                    entity.setInfantPrice(Double.valueOf(fields[2]));
                    list.add(entity);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public AirlinesEntity getByIATA(String code) {
        AirlinesEntity entity = null;
        for (AirlinesEntity air : list) {
            if (air.getCode().equals(code.substring(0, 2)))
                entity = air;
        }
        return entity;
    }
    
    @Override
    public List<AirlinesEntity> getAll() {
        return list;
    }
    
}
