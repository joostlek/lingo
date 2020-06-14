create table public.turn
(
    id bigint not null
        constraint turn_pkey
            primary key,
    count integer not null,
    ended_at timestamp,
    started_at timestamp,
    round_id bigint
        constraint fk_turn_round
            references public.round
);

create sequence public.turn_seq;
