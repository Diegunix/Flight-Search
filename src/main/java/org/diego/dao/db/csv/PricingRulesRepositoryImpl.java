package org.diego.dao.db.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.diego.dao.db.PricingRulesRepository;
import org.diego.dao.model.PricingRulesEntity;

public class PricingRulesRepositoryImpl implements PricingRulesRepository {
    public static final String SEPARATOR = ",";
    
    public List<PricingRulesEntity> list = new ArrayList<>();
    
    public PricingRulesRepositoryImpl() {
        initData();
    }
    
    public void initData() {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader("src/main/resources/pricingRules.csv"));
            String line = br.readLine();
            PricingRulesEntity entity;
            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                entity = new PricingRulesEntity();
                if (fields[0] != null || (fields[0].length()) != 0) {
                    entity.setMinDay(Integer.parseInt(fields[0]));
                    entity.setMaxDay(Integer.parseInt(fields[1]));
                    entity.setPercentBase(Integer.parseInt(fields[2]));
                    list.add(entity);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            
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
