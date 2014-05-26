# --- !Ups

create table evento (
  id                        integer not null,
  email_para_contato        varchar(255),
  estado                    varchar(2),
  descricao                 text,
  site                      varchar(255),
  twitter                   varchar(255),
  nome                      varchar(255),
  data_de_inicio            timestamp,
  data_de_fim               timestamp,
  constraint ck_evento_estado check (estado in ('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RR','RO','RJ','RN','RS','SC','SP','SE','TO')),
  constraint pk_evento primary key (id))
;

create sequence evento_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists evento;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists evento_seq;

