package io.m1nque.mybatis.demo.model.vo;

import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Builder
@Getter
@Alias("Item")
public class ItemVo {
    public enum ItemType {EQUIPTMENT, CONSUMABLE, ETC};

    private int itemId;
    private String itemName;

    private ItemType itemType;
}
