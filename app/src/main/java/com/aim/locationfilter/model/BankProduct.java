package com.aim.locationfilter.model;

import androidx.annotation.Keep;

@Keep
public class BankProduct {
    public boolean jumbo;
    public boolean insta;
    public boolean le;
    public boolean business_CreditCards;

    public boolean isBusiness_CreditCards() {
        return business_CreditCards;
    }

    public void setBusiness_CreditCards(boolean business_CreditCards) {
        this.business_CreditCards = business_CreditCards;
    }

    public boolean isJumbo() {
        return jumbo;
    }

    public void setJumbo(boolean jumbo) {
        this.jumbo = jumbo;
    }

    public boolean isInsta() {
        return insta;
    }

    public void setInsta(boolean insta) {
        this.insta = insta;
    }

    public boolean isLe() {
        return le;
    }

    public void setLe(boolean le) {
        this.le = le;
    }

    @Override
    public String toString() {
        return "BankProduct{" +
                "jumbo=" + jumbo +
                ", insta=" + insta +
                ", le=" + le +
                ", business_CreditCards=" + business_CreditCards +
                '}';
    }
}
