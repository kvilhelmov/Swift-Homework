package insurance;

import java.math.BigDecimal;

public class SocialInsuranceRecord {
    private final int _year;
    private final int _month;
    private final BigDecimal _amount;

    public SocialInsuranceRecord(int year, int month, BigDecimal amount) {
        this._year = year;
        this._month = month;
        this._amount = amount;
    }
    
    public BigDecimal getAmount(){
        return _amount;
    }

    public int getMonth() {
        return _month;
    }

    public int getYear() {
        return _year;
    }
    
}
