
create table test_entity (
  id varchar(50) not null unique,
  myAttribute text,
  version int,
  PRIMARY KEY (id)
);

