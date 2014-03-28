create database testDataBase;

create table testDataBase.test_entity (
  id varchar(50),
  myAttribute text,
  version int
);

insert into testDataBase.test_entity values ('abcd-1234', 'testString', 0);
