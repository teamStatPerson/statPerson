INSERT INTO `sites` (`ID`, `Name`, `StartDateStatistics`) VALUES 

(1, 'http://lenta.ru', null),

(2, 'http://gazeta.ru', null),

(3, 'http://kp.ru', null);

INSERT INTO `persons` (`ID`, `Name`) VALUES 

(1, 'Путин'),

(2, 'Медведев'),

(3, 'Навальный');

INSERT INTO `keywords` (`ID`, `Name`, `PersonID`) VALUES 

(1, 'Путин', 1),

(2, 'Putin', 1),

(3, 'Медведев', 2),

(4, 'Medvedev', 2),

(5, 'Навальный', 3);