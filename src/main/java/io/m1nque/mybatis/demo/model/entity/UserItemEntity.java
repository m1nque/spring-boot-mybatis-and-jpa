package io.m1nque.mybatis.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_item", uniqueConstraints = @UniqueConstraint(name = "UK_UIE", columnNames = {"user_id", "item_id"}))
public class UserItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mapId;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_UIE_USER_ID", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne
    private UserEntity user;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_UIE_ITEM_ID", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne
    private ItemEntity item;
    
    private Integer quantity;


    public UserItemEntity updateQuantity (int quantity) {
        this.quantity = quantity;
        return this;
    }
}
