package org.diego.dao.db.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.PricingRulesRepository;
import org.diego.dao.model.PricingRulesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricingRulesRepositoryImpl implements PricingRulesRepository {
    
    Logger log = LoggerFactory.getLogger(PricingRulesRepositoryImpl.class);
    
    public static final String SEPARATOR = ",";
    
    public static final String FILE = "src/main/resources/pricingRules.csv";
    
    public List<PricingRulesEntity> list = new ArrayList<>();
    
    public PricingRulesRepositoryImpl() {
        initData();
    }
    
    public void initData() {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(FILE));
            String line = br.readLine();
            PricingRulesEntity entity;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                entity = new PricingRulesEntity();
                if (fields[0] != null && (fields[0].length()) > 0) {
                    entity.setMinDay(Integer.parseInt(fields[0]));
                    entity.setMaxDay(Integer.parseInt(fields[1]));
                    entity.setPercentBase(Integer.parseInt(fields[2]));
                    list.add(entity);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            log.error("Error load data pricing");
        }
    }
    
    @Override
    public PricingRulesEntity getByDepartoure(int departureFrom) {
        PricingRulesEntity entity = null;
        for (PricingRulesEntity price : list) {
            if (price.getMinDay() <= departureFrom && price.getMaxDay() >= departureFrom)
                entity = price;
        }
        return entity;
    }
    
    @Override
    public List<PricingRulesEntity> getAll() {
        return list;
    }
}
