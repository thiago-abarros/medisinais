CREATE TABLE IF NOT EXISTS public.profissional
(
    id_profissional bigint NOT NULL,
    cpf character varying(11) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    especialidade character varying(255) COLLATE pg_catalog."default" NOT NULL,
    foto_usuario bytea,
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    slug character varying(255) COLLATE pg_catalog."default",
    telefone character varying(11) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT profissional_pkey PRIMARY KEY (id_profissional),
    CONSTRAINT uk_6guuncqkx1katspvyj4cfsh1l UNIQUE (email),
    CONSTRAINT uk_bxxjila9qnem55afsisc4865x UNIQUE (cpf),
    CONSTRAINT uk_dlowl4vk8oqbnbs5qin8ff3y9 UNIQUE (slug),
    CONSTRAINT uk_liqar0rls4uq89490pr2yv9o1 UNIQUE (telefone),
    CONSTRAINT profissional_especialidade_check CHECK (especialidade::text = ANY (ARRAY['MEDICO'::character varying, 'PSICOLOGO'::character varying, 'NUTRICIONISTA'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS public.endereco
(
    id_endereco bigint NOT NULL,
    bairro character varying(255) COLLATE pg_catalog."default" NOT NULL,
    cep integer NOT NULL,
    cidade character varying(40) COLLATE pg_catalog."default" NOT NULL,
    rua character varying(255) COLLATE pg_catalog."default" NOT NULL,
    uf character varying(2) COLLATE pg_catalog."default" NOT NULL,
    profissional_id_profissional bigint,
    CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco),
    CONSTRAINT fknxd94kr2j9k3dwu4eh5ha90v1 FOREIGN KEY (profissional_id_profissional)
        REFERENCES public.profissional (id_profissional) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT endereco_uf_check CHECK (uf::text = ANY (ARRAY['AC'::character varying, 'AL'::character varying, 'AP'::character varying, 'AM'::character varying, 'BA'::character varying, 'CE'::character varying, 'DF'::character varying, 'ES'::character varying, 'GO'::character varying, 'MA'::character varying, 'MT'::character varying, 'MS'::character varying, 'MG'::character varying, 'PA'::character varying, 'PB'::character varying, 'PR'::character varying, 'PE'::character varying, 'PI'::character varying, 'RJ'::character varying, 'RN'::character varying, 'RS'::character varying, 'RO'::character varying, 'RR'::character varying, 'SC'::character varying, 'SP'::character varying, 'SE'::character varying, 'TO'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS public.plano_saude
(
    id_plano_saude bigint NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT plano_saude_pkey PRIMARY KEY (id_plano_saude),
    CONSTRAINT plano_saude_nome_check CHECK (nome::text = ANY (ARRAY['BRADESCO'::character varying, 'UNIMED'::character varying, 'AMIL'::character varying, 'SULAMERICA'::character varying, 'HAPVIDA'::character varying]::text[]))
);

CREATE TABLE IF NOT EXISTS public.profissional_planos_aceitos
(
    profissionais_id_profissional bigint NOT NULL,
    planos_aceitos_id_plano_saude bigint NOT NULL,
    CONSTRAINT fkntce0al144j2u7xpwbpyfii8p FOREIGN KEY (planos_aceitos_id_plano_saude)
        REFERENCES public.plano_saude (id_plano_saude) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkppockid22opoqhci6jmmisf1b FOREIGN KEY (profissionais_id_profissional)
        REFERENCES public.profissional (id_profissional) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);