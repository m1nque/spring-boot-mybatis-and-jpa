package io.m1nque.mybatis.demo.repository;

import io.m1nque.mybatis.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * naming rule
 *  -
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByAccountContains(String account);
}
