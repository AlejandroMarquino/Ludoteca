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

    private Date leasing_date;
    private Date end_date;

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

    // @retun leasing_date
    public Date getLeasing_date() {
        return leasing_date;
    }

    // @param leasing_date new value of {@link #getLoan_date}
    public void setLeasing_date(Date loan_date) {
        this.leasing_date = loan_date;
    }

    // @return end_date
    public Date getEnd_date() {
        return end_date;
    }

    // @param end_date new value of {@link #getEnd_date}
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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
