USE school;

/* 1 */
SELECT lecture.lecture_name, teacher.lastname, teacher.firstname
FROM teacher
         INNER JOIN lecture
                    ON  teacher.id = lecture.teacher_id;

/*2*/

/* add a lecture for clarity*/

INSERT INTO school.lecture (create_date, lecture_date, course_id, teacher_id, lecture_name, lecture_description)
VALUES ('2022-02-12 14:00:00', '2022-02-13 14:00:00', 3, 2, 'history', 'history of computer graphics');

SELECT teacher.lastname, teacher.firstname, lecture.teacher_id, count(lecture.teacher_id)
FROM teacher
         INNER JOIN lecture
                    ON  teacher.id = lecture.teacher_id
group by lecture.teacher_id;

/*3*/

/* add a lecture for clarity*/

INSERT INTO school.lecture (create_date, lecture_date, course_id, teacher_id, lecture_name, lecture_description)
VALUES ('2023-02-14 12:00:00', '2023-02-17 12:00:00', 1, 3, 'jdbc', 'jdbc as new magic');
INSERT INTO school.lecture (create_date, lecture_date, course_id, teacher_id, lecture_name, lecture_description)
VALUES ('2023-04-14 12:00:00', '2023-04-17 12:00:00', 1, 3, 'junit', 'junit as new kind of dementors');

SELECT lecture.lecture_date, lecture.lecture_name
FROM lecture
         JOIN teacher
              ON  teacher.id = lecture.teacher_id
WHERE lecture.teacher_id = 3
ORDER BY lecture.lecture_date;

/*4*/


/*5*/
SELECT MONTHNAME(lecture.lecture_date), COUNT(*)
FROM lecture
group by MONTHNAME(lecture.lecture_date);

/*6*/
