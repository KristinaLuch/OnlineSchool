    USE school;

    INSERT INTO school.course(course_name)
    VALUES
        ('IT'),
        ('marketing'),
        ('design');

    INSERT INTO school.student(firstname, lastname, phone, email)
    VALUES
        ('Nazar', 'Ivchenko', '+3809999999', 'nazar@gmail.com'),
        ('Anna', 'Sternenko', '+3806666666', 'anna@gmail.com'),
        ('Kirill', 'Symonenko', '+3806666888', 'kirill@gmail.com'),
        ('Narine', 'Arahtyan', '+3806666333', 'narine@gmail.com'),
        ('Святослав', 'Козак', '+3806666333', 'kozak@gmail.com'),
        ('Nick', 'Smith', '+38033333337', 'nick@gmail.com');

    INSERT INTO school.course_student(course_id, student_id)
    VALUES
        (1, 6),
        (1, 5),
        (1, 4),
        (2, 3),
        (3, 4),
        (3, 2),
        (3, 6);

    INSERT INTO school.teacher(firstname, lastname, phone, email)
    VALUES
        ('Valide', 'Sultan', '+3806777333', 'valide@gmail.com'),
        ('Severus', 'Snape', '+3807777333', 'snape@gmail.com'),
        ('Albus', 'Dumbledore', '+38033333337', 'albus@gmail.com');

    INSERT INTO school.course_teacher(course_id, teacher_id)
    VALUES
        (1, 2),
        (2, 3),
        (3, 1);

    INSERT INTO school.lecture(create_date, lecture_date, course_id, teacher_id, lecture_name, lecture_description)
    VALUES
        ('2022-02-10 14:00:00', '2022-02-11 14:00:00', 1, 2, 'sql', 'sos'),
        ('2023-05-17 10:00:00', '2024-05-17 10:30:00', 2, 3, 'promotion', 'type of promotion'),
        ('2020-11-02 10:00:00', '2020-11-13 12:00:00', 3, 1, 'vector graphics', 'advantages of vector graphics');

    INSERT INTO school.homework(lecture_id, task)
    VALUES
        (1,  'print sql script'),
        (2,  'make a flyer'),
        (3,  'make a flyer'),
        (2,  'read book p.20-35');

    INSERT INTO school.materials(lecture_id, material)
    VALUES
        (1,  'SQL instruction'),
        (2,  'marketing instruction'),
        (3,  'design instruction');

    INSERT INTO school.additional_materials(lecture_id, add_mat_name, resource_type)
    VALUES
        (1,  'SQL for Dummies', 'book'),
        (2,  'https://www.atbmarket.com/', 'url'),
        (2,  'design instruction', 'video');

