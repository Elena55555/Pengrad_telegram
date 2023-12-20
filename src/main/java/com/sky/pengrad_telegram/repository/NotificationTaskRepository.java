package com.sky.pengrad_telegram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sky.pengrad_telegram.entity.NotificationTask;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface NotificationTaskRepository  extends JpaRepository <NotificationTask, Long> {

    @Query(value = "SELECT * FROM notification_task n WHERE n.clock = ?1", nativeQuery = true)
    List <NotificationTask> findByDateTime(final LocalDateTime date_and_time);













}
