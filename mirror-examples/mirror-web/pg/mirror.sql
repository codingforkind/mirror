-- project
CREATE SEQUENCE "public"."seq_project_id"
INCREMENT 1
START 1000000001
CACHE 1;
ALTER SEQUENCE "public"."seq_project_id" OWNER TO "mirror";

CREATE TABLE "project" (
	"prj_id" VARCHAR (30) DEFAULT ('PRJ' :: TEXT || nextval('seq_project_id' :: regclass)) NOT NULL,
	"name" VARCHAR (100) NOT NULL,
	"access_code" VARCHAR (100) NOT NULL,
	"user_id" VARCHAR (30) NOT NULL,
	"achv_id" VARCHAR (30),
	"graph_id" VARCHAR (30),
	CONSTRAINT "pk_project__prj_id" PRIMARY KEY ("prj_id")
) WITHOUT OIDS;

COMMENT ON COLUMN "project"."prj_id" IS 'project id';
COMMENT ON COLUMN "project"."name" IS 'project name';
COMMENT ON COLUMN "project"."access_code" IS 'access code for this project';
COMMENT ON COLUMN "project"."user_id" IS 'user info related';
COMMENT ON COLUMN "project"."achv_id" IS 'project''s source code';
COMMENT ON COLUMN "project"."graph_id" IS 'graph info related';


-- archive
CREATE SEQUENCE "public"."seq_archive_id"
INCREMENT 1
START 1000000001
CACHE 1;
ALTER SEQUENCE "public"."seq_archive_id" OWNER TO "mirror";

CREATE TABLE "archive" (
	"achv_id" VARCHAR (30) DEFAULT ('ACHV' :: TEXT || nextval('seq_archive_id' :: regclass)) NOT NULL,
	"type" CHAR (10) NOT NULL,
	"url" VARCHAR (300) NOT NULL,
	"dir" VARCHAR (300),
	CONSTRAINT "pk_archive__achv_id" PRIMARY KEY ("achv_id")
) WITHOUT OIDS;

COMMENT ON COLUMN "archive"."achv_id" IS 'archive id';
COMMENT ON COLUMN "archive"."type" IS 'archive zip type ';
COMMENT ON COLUMN "archive"."url" IS 'archive zip file location';
COMMENT ON COLUMN "archive"."dir" IS 'unziped archive location';


-- graph
CREATE SEQUENCE "public"."seq_graph_id"
INCREMENT 1
START 1000000001
CACHE 1;
ALTER SEQUENCE "public"."seq_graph_id" OWNER TO "mirror";

CREATE TABLE "graph" (
	"graph_id" VARCHAR (30) NOT NULL,
	"url" VARCHAR (300) NOT NULL,
	"username" VARCHAR (100) NOT NULL,
	"password" VARCHAR (100) NOT NULL,
	"host" VARCHAR (20) NOT NULL,
	"port" int2 NOT NULL,
	CONSTRAINT "pk_graph__graph_id" PRIMARY KEY ("graph_id")
) WITHOUT OIDS;

COMMENT ON COLUMN "graph"."graph_id" IS 'graph id';
COMMENT ON COLUMN "graph"."url" IS 'graph database linking url';


-- "user"
CREATE SEQUENCE "public"."seq_user_id"
INCREMENT 1
START 1000000001
CACHE 1;
ALTER SEQUENCE "public"."seq_user_id" OWNER TO "mirror";

CREATE TABLE "user" (
	"user_id" VARCHAR (30) DEFAULT('USR' :: TEXT || nextval('seq_user_id' :: regclass)) NOT NULL,
	"name" VARCHAR (100),
	CONSTRAINT "pk_user__user_id" PRIMARY KEY ("user_id")
) WITHOUT OIDS;


-- user_prj_rel
CREATE SEQUENCE "public"."seq_user_prj_rel_id"
INCREMENT 1
START 1000000001
CACHE 1;
ALTER SEQUENCE "public"."seq_user_prj_rel_id" OWNER TO "mirror";

CREATE TABLE "user_prj_rel" (
	"id" VARCHAR (30) DEFAULT(nextval('seq_user_prj_rel_id' :: regclass)) NOT NULL,
	"user_id" VARCHAR (30) NOT NULL,
	"prj_id" VARCHAR (30) NOT NULL,
	CONSTRAINT "pk_user_prj_rel__id" PRIMARY KEY ("id")
) WITHOUT OIDS;


-- pk & fk
ALTER TABLE "user_prj_rel" ADD CONSTRAINT "fk_user_prj_rel_user_id__user_user_id" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "user_prj_rel" ADD CONSTRAINT "fk_user_prj_rel_prj_id__project_prj_id" FOREIGN KEY ("prj_id") REFERENCES "project" ("prj_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "project" ADD CONSTRAINT "fk_project_user_id__user_user_id" FOREIGN KEY ("user_id") REFERENCES "user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "project" ADD CONSTRAINT "fk_project_achv_id__archive_achv_id" FOREIGN KEY ("achv_id") REFERENCES "archive" ("achv_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "project" ADD CONSTRAINT "fk_project_graph_id__graph_graph_id" FOREIGN KEY ("graph_id") REFERENCES "graph" ("graph_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
