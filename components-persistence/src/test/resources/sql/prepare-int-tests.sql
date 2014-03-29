drop database if exists testDataBase;
create database testDataBase;

drop table if exists `testDataBase`.`test_entity`;
create table `testDataBase`.`test_entity` (
  id varchar(50) not null unique,
  myAttribute text,
  version int,
  PRIMARY KEY (id)
);