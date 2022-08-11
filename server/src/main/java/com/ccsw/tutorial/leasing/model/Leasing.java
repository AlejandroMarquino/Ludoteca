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

// @author AMF
// es la clase que más trabajo y complicaciones me ha dado del proyecto. 

@Entity
@Table(name = "Leasing")
public class Leasing {

    // Creamos el identificador del prestamo.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // la anotación @ManyToOne nos genera una relación de muchos a uno.
    // la anotación @JoinColumn hace referencia a la columna clave que define la
    // relación
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "leasing_date", nullable = false)
    private Date leasing_date;

    @Column(name = "end_date", nullable = false)
    private Date end_date;

    // @retun id
    public Long getId() {
        return id;
    }

    // @param id new value of {@link #getId}
    public void setId(Long id) {
        this.id = id;
    }

    // @return game
    public Game getGame() {
        return game;
    }

    // @param game new value of {@link #getGame}
    public void setGame(Game game) {
        this.game = game;
    }

    // @return customer
    public Customer getCustomer() {
        return customer;
    }

    // @param customer new value of {@link #getCustomer}
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // @return la fecha de prestamo
    public Date getLeasing_date() {
        return leasing_date;
    }

    // @param para setear la fecha del inicio del prestamo
    public void setLeasing_date(Date leasing_date) {
        this.leasing_date = leasing_date;
    }

    // @return la fecha de devolución
    public Date getEnd_date() {
        return end_date;
    }

    // @param para setear la fecha de devolución
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

}