package io.m1nque.mybatis.demo.model.vo;

import io.m1nque.mybatis.demo.model.entity.ItemEntity;
import io.m1nque.mybatis.demo.model.entity.UserItemEntity;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("Item")
public class ItemVo {
    public ItemVo (int id, String itemName, ItemType itemType) {
        id = id;
        itemName = itemName;
        itemType = itemType;
    }

    public static ItemVo of (ItemEntity ie) {
        return new ItemVo(ie.getId(), ie.getItemName(), ie.getItemType());
    }

    public static ItemVo of (UserItemEntity uie) {
        ItemEntity ie = uie.getItem();
        return new ItemVo(ie.getId(), ie.getItemName(), ie.getItemType(), uie.getQuantity());
    }

    public enum ItemType {EQUIPTMENT, CONSUMABLE, ETC};

    private int id;
    private String itemName;

    private ItemType itemType;

    private Integer quantity;
}
