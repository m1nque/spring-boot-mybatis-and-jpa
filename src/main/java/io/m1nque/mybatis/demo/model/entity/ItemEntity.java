package io.m1nque.mybatis.demo.model.entity;

import io.m1nque.mybatis.demo.model.vo.ItemVo;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String itemName;

    ItemVo.ItemType itemType;
}
