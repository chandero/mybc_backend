# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table billing (
  bill_id                       bigint auto_increment not null,
  bill_nombre                   varchar(255),
  bill_descripcion              varchar(255),
  bill_estado                   tinyint(1) default 0,
  bill_ciclodesde               datetime(6),
  bill_ciclohasta               datetime(6),
  bill_validezdesde             datetime(6),
  bill_validezhasta             datetime(6),
  bill_diacorte                 integer,
  bill_horainicial              varchar(255),
  bill_horafinal                varchar(255),
  constraint pk_billing primary key (bill_id)
);

create table centro_costo (
  ceco_id                       bigint auto_increment not null,
  ceco_nombre                   varchar(255),
  ceco_codigo                   varchar(255),
  ceco_extensiones              varchar(255),
  constraint pk_centro_costo primary key (ceco_id)
);

create table conferencia (
  conf_id                       bigint auto_increment not null,
  conf_descripcion              varchar(255),
  conf_pinusuario               varchar(255),
  conf_pinadmin                 varchar(255),
  conf_esperarlider             tinyint(1) default 0,
  conf_modosilencioso           tinyint(1) default 0,
  conf_conteousuario            tinyint(1) default 0,
  conf_usuarionotificar         tinyint(1) default 0,
  conf_menu                     tinyint(1) default 0,
  conf_grabar                   tinyint(1) default 0,
  extension_exte_id             bigint,
  conf_numero                   varchar(255),
  conf_nombre                   varchar(255),
  constraint pk_conferencia primary key (conf_id)
);

create table employee (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_employee primary key (id)
);

create table extension (
  exte_id                       bigint auto_increment not null,
  prot_id                       bigint,
  exte_numero                   varchar(255),
  exte_esagente                 tinyint(1) default 0,
  exte_directa                  tinyint(1) default 0,
  exte_listar                   tinyint(1) default 0,
  exte_privada                  tinyint(1) default 0,
  exte_alias                    varchar(255),
  exte_claveweb                 varchar(255),
  exte_clave                    varchar(255),
  exte_descripcion              varchar(255),
  exte_contexto                 varchar(255),
  exte_estado                   tinyint(1) default 0,
  constraint pk_extension primary key (exte_id)
);

create table grabacion (
  grab_id                       bigint auto_increment not null,
  grab_nombre                   varchar(255),
  grab_descripcion              varchar(255),
  constraint pk_grabacion primary key (grab_id)
);

create table moh (
  moh_id                        bigint auto_increment not null,
  moh_nombre                    varchar(255),
  moh_archivo                   varchar(255),
  constraint pk_moh primary key (moh_id)
);

create table protocolo (
  prot_id                       bigint auto_increment not null,
  prot_nombre                   varchar(255),
  constraint pk_protocolo primary key (prot_id)
);

create table reglas_extension (
  reex_id                       bigint auto_increment not null,
  reex_paramentro               varchar(255),
  reex_valor                    varchar(255),
  reex_bandera                  integer,
  exte_id                       bigint,
  constraint pk_reglas_extension primary key (reex_id)
);

create table sigueme (
  sigu_id                       bigint auto_increment not null,
  extension_exte_id             bigint,
  moh_moh_id                    bigint,
  sigu_confllamada              tinyint(1) default 0,
  sigu_tiempotimbre             integer,
  sigu_lista                    varchar(255),
  sigu_nombreprefijo            varchar(255),
  sigu_activo                   tinyint(1) default 0,
  sigu_destino                  varchar(255),
  sigu_anunciollamante          tinyint(1) default 0,
  sigu_extlist                  varchar(255),
  constraint pk_sigueme primary key (sigu_id)
);

create table tipo_destino (
  tide_id                       bigint auto_increment not null,
  tide_descripcion              varchar(255),
  tide_clase                    varchar(255),
  constraint pk_tipo_destino primary key (tide_id)
);

alter table conferencia add constraint fk_conferencia_extension_exte_id foreign key (extension_exte_id) references extension (exte_id) on delete restrict on update restrict;
create index ix_conferencia_extension_exte_id on conferencia (extension_exte_id);

alter table extension add constraint fk_extension_prot_id foreign key (prot_id) references protocolo (prot_id) on delete restrict on update restrict;
create index ix_extension_prot_id on extension (prot_id);

alter table reglas_extension add constraint fk_reglas_extension_exte_id foreign key (exte_id) references extension (exte_id) on delete restrict on update restrict;
create index ix_reglas_extension_exte_id on reglas_extension (exte_id);

alter table sigueme add constraint fk_sigueme_extension_exte_id foreign key (extension_exte_id) references extension (exte_id) on delete restrict on update restrict;
create index ix_sigueme_extension_exte_id on sigueme (extension_exte_id);

alter table sigueme add constraint fk_sigueme_moh_moh_id foreign key (moh_moh_id) references moh (moh_id) on delete restrict on update restrict;
create index ix_sigueme_moh_moh_id on sigueme (moh_moh_id);


# --- !Downs

alter table conferencia drop foreign key fk_conferencia_extension_exte_id;
drop index ix_conferencia_extension_exte_id on conferencia;

alter table extension drop foreign key fk_extension_prot_id;
drop index ix_extension_prot_id on extension;

alter table reglas_extension drop foreign key fk_reglas_extension_exte_id;
drop index ix_reglas_extension_exte_id on reglas_extension;

alter table sigueme drop foreign key fk_sigueme_extension_exte_id;
drop index ix_sigueme_extension_exte_id on sigueme;

alter table sigueme drop foreign key fk_sigueme_moh_moh_id;
drop index ix_sigueme_moh_moh_id on sigueme;

drop table if exists billing;

drop table if exists centro_costo;

drop table if exists conferencia;

drop table if exists employee;

drop table if exists extension;

drop table if exists grabacion;

drop table if exists moh;

drop table if exists protocolo;

drop table if exists reglas_extension;

drop table if exists sigueme;

drop table if exists tipo_destino;

