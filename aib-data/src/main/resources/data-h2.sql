SET @lorem='Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aliquid harum asperiores facilis,' ||
            'quisquam eaque vel nihil officia culpa ducimus voluptate temporibus in dolorum, error quos repellat voluptatibus cumque.' ||
            'Nam! Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aliquid harum asperiores facilis, quisquam eaque' ||
            'vel nihil officia culpa ducimus voluptate temporibus in dolorum, error quos repellat voluptatibus cumque. Nam! Lorem ipsum dolor' ||
            'sit amet consectetur adipisicing elit. Laboriosam, aliquid harum asperiores facilis, quisquam eaque vel nihil officia culpa ducimus' ||
            'voluptate temporibus in dolorum, error quos repellat voluptatibus cumque. Nam! Lorem ipsum dolor sit amet consectetur adipisicing elit.' ||
            'Laboriosam, aliquid harum asperiores facilis, quisquam eaque vel nihil officia culpa ducimus voluptate temporibus in dolorum,' ||
            'error quos repellat voluptatibus cumque. Nam!';

INSERT INTO board (id, title, genre)
VALUES
('a', 'Anime', 'JAPAN'),
('mng', 'Manga', 'JAPAN'),
('m', 'Mecha', 'JAPAN'),
('jp', 'Otaku Culture', 'JAPAN'),
('v', 'Video Games', 'GAMES'),
('vp', 'Pokemon', 'GAMES'),
('vr', 'Retro Games', 'GAMES'),
('mmo', 'MMO Games', 'GAMES'),
('g', 'Technology', 'INTERESTS'),
('sci', 'Science', 'INTERESTS'),
('toy', 'Toys', 'INTERESTS'),
('int', 'International', 'INTERESTS'),
('an', 'Animals', 'INTERESTS'),
('his', 'History', 'INTERESTS'),
('p', 'Photography', 'CREATIVE'),
('ck', 'Food & Cooking', 'CREATIVE'),
('po', 'Papercraft & Origami', 'CREATIVE'),
('ic', 'Artwork', 'CREATIVE'),
('walp', 'Wallpapers', 'CREATIVE'),
('mu', 'Music', 'CREATIVE'),
('lit', 'Literature', 'CREATIVE'),
('gd', 'Graphic Design', 'CREATIVE'),
('fa', 'Fashion', 'CREATIVE');

INSERT INTO thread (id, title, board_id, image_url, content)
VALUES
(0, 'Neon Genesis Evangelion Thread', 'a', 'http://localhost:8081/images/eva.png', @lorem),
(1, 'K-On Thread', 'a', 'http://localhost:8081/images/k-on.jpg', @lorem),
(2, 'JoJo Thread', 'a', 'http://localhost:8081/images/jojo.jpg', @lorem),
(3, 'SAO Thread', 'a', 'http://localhost:8081/images/sao.jpg', @lorem),
(4, 'ReZero Thread', 'a', 'http://localhost:8081/images/rezero.png', @lorem);
