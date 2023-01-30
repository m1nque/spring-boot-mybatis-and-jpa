package io.m1nque.mybatis.demo.repository;

import io.m1nque.mybatis.demo.model.entity.UserItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItemEntity, Integer> {
}