package com.ccsw.tutorial.leasing.model;

import java.sql.Date;

import com.ccsw.tutorial.customer.model.CustomerDto;
import com.ccsw.tutorial.game.model.GameDto;

public class LeasingDto {

    private Long id;

    private GameDto game;

    private CustomerDto customer;

    private Date leasingDate;
    private Date endDate;

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
        return leasingDate;
    }

    /**
     * @param leasingDate new value of {@link #getLeasingDate}
     */
    public void setLeasingDate(Date leasingDate) {
        this.leasingDate = leasingDate;
    }

    /**
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param enDate new value of {@link #getEnDate}
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
