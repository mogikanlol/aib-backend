INSERT INTO board (id, title, genre)
VALUES
('jp', 'Japan', 'JAPAN'),
('a', 'Anime', 'JAPAN');

INSERT INTO thread (id, title, board_id, image_url, content)
VALUES
(0, 'Neon Genesis Evangelion Thread', 'a', 'http://localhost:8081/images/eva.png', 'content');
ALTER sequence thread_id_seq restart with 1;