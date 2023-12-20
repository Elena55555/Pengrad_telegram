package com.sky.pengrad_telegram;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.sky.pengrad_telegram.entity.NotificationTask;
import com.sky.pengrad_telegram.repository.NotificationTaskRepository;
import com.sky.pengrad_telegram.service.TelegramBotUpdatesListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.sky.pengrad_telegram.constants.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class PengradTelegramApplicationTests {

	@Mock
	private NotificationTaskRepository repository;

	@Mock
	private TelegramBotUpdatesListener telegramBotUpdatesListener;



	@Test
	void createNotificationTaskTest() {
		NotificationTask task1 = new NotificationTask();
		task1.setId(1L);
		task1.setChat(1L);
		LocalDateTime dateTime = LocalDateTime.parse("2023-08-24 21:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		task1.setClock(dateTime);
		task1.setMessage("Текст задания");

 		Mockito.doReturn(task1).when(repository).save(task1);

		assertEquals(task1, repository.save(task1));


	}


	@Test
	void findByDateTime() {
		LocalDateTime dateTime = LocalDateTime.parse("2023-08-24 21:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		when(repository.findByDateTime(dateTime))
				.thenReturn(TASK_LIST);

		assertThat(repository.findByDateTime(dateTime))
				.hasSize(4)
				.containsExactlyInAnyOrder(TASK_1, TASK_2, TASK_3, TASK_4);
	}
}