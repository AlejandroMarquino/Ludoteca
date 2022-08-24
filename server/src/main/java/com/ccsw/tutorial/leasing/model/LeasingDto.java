package com.ccsw.tutorial.leasing.model;

import java.sql.Date;

import com.ccsw.tutorial.customer.model.CustomerDto;
import com.ccsw.tutorial.game.model.GameDto;

public class LeasingDto {

    private Long id;

    private GameDto game;

    private CustomerDto customer;

    private Date leasing_date;
    private Date end_date;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return game
     */
    public GameDto getGame() {
        return game;
    }

    /**
     * @param game new value of {@link #getGame}
     */
    public void setGame(GameDto game) {
        this.game = game;
    }

    /**
     * @return customer
     */
    public CustomerDto getCustomer() {
        return customer;
    }

    /**
     * @param customer new value of {@link #getCustomer}
     */
    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    /**
     * @return leasingDate
     */
    public Date getLeasingDate() {
        return leasing_date;
    }

    /**
     * @param leasingDate new value of {@link #getLeasingDate}
     */
    public void setLeasingDate(Date leasing_date) {
        this.leasing_date = leasing_date;
    }

    /**
     * @return endDate
     */
    public Date getEndDate() {
        return end_date;
    }

    /**
     * @param enDate new value of {@link #getEnDate}
     */
    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

}
