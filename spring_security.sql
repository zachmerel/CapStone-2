create schema if not exists spring_security;
use spring_security;

create table if not exists users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create table if not exists authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username));
	create unique index ix_auth_username on authorities (username,authority
);

insert into users (username, password, enabled) values ('employee', '$2a$10$Q2GI9WKD8T24G9cn8e7/YeAOfU9gZO1izoteqxOSGvdFGyAVig7s.', true);
insert into users (username, password, enabled) values ('teamLead', '$2a$10$lPjRlawCRuWsvpHYjorBW.rMFfzUqNsrrf1Sz/9Usd0ngzn.LChXG', true);
insert into users (username, password, enabled) values ('manager', '$2a$10$z24ZQ/UfPfJJGs6f062Gu.mCsi.jYXCP3iQJUKs797pqtj9PKRi8K', true);
insert into users (username, password, enabled) values ('admin', '$$2a$10$i5si91RjP/8kM4AEaxmRp.dfp5X4mJ1YKKWWg/lp4JfxOxDbpp3gu', true);

insert into authorities (username, authority) values ('employee', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('teamLead', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('teamLead', 'ROLE_TEAMLEAD');
insert into authorities (username, authority) values ('manager', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('manager', 'ROLE_TEAMLEAD');
insert into authorities (username, authority) values ('manager', 'ROLE_MANAGER');
insert into authorities (username, authority) values ('admin', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('admin', 'ROLE_TEAMLEAD');
insert into authorities (username, authority) values ('admin', 'ROLE_MANAGER');
insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');