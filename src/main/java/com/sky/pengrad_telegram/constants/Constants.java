package com.sky.pengrad_telegram.constants;
import com.sky.pengrad_telegram.entity.NotificationTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Constants {



        //создать LocalDateTime объект из строки,   используем статический LocalDateTime.parse() метод.
        // Он принимает строку и DateTimeFormatter параметр as.
        // DateTimeFormatter Используется для указания шаблона даты / времени.
        //String s = "20210223";
        //LocalDate.parse(s, DateTimeFormatter.ofPattern("uuuuMMdd"));
        //// java.time.format.DateTimeParseException
        //LocalDateTime.parse(s, DateTimeFormatter.ofPattern("uuuuMMdd"));

        public static final LocalDateTime TIME_TASK_1 = LocalDateTime.parse("2023-08-24 21:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        public static final LocalDateTime TIME_TASK_2 = LocalDateTime.parse("2023-08-24 21:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        public static final LocalDateTime TIME_TASK_3 = LocalDateTime.parse("2023-08-24 21:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        public static final LocalDateTime TIME_TASK_4 = LocalDateTime.parse("2023-08-24 21:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        public static final NotificationTask TASK_1 = new NotificationTask(1L, 1L, "Задание для первого пользователя", TIME_TASK_1);
        public static final NotificationTask TASK_2 = new NotificationTask(2L,  2L, "Задание для второго пользователя", TIME_TASK_2);
        public static final NotificationTask TASK_3 = new NotificationTask(3L,   3L, "Задание для третьего пользователя", TIME_TASK_3);
        public static final NotificationTask TASK_4 = new NotificationTask(4L,   4L, "Задание для третьего пользователя", TIME_TASK_4);

        public static final List<NotificationTask> TASK_LIST = List.of(
                TASK_1,
                TASK_2,
                TASK_3,
                TASK_4

        );

    }

