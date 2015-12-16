CREATE DATABASE RRHH
GO
USE RRHH
GO
IF OBJECT_ID('t_marcacion') IS NOT NULL DROP TABLE t_marcacion
GO
IF OBJECT_ID('v_marcacioN') IS NOT NULL DROP VIEW v_marcacion
GO
CREATE TABLE t_marcacion(
id INT, 
nombre VARCHAR(100),
marcacion VARCHAR(100)
)
GO
CREATE VIEW v_marcacion
AS 
SELECT id, nombre, marcacion
FROM t_marcacion
GO
BULK INSERT v_marcacion FROM 'C:\Users\DACO78\Desktop\Dacotrans.txt'
WITH
(
FIELDTERMINATOR = ',',
ROWTERMINATOR = '\n'
)
GO
SELECT * FROM t_marcacion
 
