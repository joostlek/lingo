create table public.word
(
    id bigint not null
        constraint word_pkey
            primary key,
    word varchar(255),
    dictionary_id bigint not null
        constraint fk_word_dictionary
            references public.dictionary
);

create sequence public.word_seq;


