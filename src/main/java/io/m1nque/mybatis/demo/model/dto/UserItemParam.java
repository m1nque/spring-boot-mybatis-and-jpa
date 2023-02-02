package io.m1nque.mybatis.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Builder
@Alias("UserItemParam")
public record UserItemParam (Integer itemId, Integer userId, int quantity){
}