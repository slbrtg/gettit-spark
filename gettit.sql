--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE comments (
    id integer NOT NULL,
    comment character varying,
    postid integer,
    username character varying,
    "time" timestamp without time zone
);


ALTER TABLE comments OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE comments_id_seq OWNER TO "Guest";

--
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE comments_id_seq OWNED BY comments.id;


--
-- Name: posts; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE posts (
    id integer NOT NULL,
    title character varying,
    type character varying,
    content character varying,
    votes integer,
    subname character varying,
    "time" timestamp without time zone,
    username character varying,
    glyph character varying
);


ALTER TABLE posts OWNER TO "Guest";

--
-- Name: posts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE posts_id_seq OWNER TO "Guest";

--
-- Name: posts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE posts_id_seq OWNED BY posts.id;


--
-- Name: subs; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE subs (
    id integer NOT NULL,
    name character varying,
    modid integer,
    description character varying
);


ALTER TABLE subs OWNER TO "Guest";

--
-- Name: subs_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE subs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE subs_id_seq OWNER TO "Guest";

--
-- Name: subs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE subs_id_seq OWNED BY subs.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE users (
    id integer NOT NULL,
    username character varying,
    password character varying,
    subs text[]
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY posts ALTER COLUMN id SET DEFAULT nextval('posts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY subs ALTER COLUMN id SET DEFAULT nextval('subs_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY comments (id, comment, postid, username, "time") FROM stdin;
\.


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('comments_id_seq', 1, false);


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY posts (id, title, type, content, votes, subname, "time", username, glyph) FROM stdin;
1	ed	text	ededed	0	testSub	2017-07-26 10:07:00.573885	red	none
2	redred	text	redredredrefef	0	testSub	2017-07-26 10:09:52.415497	red	none
3	funny link	text	lol	0	testSub	2017-07-26 10:47:47.873594	red	none
4	Animals are better than people	text	https://www.theodysseyonline.com/why-animals-are-sometimes-better-then-humans	0	testSub	2017-07-26 12:25:49.448042	red	none
5	something about dogs	text	dogs r cool	0	testSub	2017-07-26 14:05:52.207814	red	none
6	cats are so fun	text	cats and fun	0	testSub	2017-07-26 14:07:26.564335	red	none
7	foxes	text	foxes	0	testSub	2017-07-26 14:08:29.85998	red	none
8	hi	text	hi	0	TestNumber2	2017-07-26 14:16:02.778969	red	none
9	my dog is so funny 	text	lol	0	animals	2017-07-26 14:27:20.953144	red	none
\.


--
-- Name: posts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('posts_id_seq', 9, true);


--
-- Data for Name: subs; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY subs (id, name, modid, description) FROM stdin;
3	testSub	10	test sub for tests
4	TestNumber2	10	Used for testing subs and posts
5	animals	10	a serious sub about animals. no pics, memes, or gifs.
\.


--
-- Name: subs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('subs_id_seq', 5, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, username, password, subs) FROM stdin;
10	red	red	\N
11	cyan	cyan	\N
12	yellow	red	\N
13	redered	yellow	\N
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 13, true);


--
-- Name: comments_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: posts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- Name: subs_name_key; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY subs
    ADD CONSTRAINT subs_name_key UNIQUE (name);


--
-- Name: subs_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY subs
    ADD CONSTRAINT subs_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_username_key; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

