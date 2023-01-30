package io.m1nque.mybatis.demo.repository;

import io.m1nque.mybatis.demo.model.dto.UserParam;
import io.m1nque.mybatis.demo.model.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserVo> selectUsers (UserParam userParam);

    List<UserVo> selectUsersWithItems (UserParam userParam);
}
