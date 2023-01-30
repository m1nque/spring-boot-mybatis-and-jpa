package io.m1nque.mybatis.demo.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("User")
public class UserVo {
    public UserVo (Integer id, String account) {
        id = id;
        account = account;
    }

    private Integer id;
    private String account;

    @Setter
    private List<ItemVo> items;
}
