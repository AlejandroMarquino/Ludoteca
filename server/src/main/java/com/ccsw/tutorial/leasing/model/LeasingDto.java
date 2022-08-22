package com.ccsw.tutorial.leasing.model;

import java.sql.Date;

import org.springframework.data.domain.Pageable;

import com.ccsw.tutorial.customer.model.CustomerDto;
import com.ccsw.tutorial.game.model.GameDto;

//@author AMF 

public class LeasingDto {

    private Long id;

    private GameDto game;

    private CustomerDto customer;

    private Date leasingDate;
    private Date endDate;

    // @retun id
    public Long getId() {
        return id;
    }

    // @param id new value of {@link #getId}
    public void setId(Long id) {
        this.id = id;
    }

    // @return customer
    public CustomerDto getCustomer() {
        return customer;
    }

    // @param customer new value of {@link #getCustomer}
    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    // @return game
    public GameDto getGame() {
        return game;
    }

    // @param game new value of {@link #getGame}
    public void setGame(GameDto game) {
        this.game = game;
    }

    // @retun leasingDate
    public Date getLeasingDate() {
        return leasingDate;
    }

    // @param leasingDate new value of {@link #getLoan_date}
    public void setLeasingDate(Date leasingDate) {
        this.leasingDate = leasingDate;
    }

    // @return endDate
    public Date getEndDate() {
        return endDate;
    }

    // @param endDate new value of {@link #getEndDate}
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Pageable pageable;

    // @return pageable

    public Pageable getPageable() {
        return this.pageable;
    }

    // @param pageable new vvalue of {@link #getPageable}

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
