-- 建立 Employee 表
CREATE TABLE Employee (
    EMP_ID INT PRIMARY KEY,        -- 員工 ID
    NAME VARCHAR(100),             -- 員工姓名
    EMAIL VARCHAR(100),            -- 員工電子郵件
    FLOOR_SEAT_SEQ INT            -- 座位序號（與 SeatingChart 相關聯）
);

-- 建立 SeatingChart 表
CREATE TABLE SeatingChart (
    FLOOR_SEAT_SEQ INT PRIMARY KEY,  -- 座位序號（與 Employee 相關聯）
    FLOOR_NO INT,                    -- 樓層號
    SEAT_NO INT                       -- 座位號
);