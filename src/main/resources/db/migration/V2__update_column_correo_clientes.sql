-- set a fake correo address
UPDATE clientes SET correo = concat( lower(nombre), lower(substr(paterno,1,3)),"@gmail.com" );
-- update the field correo to NOT NULL
ALTER TABLE clientes MODIFY correo varchar(30) NOT NULL;