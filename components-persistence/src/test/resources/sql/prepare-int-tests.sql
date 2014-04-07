drop table if exists test_entity;
create table test_entity (
  id varchar(50) not null,
  myAttribute varchar(255),
  version int,
  PRIMARY KEY (id)
);
