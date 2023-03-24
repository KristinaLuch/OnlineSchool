USE school;
/*1. students sort lastname*/
SELECT * FROM student ORDER BY lastname;

/*2.lecture_name with add.mat. sort for date before 2023 */
/*change the data for clarity*/
USE school;
update lecture set lecture_date = '2024-05-17 10:30:00' WHERE id = 2;

SELECT additional_materials.id, additional_materials.add_mat_name, additional_materials.resource_type, lecture.lecture_date, lecture.lecture_name
FROM additional_materials, lecture
WHERE additional_materials.lecture_id = lecture.id AND lecture_date < '2023-01-01 00:00:00'
ORDER BY lecture_date;
/*3.*/
WITH lecture_homework_counts AS (
    SELECT
        l.id,
        l.lecture_name,
        l.create_date,
        l.lecture_date,
        COUNT(h.id) AS homework_count
    FROM lecture l
             JOIN homework h ON l.id = h.lecture_id
    GROUP BY l.id, l.create_date, l.lecture_date
)

SELECT
    id,
    lecture_name,
    create_date,
    lecture_date,
    homework_count
FROM lecture_homework_counts
ORDER BY homework_count DESC, create_date ASC
    LIMIT 1;

/*4.*/

SELECT resource_type, count(*) FROM additional_materials GROUP BY resource_type;

/*5.*/
SELECT * FROM teacher WHERE lastname between 'A' and 'N' or lastname between 'а' and 'н';

/*6.*/
SELECT lastname, firstname, COUNT(*) AS course_count
FROM student
         JOIN course_student ON student.id = course_student.student_id
GROUP BY lastname, firstname
HAVING course_count = 1 OR course_count = 2 OR course_count >= 3
ORDER BY lastname;