drop table course if exists;
drop table author if exists;
create table if not exists course (id uuid primary key, title varchar(250) not null, description varchar(300) not null);
create table if not exists author (id uuid primary key, firstName VARCHAR(50), lastName VARCHAR(50), emailAddress VARCHAR(50));