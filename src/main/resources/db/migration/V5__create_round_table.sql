create table public.round
(
    id bigint not null
        constraint round_pkey
            primary key,
    count integer not null,
    ended_at timestamp,
    guessed boolean not null,
    started_at timestamp,
    answer_id bigint
        constraint fk_round_word
            references public.word,
    game_id bigint
        constraint fk_round_game
            references public.game
);

alter sequence public.round_seq owner to postgres;
