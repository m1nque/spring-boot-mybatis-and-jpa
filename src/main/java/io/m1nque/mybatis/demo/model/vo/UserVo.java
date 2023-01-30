package io.m1nque.mybatis.demo.model.vo;

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
@Alias("User")
public class UserVo {
    public UserVo (Integer id, String account) {
        id = id;
        account = account;
    }

    private Integer id;
    private String account;

    @Builder.Default
    private List<ItemVo> items = new ArrayList<>();
}
