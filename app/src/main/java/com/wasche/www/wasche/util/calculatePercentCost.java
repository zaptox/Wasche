package com.wasche.www.wasche.util;

public class calculatePercentCost {

    public static double getUrgentCost(double actualCost,double percent){

        return actualCost+(actualCost*(percent/100));
    }

}
