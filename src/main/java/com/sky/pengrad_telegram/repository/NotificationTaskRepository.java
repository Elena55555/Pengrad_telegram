package com.sky.pengrad_telegram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sky.pengrad_telegram.entity.NotificationTask2;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
@Repository
public interface NotificationTaskRepository  extends JpaRepository <NotificationTask2, Long> {


    @Query(value = "SELECT * FROM notification_task n WHERE n.clock = ?1", nativeQuery = true)
    List <NotificationTask2> findByDateTime(final LocalDateTime date_and_time);
}
