-- MIGRATION TABLE roles
DROP TABLE IF EXISTS roles CASCADE;
DROP SEQUENCE IF EXISTS roles_role_id_seq;
CREATE SEQUENCE IF NOT EXISTS roles_role_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS roles
(
    role_id integer NOT NULL DEFAULT nextval('roles_role_id_seq'::regclass),
    role_name character varying(255) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (role_id),
    CONSTRAINT roles_role_name_unique UNIQUE (role_name)
);

-- MIGRATION TABLE profiles
DROP TABLE IF EXISTS profiles CASCADE;
DROP SEQUENCE IF EXISTS profiles_profile_id_seq;
CREATE SEQUENCE IF NOT EXISTS profiles_profile_id_seq
  start 1
  increment 1;

CREATE TABLE IF NOT EXISTS profiles
(
    profile_id integer NOT NULL DEFAULT nextval('profiles_profile_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    okta_id character varying(255) COLLATE pg_catalog."default",
    role_id integer NOT NULL,
    avatarUrl character varying(255) COLLATE pg_catalog."default" DEFAULT 'https://i.stack.imgur.com/frlIf.png'::character varying,
    pending character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT profiles_pkey PRIMARY KEY (profile_id),
    CONSTRAINT profiles_okta_id_unique UNIQUE (okta_id),
    CONSTRAINT profiles_role_id_foreign FOREIGN KEY (role_id)
        REFERENCES roles (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- MIGRATION TABLE profiles
DROP TABLE IF EXISTS conversations CASCADE;
DROP SEQUENCE IF EXISTS conversations_profile_id_seq;
CREATE SEQUENCE IF NOT EXISTS conversations_profile_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS conversations
(
    conversation_id integer NOT NULL DEFAULT nextval('conversations_profile_id_seq'::regclass),
    profile_id integer NOT NULL,
    CONSTRAINT conversations_pkey PRIMARY KEY (conversation_id),
    CONSTRAINT conversations_profile_id_foreign FOREIGN KEY (profile_id)
        REFERENCES profiles (profile_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- MIGRATION TABLE messages
DROP TABLE IF EXISTS messages CASCADE;
DROP SEQUENCE IF EXISTS messages_profile_id_seq;
CREATE SEQUENCE IF NOT EXISTS messages_profile_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS messages
(
    messages_id integer NOT NULL DEFAULT nextval('messages_profile_id_seq'::regclass),
    sent_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    read boolean DEFAULT false,
    message text COLLATE pg_catalog."default" NOT NULL,
    sent_by_profile_id integer NOT NULL,
    conversation_id integer NOT NULL,
    CONSTRAINT messages_pkey PRIMARY KEY (messages_id),
    CONSTRAINT messages_conversation_id_foreign FOREIGN KEY (conversation_id)
        REFERENCES conversations (conversation_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT messages_sent_by_profile_id_foreign FOREIGN KEY (sent_by_profile_id)
        REFERENCES profiles (profile_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

