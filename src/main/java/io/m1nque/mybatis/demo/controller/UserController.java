package io.m1nque.mybatis.demo.controller;

import io.m1nque.mybatis.demo.model.dto.UserParam;
import io.m1nque.mybatis.demo.model.entity.ItemEntity;
import io.m1nque.mybatis.demo.model.entity.UserEntity;
import io.m1nque.mybatis.demo.model.entity.UserItemEntity;
import io.m1nque.mybatis.demo.model.vo.ItemVo;
import io.m1nque.mybatis.demo.repository.UserItemRepository;
import io.m1nque.mybatis.demo.model.vo.UserVo;
import io.m1nque.mybatis.demo.repository.UserMapper;
import io.m1nque.mybatis.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private final UserItemRepository userItemRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @GetMapping("/jpa/users")
    public List<UserVo> getUsersByJpa (@RequestParam String account) {
        UserParam userParam = UserParam.builder().account(account).build();
//        return userMapper.selectUsers(userParam);
        List<UserEntity> entities = userRepository.findByAccountContains(account);

        return entities.stream().map(e -> UserVo.builder().id(e.getId()).account(e.getAccount()).build()).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/jpa/users/{account}")
    public UserVo getUserWithItemsByJpa (@PathVariable String account) {
        UserParam userParam = UserParam.builder().account(account).build();
        UserEntity userEntity = userRepository.findOne(Example.of(UserEntity.builder().account(account).build())).get();
        UserVo userVo = UserVo.builder().id(userEntity.getId()).account(userEntity.getAccount()).build();

        List<UserItemEntity> itemEntity = userItemRepository.findAll(Example.of(UserItemEntity.builder().user(userEntity).build()));
        List<ItemVo> items = itemEntity.stream()
                .map(e -> ItemVo.of(e.getItem())).toList();

        userVo.setItems(items);

        return userVo;
    }

    @Transactional(readOnly = true)
    @GetMapping("/mybatis/users")
    public List<UserVo> getUsersByMaBatis (@RequestParam String account) {
        UserParam userParam = UserParam.builder().account(account).build();
        return userMapper.selectUsers(userParam);
    }

    @Transactional(readOnly = true)
    @GetMapping("/mybatis/users/{account}")
    public List<UserVo> getUserWithItemsByMaBatis (@PathVariable String account) {
        UserParam userParam = UserParam.builder().account(account).build();
        return userMapper.selectUsersWithItems(userParam);
    }
}
