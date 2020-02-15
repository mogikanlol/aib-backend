SET @lorem='Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aliquid harum asperiores facilis,' ||
            'quisquam eaque vel nihil officia culpa ducimus voluptate temporibus in dolorum, error quos repellat voluptatibus cumque.' ||
            'Nam! Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aliquid harum asperiores facilis, quisquam eaque' ||
            'vel nihil officia culpa ducimus voluptate temporibus in dolorum, error quos repellat voluptatibus cumque. Nam! Lorem ipsum dolor' ||
            'sit amet consectetur adipisicing elit. Laboriosam, aliquid harum asperiores facilis, quisquam eaque vel nihil officia culpa ducimus' ||
            'voluptate temporibus in dolorum, error quos repellat voluptatibus cumque. Nam! Lorem ipsum dolor sit amet consectetur adipisicing elit.' ||
            'Laboriosam, aliquid harum asperiores facilis, quisquam eaque vel nihil officia culpa ducimus voluptate temporibus in dolorum,' ||
            'error quos repellat voluptatibus cumque. Nam!';
SET @ content = 'Lorem ipsum dolor sit amet consectetur adipisicing elit. ' ||
                'Error, voluptas itaque nemo ullam rerum modi aperiam dicta quibusdam neque ' ||
                'tempore quo repellendus sunt, ab reiciendis quam quisquam obcaecati alias expedita?';

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

INSERT INTO thread (id, title, board_id, image_name, content)
VALUES
(0, 'Neon Genesis Evangelion Thread', 'a', 'eva.png', @lorem),
(1, 'K-On Thread', 'a', 'k-on.jpg', @lorem),
(2, 'JoJo Thread', 'a', 'jojo.jpg', @lorem),
(3, 'SAO Thread', 'a', 'sao.jpg', @lorem),
(4, 'ReZero Thread', 'a', 'rezero.png', @lorem);

ALTER sequence thread_id_seq restart with 5;

INSERT INTO post (id, content, thread_id)
VALUES
(0, @content, 0),
(1, @content, 0),
(2, @content, 0),
(3, @content, 0),
(4, @content, 0);

ALTER sequence post_id_seq restart with 5;