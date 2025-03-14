# WebProjectMsvcs-Back
Proyecto Microservicios con Consignatarios y Clientes

> Base Datos "evser" motor Postgresql

CREATE TABLE cliente (

clienteid serial,

clienteactivo bool NOT NULL,

clientenombre varchar NOT NULL,

clientefechacreacion timestamptz(3) NOT NULL DEFAULT now(),

clientefechamodificacion timestamptz(3) NOT NULL DEFAULT now(),

CONSTRAINT cliente_pkey PRIMARY KEY (clienteid)

);

 

CREATE TABLE consignatario (

clienteid int4 NOT NULL,

consignatarioid serial,

consignatarioactivo bool NOT NULL,

consignatarionombre varchar NOT NULL,

consignatariofechacreacion timestamptz(3) NOT NULL DEFAULT now(),

consignatariofechamodificacion timestamptz(3) NOT NULL DEFAULT now(),

CONSTRAINT consignatario_pkey PRIMARY KEY (clienteid, consignatarioid),

constraint consignatario_clienteid_fk FOREIGN KEY (clienteid) REFERENCES cliente(clienteid)

);

CREATE OR REPLACE PROCEDURE public.manejar_cliente(
	IN p_clienteid integer,
	IN p_clienteactivo boolean,
	IN p_clientenombre character varying)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    -- SI EL ID ES 0, CREAMOS UN NUEVO CLIENTE
    IF P_CLIENTEID = 0 THEN
        INSERT INTO cliente (clienteactivo, clientenombre)
        VALUES (P_CLIENTEACTIVO, P_CLIENTENOMBRE);
    ELSE
        -- SI EL ID NO ES 0, ACTUALIZAMOS EL CLIENTE EXISTENTE
        UPDATE cliente
        SET clienteactivo = P_CLIENTEACTIVO,
            clientenombre = P_CLIENTENOMBRE,
            clientefechamodificacion = NOW()
        WHERE clienteid = P_CLIENTEID;

        -- COMPROBAR SI SE ACTUALIZÓ ALGUNA FILA
        IF NOT FOUND THEN
            RAISE EXCEPTION 'NO SE ENCONTRÓ EL CLIENTE CON EL ID PROPORCIONADO';
        END IF;
    END IF;
END;
$BODY$;

CREATE OR REPLACE PROCEDURE public.manejar_consignatario(
	IN p_clienteid integer,
	IN p_consignatarioid integer,
	IN p_consignatarioactivo boolean,
	IN p_consignatarionombre character varying)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
    v_consignatarioid INT;
BEGIN
    -- SI EL ID ES 0, CREAMOS UN NUEVO CONSIGNATARIO
    IF P_CONSIGNATARIOID = 0 THEN
        -- OBTENER EL MÁXIMO consignatarioid PARA EL clienteid DADO
        SELECT COALESCE(MAX(consignatarioid), 0) + 1
        INTO v_consignatarioid
        FROM consignatario
        WHERE clienteid = P_CLIENTEID;

        -- INSERTAR EL NUEVO CONSIGNATARIO
        INSERT INTO consignatario (clienteid, consignatarioid, consignatarioactivo, consignatarionombre)
        VALUES (P_CLIENTEID, v_consignatarioid, P_CONSIGNATARIOACTIVO, P_CONSIGNATARIONOMBRE);
    ELSE
        -- SI EL ID NO ES 0, ACTUALIZAMOS EL CONSIGNATARIO EXISTENTE
        UPDATE consignatario
        SET consignatarioactivo = P_CONSIGNATARIOACTIVO,
            consignatarionombre = P_CONSIGNATARIONOMBRE,
            consignatariofechamodificacion = NOW()
        WHERE clienteid = P_CLIENTEID AND consignatarioid = P_CONSIGNATARIOID;

        -- COMPROBAR SI SE ACTUALIZÓ ALGUNA FILA
        IF NOT FOUND THEN
            RAISE EXCEPTION 'NO SE ENCONTRÓ EL CONSIGNATARIO CON EL ID PROPORCIONADO';
        END IF;
    END IF;
END;
$BODY$;

CREATE OR REPLACE PROCEDURE public.consultar_consignatarios(
	INOUT resultado refcursor)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    -- Abrir el cursor con la consulta
    OPEN resultado FOR
    SELECT clienteid, consignatarioid, consignatarioactivo, consignatarionombre, consignatariofechacreacion, consignatariofechamodificacion
    FROM consignatario
    ORDER BY consignatariofechacreacion DESC
    LIMIT 5;
END;
$BODY$;


CREATE OR REPLACE PROCEDURE public.consultar_clientes(
	INOUT resultado refcursor)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
    -- Abrir el cursor con la consulta
    OPEN resultado FOR
    SELECT clienteid, clienteactivo, clientenombre, clientefechacreacion, clientefechamodificacion
    FROM cliente
    ORDER BY clientefechacreacion DESC
    LIMIT 5;
END;
$BODY$;



