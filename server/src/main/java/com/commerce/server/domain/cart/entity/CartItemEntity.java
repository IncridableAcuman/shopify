package com.commerce.server.domain.cart.entity;

import com.commerce.server.domain.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;
}
