-- 插入員工資料
INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ)
VALUES (10001, '甲行員', 'aaaa@eample.com', 101),
       (10002, '乙行員', 'bbbb@example.com', 102),
       (10003, '丙行員', 'cccc@example.com', 103),
	   (10004, '丁行員', 'dddd@example.com', 104),
	   (10005, '戊行員', 'eeee@example.com', 105);
	   
-- 插入座位圖資料
INSERT INTO SeatingChart (FLOOR_SEAT_SEQ)
VALUES (101), 
       (102), 
       (103),
	   (105),
	   (104);

select * from Employee	  
select * from SeatingChart