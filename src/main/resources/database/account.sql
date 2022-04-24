create table account (
  ano number primary key,
  owner varchar(20) not null,
  balance number not null
);

insert into account (ano, owner, balance) values (1, '홍길동', 1000000);
insert into account (ano, owner, balance) values (2, '스프링', 0);
commit;