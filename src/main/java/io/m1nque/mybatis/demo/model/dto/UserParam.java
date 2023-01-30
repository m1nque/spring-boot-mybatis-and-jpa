package io.m1nque.mybatis.demo.model.dto;

import lombok.Builder;
import org.apache.ibatis.type.Alias;

@Builder
@Alias("UserParam")
public record UserParam (Integer id, String account){
}
