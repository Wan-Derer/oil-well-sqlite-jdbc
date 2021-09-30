create table well
(
	id integer not null
		constraint well_pk
			primary key autoincrement,
	name varchar(32) not null
);

create unique index well_id_uindex
	on well (id);

create unique index well_name_uindex
	on well (name);


create table equipment
(
	id integer not null
		constraint equipment_pk
			primary key autoincrement,
	name varchar(32) not null,
	well_id integer
		constraint equipment_well_id_fk
			references well
				on update cascade on delete cascade
);

create unique index equipment_id_uindex
	on equipment (id);

create unique index equipment_name_uindex
	on equipment (name);

