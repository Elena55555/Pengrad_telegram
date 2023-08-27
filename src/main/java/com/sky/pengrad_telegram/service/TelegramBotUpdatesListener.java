package com.sky.pengrad_telegram.service;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;
import com.sky.pengrad_telegram.entity.NotificationTask2;
import com.sky.pengrad_telegram.repository.NotificationTaskRepository2;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service

public class TelegramBotUpdatesListener  implements UpdatesListener {
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener2.class);

    @Autowired
    private  TelegramBot telegramBot;
    @Autowired
    private NotificationTaskRepository2 notificationTaskRepository;
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Process update: {}", update);
            String messageText = update.message().text();
            Long chatId = update.message().chat().id();
            if (update.message().text() != null && messageText.equals("/start")) {

                sendMessage(chatId, "Привет! Введи по порядку время и название задачи в формате: 01.01.2022 20:00 Сделать домашнюю работу");
            } else {
                splitMessage(chatId, messageText);
            }

        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    public void splitMessage(Long chat, String message) {
        Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
        Matcher matcher = pattern.matcher(message);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Неверный формат введенной задачи");
        }

        String dateTime = matcher.group(1);
        String reminderText = matcher.group(3);

        LocalDateTime reminderClock = LocalDateTime.parse(dateTime , DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        NotificationTask2 task = new NotificationTask2();

        task.setChat(chat);
        task.setMessage(reminderText);
        task.setClock(reminderClock);


        notificationTaskRepository.save(task);
        sendMessage(chat, "Задача добавлена");
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotifications() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<NotificationTask2> notificationTaskList = notificationTaskRepository.findByDateTime(now);
        notificationTaskList.forEach((notificationTask -> {
            Long chatId = notificationTask.getChat();
            String message = notificationTask.getMessage();
            sendMessage(chatId, message);
        }));
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(message);
        logger.info("Response: {}", response.isOk());
        logger.info("Error code: {}", response.errorCode());
    }

}
