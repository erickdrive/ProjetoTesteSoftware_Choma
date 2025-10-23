package org.example.checkout;

public class CouponResult {
    private double percent;      // 0.10 = 10%  |  0.0 se sem desconto %
    private boolean freeShipping;

    public CouponResult(double percent, boolean freeShipping) {
        this.percent = percent;
        this.freeShipping = freeShipping;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }
}