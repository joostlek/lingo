drop sequence public.result_seq;

ALTER TABLE public.result
    DROP CONSTRAINT result_pkey;

ALTER TABLE public.result
    DROP COLUMN id;

ALTER TABLE public.result
    ADD PRIMARY KEY (position, turn_id);

