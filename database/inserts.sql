/*INGRESOS A LA TABLA CLIENTE*/
use facturacion;
desc cliente;
/*
Cedula_C varchar(10)  PK NOT NULL
Fecha_C date
Nombre_C varchar(30) not null
Apellido_C varchar(30) not null
Direccion_C varchar(30) not null
Celular_C varchar(15)
Convencional_C varchar(15)
Email_C varchar(30)
*/
SELECT * FROM cliente;
INSERT INTO cliente VALUES ("0092314030", "2016-08-01", "Audra Tahis", "Ahtty Arteaga", "Guayaquil", "0918218081", "", ""); 
INSERT INTO cliente VALUES ("0998531363", "2016-08-01", "Isaac Josue", "Ballen Gavilanes", "Guayaquil", "0938997722", "", ""); 
INSERT INTO cliente VALUES ("0938997722", "2016-08-02", "Luigi", "Salvatore Basantes", "Guayaquil", "0907862021", "", "");
INSERT INTO cliente VALUES ("0971581949", "2016-08-01", "Karen", "Borbor", "Cuenca", "0908947555", "", "");
INSERT INTO cliente VALUES ("0961831371", "2016-08-10", "Juan Jose", "Crow", "Guayaquil", "", "", ""); 
INSERT INTO cliente VALUES ("0911801240", "2016-08-05", "Luis", "Cruz Intriago", "Ibarra", "0914225890", "042587965", "");
INSERT INTO cliente VALUES ("0925831528", "2016-08-05", "Jose Ernesto", "Garcia", "Cuenca", "0938190620", "042568715", "");
INSERT INTO cliente VALUES ("0967111292", "2016-08-02", "Jorge Luis", "Hugo Cruz", "Guayaquil", "0981978746", "046552287", ""); 
INSERT INTO cliente VALUES ("0936952283", "2016-08-02", "Leonardo", "Kuffo", "Guayaquil", "0973281156", "042587546", ""); 
INSERT INTO cliente VALUES ("0902009344", "2016-08-04", "Paolo", "Lara", "Guayaquil", "0907297083", "", ""); 
INSERT INTO cliente VALUES ("0903972289", "2016-08-04", "Carlos Manuel", "Leon Moran", "Guayaquil", "0912399577", "", ""); 
INSERT INTO cliente VALUES ("0938658545", "2016-08-01", "Andres Alberto", "Mena Tapia", "Guayaquil", "0923554297", "045689721", ""); 
INSERT INTO cliente VALUES ("0960880932", "2016-08-10", "Ivan Alejandro", "Mera Maldonado", "Quito", "0920018143", "045986999", ""); 
INSERT INTO cliente VALUES ("0920018152", "2016-08-09", "Ruddy Maricela", "Moncayo Rea", "Guayaquil", "0966900930", "", ""); 
INSERT INTO cliente VALUES ("0972679210", "2016-08-05", "Emmanuel", "Moran Berreiro", "Guayaquil", "0942301795", "", ""); 
INSERT INTO cliente VALUES ("0953360227", "2016-08-02", "Joe Sebastian", "Saverio Alvarado", "Guayaquil", "0924977979", "042563215", ""); 
INSERT INTO cliente VALUES ("0946351181", "2016-08-05", "Amira Carolina", "Tomala Esparza", "Guayaquil", "0903254458", "", ""); 
INSERT INTO cliente VALUES ("0980872103", "2016-08-05", "Madelyne Carolina", "Velasco Mite", "Guayaquil", "0955733977", "", ""); 
INSERT INTO cliente VALUES ("0978528211", "2016-08-01", "Lucrecia Beatriz", "Vintimilla Cardenas", "Guayaquil", "0930255560", "045889965", ""); 
INSERT INTO cliente VALUES ("0957839585", "2016-08-01", "jose Antonio", "Viteri Cuenca", "Guayaquil", "0986475090", "", ""); 
INSERT INTO cliente VALUES ("0924781166", "2016-08-01", "Erasmo Israel", "Zurita Barahona", "Guayaquil", "0953680843", "", ""); 

SELECT * FROM cliente;








