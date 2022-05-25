INSERT INTO article(id,title,content) VALUES (1, '게시판 글등록!', '안녕하세요');
INSERT INTO article(id,title,content) VALUES (2, '첫글이요~!', '안녕요');
INSERT INTO article(id,title,content) VALUES (3, 'ㅋㅋ', '등업점');

-- article 더미 데이터
INSERT INTO article(id,title,content) VALUES (4, '점심뭐먹어', '댓글 ㄱ');
INSERT INTO article(id,title,content) VALUES (5, '저녁뭐먹어', '댓 ㄱ');
INSERT INTO article(id,title,content) VALUES (6, '내일 운동뭐행', 'TAHT GO');

-- comment 더미 데이터

--Id 4번의 댓글
INSERT INTO comment(id,article_id,nickname,body) VALUES (1,4, '대후니', '배불러');
INSERT INTO comment(id,article_id,nickname,body) VALUES (2,4, '대후니', '근데 그냥 치킨드셈');
INSERT INTO comment(id,article_id,nickname,body) VALUES (3,4, '손대훈', '햄버거');

--id 5번의 댓글
INSERT INTO comment(id,article_id,nickname,body) VALUES (4,5, '이글', '알아서드셈');
INSERT INTO comment(id,article_id,nickname,body) VALUES (5,5, '하우스', '킹근이');
INSERT INTO comment(id,article_id,nickname,body) VALUES (6,5, 'RA', '킹식코드');

--id 6번의 댓글
INSERT INTO comment(id,article_id,nickname,body) VALUES (7,6, '연세', '가슴이두');
INSERT INTO comment(id,article_id,nickname,body) VALUES (8,6, '대학교', '등 삼두');
INSERT INTO comment(id,article_id,nickname,body) VALUES (9,6, '연세', '그리고 유산소');



