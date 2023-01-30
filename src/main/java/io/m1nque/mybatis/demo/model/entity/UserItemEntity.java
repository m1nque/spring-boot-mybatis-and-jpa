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
@Table(name = "user_item", uniqueConstraints = @UniqueConstraint(name = "UK_UIE", columnNames = {"userId", "itemId"}))
public class UserItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mapId;

    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_UIE_USER_ID", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "itemId", foreignKey = @ForeignKey(name = "FK_UIE_ITEM_ID", value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne
    private ItemEntity item;
}
