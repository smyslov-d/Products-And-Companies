/*
 Navicat Premium Data Transfer

 Source Server         : vit3
 Source Server Type    : PostgreSQL
 Source Server Version : 130002
 Source Host           : vit3:5432
 Source Catalog        : product_y
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130002
 File Encoding         : 65001

 Date: 09/10/2021 18:49:34
*/


-- ----------------------------
-- Sequence structure for company_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."company_id_seq";
CREATE SEQUENCE "public"."company_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for good_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."good_id_seq";
CREATE SEQUENCE "public"."good_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS "public"."company";
CREATE TABLE "public"."company" (
  "id" int4 NOT NULL DEFAULT nextval('company_id_seq'::regclass),
  "name" text COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO "public"."company" VALUES (5, 'testComp1');
INSERT INTO "public"."company" VALUES (6, 'testComp2');
INSERT INTO "public"."company" VALUES (7, 'testComp3');
INSERT INTO "public"."company" VALUES (8, 'testComp4');
INSERT INTO "public"."company" VALUES (9, 'testComp5');
INSERT INTO "public"."company" VALUES (10, 'testComp6');
INSERT INTO "public"."company" VALUES (11, 'testComp7');
INSERT INTO "public"."company" VALUES (12, 'testComp8');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS "public"."goods";
CREATE TABLE "public"."goods" (
  "id" int4 NOT NULL DEFAULT nextval('good_id_seq'::regclass),
  "name" text COLLATE "pg_catalog"."default" NOT NULL,
  "idcomp" int4 NOT NULL,
  "price" numeric(10,2)
)
;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO "public"."goods" VALUES (25, 'testGood1', 12, 10.00);
INSERT INTO "public"."goods" VALUES (27, 'aefsdf', 12, 100.00);
INSERT INTO "public"."goods" VALUES (26, 'testing', 5, 10000.00);
INSERT INTO "public"."goods" VALUES (24, 'qwerty213', 5, 100.00);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."company_id_seq"', 18, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."good_id_seq"', 28, true);

-- ----------------------------
-- Primary Key structure for table company
-- ----------------------------
ALTER TABLE "public"."company" ADD CONSTRAINT "company_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table goods
-- ----------------------------
ALTER TABLE "public"."goods" ADD CONSTRAINT "goods_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table goods
-- ----------------------------
ALTER TABLE "public"."goods" ADD CONSTRAINT "fkbdm69jfuch7fywm2r3ccfq3v4" FOREIGN KEY ("idcomp") REFERENCES "public"."company" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
