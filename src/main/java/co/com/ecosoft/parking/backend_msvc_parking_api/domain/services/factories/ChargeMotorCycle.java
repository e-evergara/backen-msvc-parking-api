package co.com.ecosoft.parking.backend_msvc_parking_api.domain.services.factories;

import co.com.ecosoft.parking.backend_msvc_parking_api.domain.models.Registry;

public class ChargeMotorCycle implements ChargeParking {

    private static final int HOURS_CASH_BY_DAY = 9;
    private static final int VALUE_PER_HOUR = 500;
    private static final int VALUE_BY_DAY = 4000;
    private static final int MILLISECOND_PER_HOUR = 3600000;
    private static final int MILLISECOND_PER_MINUTE = 60000;
    private static final int MINIMUM_MINUTE = 10;
    private static final int HOUR_PER_DAY = 24;

    private static final int VALUE_ADDITIONAL = 2000;
    private static final int DISPLACEMENT_MAXIMUM = 500;

    @Override
    public void setCharge(Registry registry) {
        long value;
        double millisecond = (registry.getDateDeparture().getTime() - registry.getDateArrival().getTime());
        double hour = (millisecond/ MILLISECOND_PER_HOUR);
        double minute = (millisecond/ MILLISECOND_PER_MINUTE);
        long totalHour = Math.round(hour);
        long totalMinute = Math.round(minute);
        int totalDay = (int)  totalHour / HOUR_PER_DAY;
        int  totalHourNewDay = (int) totalHour % HOUR_PER_DAY;


        if(totalHour < HOURS_CASH_BY_DAY){
            if((totalMinute >= MINIMUM_MINUTE) && (totalHour == 0)){
                value = VALUE_PER_HOUR;
            }else{
                value = totalHour * VALUE_PER_HOUR;
            }
        }else if(totalHourNewDay == 0 || (totalHourNewDay >= HOURS_CASH_BY_DAY && totalHourNewDay < HOUR_PER_DAY)){
            value = ((long) VALUE_BY_DAY * (totalDay == 0 ? 1:totalDay));
        }else{
            value = (((long) VALUE_BY_DAY * totalDay) + (totalHourNewDay * VALUE_PER_HOUR));
        }

        if(Integer.parseInt(registry.getDisplacement()) > DISPLACEMENT_MAXIMUM ){
            value = value + VALUE_ADDITIONAL;
        }
        registry.setValue(value);
    }
}