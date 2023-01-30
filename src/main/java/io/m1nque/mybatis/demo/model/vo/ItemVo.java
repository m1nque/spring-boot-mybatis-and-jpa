package io.m1nque.mybatis.demo.model.vo;

import io.m1nque.mybatis.demo.model.entity.ItemEntity;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Alias("Item")
public class ItemVo {
    public static ItemVo of (ItemEntity ie) {
        return new ItemVo(ie.getId(), ie.getItemName(), ie.getItemType());
    }

    public enum ItemType {EQUIPTMENT, CONSUMABLE, ETC};

    private int id;
    private String itemName;

    private ItemType itemType;
}
