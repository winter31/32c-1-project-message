create table users1 (
	id varchar(20),
	name varchar(20) not null unique,
	constraints pk_users1 primary key(id)
);

create table notes (
	id varchar(20) not null,
	title varchar(40) not null,
	content varchar(4000) default '',
	date_created timestamp default sysdate, 
	to_id varchar(20) not null, 
	read char(1) default 'f'
	);

create table log_in_out( 
	id varchar(20) constraint fk_log_in_out references users, 
	log_in timestamp default sysdate, 
	log_out timestamp default sysdate, 
	log char(1) default 'f'
	);


--insert a user
	

--insert a note


--insert a sending


--update sent_flg


--select all users


--select a user by id


--select a user by name


--select next note sequence


--select a note


--select notes from a user


--select notes to a user


--select notes not yet sent


