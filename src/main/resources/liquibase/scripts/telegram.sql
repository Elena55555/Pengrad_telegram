--liquibase formatted sql
--changeset ekastalskaya:1

create table notification_task(
      id serial primary key,
      chat bigint not null,
      message VARCHAR(255) not null,
      clock TIMESTAMP not null
  )