INSERT INTO usuarios ( username, password ) VALUES ( 'admin', '$2a$12$b6b/HHto0zMzYPz.oVHYEucU5AsASKcuXF/oQwT/CWXHtKx8iyDOG' );

INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'PAUL DEITEL', '1969-05-01' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'MARK ALLEN WEISS', '1960-08-11' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'PABLO AUGUSTO SZNAJDLEDER', '1960-08-11' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'MARTIN SILVA', '1984-02-14' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'GABRIEL GARCIA MARQUEZ', '1927-03-06' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'CARLOS RUIZ ZAFON', '1964-09-25' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'KEN FOLLET', '1949-06-05' );
INSERT INTO autores ( nombre, fecha_nacimiento ) VALUES ( 'GEORGE ORWELL', '1903-06-25' );

INSERT INTO libros ( id, titulo, autor_id, numero_paginas, isbn, url_portada ) VALUES ( 'e0d1a5e8-12f7-4f6c-8b6c-f8123e7fd6cb', 'COMO PROGRAMAR EN JAVA', 1, 586, '9780130119650', 'https://example.com/java.jpg' );
INSERT INTO libros ( id, titulo, autor_id, numero_paginas, isbn, url_portada ) VALUES ( 'a91b2e18-4e2d-401a-91d9-2dbf0f7fa47e', 'ESTRUCTURAS DE DATOS EN JAVA', 2, 420, '9788415552239', 'https://example.com/estructuras.jpg' );
INSERT INTO libros ( id, titulo, autor_id, numero_paginas, isbn, url_portada ) VALUES ( 'b8c63d58-f104-4c7e-a63c-8a2a33bdf17a', 'SISTEMAS OPERATIVOS', 4, 370, '9789873832031', 'https://example.com/sistemas.jpg' );
INSERT INTO libros ( id, titulo, autor_id, numero_paginas, isbn, url_portada ) VALUES ( 'cbf5413e-84b7-4637-8b85-f2a9f8446d80', 'SISTEMAS DE REDES DE COMPUTADORAS', 1, 515, '', 'https://example.com/redes.jpg' );
INSERT INTO libros ( id, titulo, autor_id, numero_paginas, isbn, url_portada ) VALUES ( 'f37c9de4-8c1f-4db1-bf91-0cf6b12f5d93', 'ALGORITMOS AVANZADOS', 3, 630, '', 'https://example.com/algoritmos.jpg' );
