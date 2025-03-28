-- Exemplo de remoção de tabelas se já existirem
DROP TABLE IF EXISTS "PUBLIC"."TB_MANAGER";
DROP TABLE IF EXISTS "PUBLIC"."TB_PATIENT";
DROP TABLE IF EXISTS "PUBLIC"."TB_PROFESSIONAL";
DROP TABLE IF EXISTS "PUBLIC"."TB_REPORT";
DROP TABLE IF EXISTS "PUBLIC"."TB_SCHEDULING";

-- Criação das tabelas
CREATE MEMORY TABLE "PUBLIC"."TB_MANAGER"(
    "PERMISSION" BOOLEAN NOT NULL,
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CONTACT" CHARACTER VARYING(255),
    "EMAIL" CHARACTER VARYING(255),
    "NAME" CHARACTER VARYING(255)
);   
ALTER TABLE "PUBLIC"."TB_MANAGER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");   

CREATE MEMORY TABLE "PUBLIC"."TB_PATIENT"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 13) NOT NULL,
    "ADDRESS" CHARACTER VARYING(255),
    "BIRTH_DAY" CHARACTER VARYING(255),
    "CONTACT" CHARACTER VARYING(255),
    "NAME" CHARACTER VARYING(255)
);            
ALTER TABLE "PUBLIC"."TB_PATIENT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_E" PRIMARY KEY("ID");   

CREATE MEMORY TABLE "PUBLIC"."TB_PROFESSIONAL"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 6) NOT NULL,
    "CONTACT" CHARACTER VARYING(255),
    "NAME" CHARACTER VARYING(255),
    "SPECIALTY" CHARACTER VARYING(255)
);               
ALTER TABLE "PUBLIC"."TB_PROFESSIONAL" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_F" PRIMARY KEY("ID"); 

CREATE MEMORY TABLE "PUBLIC"."TB_REPORT"(
    "DATE_REPORT" TIMESTAMP(6),
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 3) NOT NULL,
    "PATIENT_ID" BIGINT,
    "PROFESSIONAL_ID" BIGINT,
    "DESCRIPTION" CHARACTER VARYING,
    "REPORT_TYPE" CHARACTER VARYING(255)
);              
ALTER TABLE "PUBLIC"."TB_REPORT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");    

CREATE MEMORY TABLE "PUBLIC"."TB_SCHEDULING"(
    "IS_CANCEL" BOOLEAN NOT NULL,
    "IS_CONFIRMED" BOOLEAN NOT NULL,
    "IS_PRESENT" BOOLEAN NOT NULL,
    "DATE_HOUR" TIMESTAMP(6),
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 8) NOT NULL,
    "PATIENT_ID" BIGINT,
    "PROFESSIONAL_ID" BIGINT
);
ALTER TABLE "PUBLIC"."TB_SCHEDULING" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_7" PRIMARY KEY("ID");

-- Relacionamentos de chave estrangeira
ALTER TABLE "PUBLIC"."TB_SCHEDULING" ADD CONSTRAINT "PUBLIC"."FK8GBNA3F1MOWEAQ6WFQXE18QDJ" FOREIGN KEY("PROFESSIONAL_ID") REFERENCES "PUBLIC"."TB_PROFESSIONAL"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."TB_REPORT" ADD CONSTRAINT "PUBLIC"."FKQME7QFCSASHAV75LPNF64IQIS" FOREIGN KEY("PATIENT_ID") REFERENCES "PUBLIC"."TB_PATIENT"("ID") NOCHECK;              
ALTER TABLE "PUBLIC"."TB_SCHEDULING" ADD CONSTRAINT "PUBLIC"."FKIRN1OV359ON433HJNDE9QO31J" FOREIGN KEY("PATIENT_ID") REFERENCES "PUBLIC"."TB_PATIENT"("ID") NOCHECK;          
ALTER TABLE "PUBLIC"."TB_REPORT" ADD CONSTRAINT "PUBLIC"."FKCRXU8FPOGCMPSKUBC2BWWWVQ8" FOREIGN KEY("PROFESSIONAL_ID") REFERENCES "PUBLIC"."TB_PROFESSIONAL"("ID") NOCHECK;

-- Insira os dados diretamente no schema.sql ou em um arquivo separado de import.sql
