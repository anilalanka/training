# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  name                          varchar(255) not null,
  pwd                           varchar(255),
  constraint pk_user primary key (name)
);


# --- !Downs

drop table if exists user;

