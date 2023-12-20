package com.sky.pengrad_telegram.service;

import com.pengrad.telegrambot.TelegramBot;
import com.sky.pengrad_telegram.entity.NotificationTask;
import com.sky.pengrad_telegram.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;

@Service
public class ScheduledClass {
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private NotificationTaskRepository repository;

    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Autowired
    public void init(TelegramBotUpdatesListener telegramBotUpdatesListener) {
        this.telegramBotUpdatesListener = telegramBotUpdatesListener;
    }

    @Scheduled(cron = "0 0/1 * * * *")

    public void sendAndDeleteNotifications() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        List<NotificationTask> notificationTaskList = this.repository.findByDateTime(now);
        notificationTaskList.forEach((notificationTask -> {
            Long chatId = notificationTask.getChat();
            String message = notificationTask.getMessage();
            telegramBotUpdatesListener.sendMessage(chatId, message);
            repository.deleteById(notificationTask.getId());

        }));
    }
}
















