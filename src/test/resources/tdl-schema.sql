drop table if exists `tdTask` CASCADE ;
drop table if exists `tdList` CASCADE ;

create table if not exists tdList (id bigint PRIMARY KEY AUTO_INCREMENT, `name` varchar(25) not null);
create table if not exists tdTask (id bigint PRIMARY KEY AUTO_INCREMENT, todo varchar(40) not null);
