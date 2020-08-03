package com.aleksandr0412.hibernate2.entities;

import javax.persistence.*;

@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "current_bet")
    private Long currentBet;

    @ManyToOne
    @JoinColumn(name = "last_bet_owner")
    private User lastBetOwner;

    @Version
    Long version;

    public Lot() {
    }

    public Lot(String title, Long currentBet) {
        this.title = title;
        this.currentBet = currentBet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(Long currentBet) {
        this.currentBet = currentBet;
    }

    public User getLastBetOwner() {
        return lastBetOwner;
    }

    public void setLastBetOwner(User lastBetOwner) {
        this.lastBetOwner = lastBetOwner;
    }

    public Long getVersion() {
        return version;
    }
}