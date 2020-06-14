create table public.result
(
    id bigint not null
        constraint result_pkey
            primary key,
    character char not null,
    feedback integer,
    position integer not null,
    turn_id bigint
        constraint fk_result_turn
            references public.turn
);

create sequence public.result_seq;
