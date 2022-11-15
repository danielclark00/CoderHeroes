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

-- MIGRATION TABLE programs
DROP TABLE IF EXISTS programs CASCADE;
DROP SEQUENCE IF EXISTS programs_program_id_seq;
CREATE SEQUENCE IF NOT EXISTS programs_program_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS programs
(
    program_id integer NOT NULL DEFAULT nextval('programs_program_id_seq'::regclass),
    program_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    program_description text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT programs_pkey PRIMARY KEY (program_id),
    CONSTRAINT programs_program_name_unique UNIQUE (program_name)
);

-- MIGRATION TABLE admins
DROP TABLE IF EXISTS admins CASCADE;
DROP SEQUENCE IF EXISTS admins_admin_id_seq;
CREATE SEQUENCE IF NOT EXISTS admins_admin_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS admins
(
    admin_id integer NOT NULL DEFAULT nextval('admins_admin_id_seq'::regclass),
    profile_id integer NOT NULL,
    CONSTRAINT admins_pkey PRIMARY KEY (admin_id),
    CONSTRAINT admins_profile_id_unique UNIQUE (profile_id),
    CONSTRAINT admins_profile_id_foreign FOREIGN KEY (profile_id)
        REFERENCES profiles (profile_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- MIGRATION TABLE instructors
DROP TABLE IF EXISTS instructors CASCADE;
DROP SEQUENCE IF EXISTS instructors_instructor_id_seq;
CREATE SEQUENCE IF NOT EXISTS instructors_instructor_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS instructors
(
    instructor_id integer NOT NULL DEFAULT nextval('instructors_instructor_id_seq'::regclass),
    instructor_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    rating integer NOT NULL,
    availability character varying(255) COLLATE pg_catalog."default",
    bio character varying(255) COLLATE pg_catalog."default" NOT NULL,
    profile_id integer NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL DEFAULT 'pending'::character varying,
    created_on date DEFAULT CURRENT_TIMESTAMP,
    approved_by integer,
    CONSTRAINT instructors_pkey PRIMARY KEY (instructor_id),
    CONSTRAINT instructors_profile_id_unique UNIQUE (profile_id),
    CONSTRAINT instructors_approved_by_foreign FOREIGN KEY (approved_by)
        REFERENCES admins (admin_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT instructors_profile_id_foreign FOREIGN KEY (profile_id)
        REFERENCES profiles (profile_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- MIGRATION TABLE instructors_program_types
DROP TABLE IF EXISTS instructors_program_types CASCADE;
DROP SEQUENCE IF EXISTS instructors_program_types_instructors_program_types_id_seq;
CREATE SEQUENCE IF NOT EXISTS instructors_program_types_instructors_program_types_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS instructors_program_types
(
    instructors_program_types_id integer NOT NULL DEFAULT nextval('instructors_program_types_instructors_program_types_id_seq'::regclass),
    instructor_id integer NOT NULL,
    program_id integer NOT NULL,
    CONSTRAINT instructors_program_types_pkey PRIMARY KEY (instructors_program_types_id),
    CONSTRAINT instructors_program_types_instructor_id_foreign FOREIGN KEY (instructor_id)
        REFERENCES instructors (instructor_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT instructors_program_types_program_id_foreign FOREIGN KEY (program_id)
        REFERENCES programs (program_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- MIGRATION TABLE courses
DROP TABLE IF EXISTS courses CASCADE;
DROP SEQUENCE IF EXISTS courses_course_id_seq;
CREATE SEQUENCE IF NOT EXISTS courses_course_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS courses
(
    course_id integer NOT NULL DEFAULT nextval('courses_course_id_seq'::regclass),
    course_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    course_description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_on date DEFAULT CURRENT_TIMESTAMP,
    days_of_week text[] COLLATE pg_catalog."default",
    max_size integer NOT NULL,
    enrolled_students integer NOT NULL,
    min_age integer NOT NULL,
    max_age integer NOT NULL,
    instructor_id integer NOT NULL,
    program_id integer NOT NULL,
    start_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    number_of_sessions integer NOT NULL,
    difficulty character varying(255) COLLATE pg_catalog."default" NOT NULL,
    session_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    syllabus_link character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT courses_pkey PRIMARY KEY (course_id),
    CONSTRAINT courses_instructor_id_foreign FOREIGN KEY (instructor_id)
        REFERENCES instructors (instructor_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT courses_program_id_foreign FOREIGN KEY (program_id)
        REFERENCES programs (program_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- MIGRATION TABLE newsfeed
DROP TABLE IF EXISTS newsfeed CASCADE;
DROP SEQUENCE IF EXISTS newsfeed_newsfeed_id_seq;
CREATE SEQUENCE IF NOT EXISTS newsfeed_newsfeed_id_seq
  start 1
  increment 1;
CREATE TABLE IF NOT EXISTS newsfeed
(
    newsfeed_id integer NOT NULL DEFAULT nextval('newsfeed_newsfeed_id_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    link character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    posted_at timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT newsfeed_pkey PRIMARY KEY (newsfeed_id)
);