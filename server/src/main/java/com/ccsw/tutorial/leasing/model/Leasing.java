package com.ccsw.tutorial.leasing.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.tutorial.customer.model.Customer;
import com.ccsw.tutorial.game.model.Game;

/**
 * Objeto Entity de la clase Leasing Métodos y atributos para Leasing
 * 
 * @author Alejandro Marquino Fernandez
 */

@Entity
@Table(name = "Leasing")
public class Leasing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "leasingDate", nullable = false)
    private Date leasingDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getLeasingDate() {
        return leasingDate;
    }

    public void setLeasingDate(Date leasingDate) {
        this.leasingDate = leasingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void EndDate(Date endDate) {
        this.endDate = endDate;
    }

}
