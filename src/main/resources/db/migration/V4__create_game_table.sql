create table public.game
(
    id bigint not null
        constraint game_pkey
            primary key,
    created_at timestamp,
    word_length integer,
    dictionary_id bigint
        constraint fk_game_dictionary
            references public.dictionary
);

create sequence public.game_seq;
