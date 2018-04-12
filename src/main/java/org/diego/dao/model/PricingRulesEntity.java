package org.diego.dao.model;

import java.io.Serializable;

public class PricingRulesEntity implements Serializable {
    
    private static final long serialVersionUID = -787815255472810873L;
    
    private int minDay;
    
    private int maxDay;
    
    private int percentBase;
    
    public int getMinDay() {
        return minDay;
    }
    
    public void setMinDay(int minDay) {
        this.minDay = minDay;
    }
    
    public int getMaxDay() {
        return maxDay;
    }
    
    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }
    
    public int getPercentBase() {
        return percentBase;
    }
    
    public void setPercentBase(int percentBase) {
        this.percentBase = percentBase;
    }
    
}
