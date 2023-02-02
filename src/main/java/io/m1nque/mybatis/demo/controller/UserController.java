package io.m1nque.mybatis.demo.controller;

import io.m1nque.mybatis.demo.model.dto.UserItemParam;
import io.m1nque.mybatis.demo.model.dto.UserParam;
import io.m1nque.mybatis.demo.model.entity.ItemEntity;
import io.m1nque.mybatis.demo.model.entity.UserEntity;
import io.m1nque.mybatis.demo.model.entity.UserItemEntity;
import io.m1nque.mybatis.demo.model.vo.ItemVo;
import io.m1nque.mybatis.demo.repository.ItemMapper;
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
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    @GetMapping("/jpa/users")
    public List<UserVo> getUsersByJpa (@RequestParam String account) {
        List<UserEntity> entities = userRepository.findByAccountContains(account);

        return entities.stream().map(e -> UserVo.builder().id(e.getId()).account(e.getAccount()).build()).toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/mybatis/users")
    public List<UserVo> getUsersByMaBatis (@RequestParam String account) {
        UserParam userParam = UserParam.builder().account(account).build();
        return userMapper.selectUsers(userParam);
    }

    @Transactional(readOnly = true)
    @GetMapping("/jpa/users/{account}")
    public UserVo getUserWithItemsByJpa (@PathVariable String account) {
        UserEntity userEntity = userRepository.findOne(Example.of(UserEntity.builder().account(account).build())).get();

        List<UserItemEntity> userItemEntities = userItemRepository.findAll(Example.of(UserItemEntity.builder().user(userEntity).build()));
        List<ItemVo> items = userItemEntities.stream()
                .map(e -> ItemVo.of(e)).toList();

        UserVo userVo = UserVo.builder().id(userEntity.getId()).account(userEntity.getAccount()).build();
        userVo.setItems(items);

        return userVo;
    }

    @Transactional(readOnly = true)
    @GetMapping("/mybatis/users/{account}")
    public UserVo getUserWithItemsByMaBatis (@PathVariable String account) {
        UserParam userParam = UserParam.builder().account(account).build();
        return userMapper.selectUserWithItems(userParam);
    }

    @Transactional
    @PutMapping("/jpa/users/{account}/update-some-items")
    public UserVo updateUserWithItemsByJpa (@PathVariable String account,
                                                  @RequestParam Integer quantity) {
        UserEntity userEntity = userRepository.findOne(Example.of(UserEntity.builder().account(account).build())).get();
        List<UserItemEntity> userItemEntities = userItemRepository.findAll(Example.of(UserItemEntity.builder().user(userEntity).build()));

        userItemEntities.get(0).updateQuantity(quantity); // Update quantity
        List<ItemVo> items = userItemEntities.stream()
                .map(e -> ItemVo.of(e)).toList();

        UserVo userVo = UserVo.builder().id(userEntity.getId()).account(userEntity.getAccount()).build();
        userVo.setItems(items);

        return userVo;
    }

    @Transactional
    @PutMapping("/mybatis/users/{account}/update-some-items")
    public UserVo updateUserWithItemsByBabatis (@PathVariable String account,
                                         @RequestParam Integer quantity) {
        UserParam userParam = UserParam.builder().account(account).build();
        UserVo user = userMapper.selectUserWithItems(userParam);

        ItemVo item = user.getItems().get(0);
//        log.info("{} / {} / {}", item.getId(), user.getId(), quantity);
        UserItemParam itemParam = new UserItemParam(item.getId(), user.getId(), quantity);

        itemMapper.updateUserItemQuantity(itemParam);
        user = userMapper.selectUserWithItems(userParam);

        return user;
    }
}
