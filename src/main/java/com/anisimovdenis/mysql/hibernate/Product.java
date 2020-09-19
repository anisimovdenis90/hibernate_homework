package com.anisimovdenis.mysql.hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @ManyToMany
    @JoinTable(
            name = "users_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Product() {
    }

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, cost = %d]", id, title, cost);
    }
}
