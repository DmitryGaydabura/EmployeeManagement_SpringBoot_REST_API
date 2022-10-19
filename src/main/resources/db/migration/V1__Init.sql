create table public.users (
                              id integer primary key not null default nextval('table_name_id_seq'::regclass),
                              name character varying,
                              email character varying,
                              country character varying,
                              is_full boolean,
                              is_updated boolean default false,
                              password character varying,
                              passport_id integer,
                              employee_id integer,
                              foreign key (employee_id) references public.passport (passport_id)
                                  match simple on update no action on delete no action,
                              foreign key (passport_id) references public.passport (passport_id)
                                  match simple on update no action on delete no action
);

create table public.task (
                             task_id integer primary key not null default nextval('task_task_id_seq'::regclass),
                             hours_to_complete timestamp without time zone,
                             task_description character varying(255),
                             employee_id integer,
                             foreign key (employee_id) references public.users (id)
                                 match simple on update no action on delete no action
);

create table public.passport (
                                 passport_id integer primary key not null default nextval('passport_id_seq'::regclass),
                                 date_of_expiry timestamp without time zone,
                                 serial_number integer
);

