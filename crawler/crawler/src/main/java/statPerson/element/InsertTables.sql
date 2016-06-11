INSERT INTO `SITE` (`ID`, `NAME`, `STARTDATESTATISTICS`) VALUES 

(1, 'http://lenta.ru', null),

(2, 'http://gazeta.ru', null),

(3, 'http://kp.ru', null);

INSERT INTO `PERSON` (`ID`, `NAME`) VALUES 

(1, 'Путин'),

(2, 'Медведев'),

(3, 'Навальный');

INSERT INTO `KEYWORD` (`ID`, `NAME`, `PERSONID`) VALUES 

(1, 'Путин', 1),

(2, 'Putin', 1),

(3, 'Медведев', 2),

(4, 'Medvedev', 2),

(5, 'Навальный', 3);