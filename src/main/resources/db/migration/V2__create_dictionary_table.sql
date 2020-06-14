create table public.dictionary
(
	id bigint not null
		constraint dictionary_pkey
			primary key,
	language varchar(255)
);

create sequence public.dictionary_seq;

