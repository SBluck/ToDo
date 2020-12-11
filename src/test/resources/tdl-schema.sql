drop table if exists `list` CASCADE ;
drop table if exists `task` CASCADE ;

create table if not exists `list` (id bigint PRIMARY KEY AUTO_INCREMENT, `name` varchar(25) not null);
create table if not exists `task` (id bigint PRIMARY KEY AUTO_INCREMENT, list_id bigint not null, `name` varchar(40) not null);
