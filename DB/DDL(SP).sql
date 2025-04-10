CREATE PROCEDURE GetEmployeeSeatingInfo
AS
BEGIN
    SELECT 
        e.EMP_ID, 
        e.NAME, 
        e.EMAIL, 
		e.FLOOR_SEAT_SEQ,
        s.FLOOR_NO, 
        s.SEAT_NO
    FROM 
        Employee e
    JOIN 
        SeatingChart s
    ON 
        e.FLOOR_SEAT_SEQ = s.FLOOR_SEAT_SEQ;
END;