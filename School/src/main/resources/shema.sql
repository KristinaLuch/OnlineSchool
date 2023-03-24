    USE school;

    CREATE TABLE course (
                            id INTEGER AUTO_INCREMENT PRIMARY KEY,
                            course_name VARCHAR(30)
    );

    CREATE TABLE  teacher(
                             id INTEGER AUTO_INCREMENT PRIMARY KEY,
                             firstname VARCHAR(30),
                             lastname VARCHAR(30),
                             phone VARCHAR(30),
                             email VARCHAR(30) UNIQUE
    );

    CREATE TABLE  student(
                             id INTEGER AUTO_INCREMENT PRIMARY KEY,
                             firstname VARCHAR(30),
                             lastname VARCHAR(30),
                             phone VARCHAR(30),
                             email VARCHAR(30) UNIQUE
    );

    CREATE TABLE course_student (
                                    course_id INT NOT NULL,
                                    student_id INT NOT NULL,
                                    PRIMARY KEY (course_id, student_id),
                                    FOREIGN KEY (course_id) REFERENCES course(id),
                                    FOREIGN KEY (student_id) REFERENCES student(id)
    );
    CREATE TABLE course_teacher (
                                    course_id INT NOT NULL,
                                    teacher_id INT NOT NULL,
                                    PRIMARY KEY (course_id, teacher_id),
                                    FOREIGN KEY (course_id) REFERENCES course(id),
                                    FOREIGN KEY (teacher_id) REFERENCES teacher(id)
    );

    CREATE TABLE  lecture(
                             id INTEGER AUTO_INCREMENT PRIMARY KEY,
                             create_date DATETIME,
                             lecture_date DATETIME,
                             course_id INT DEFAULT NULL,
                             teacher_id INT DEFAULT NULL,
                             FOREIGN KEY (course_id) REFERENCES course (id),
                             FOREIGN KEY (teacher_id) REFERENCES teacher (id),
                             lecture_name VARCHAR(30),
                             lecture_description VARCHAR(150)
    );

    CREATE TABLE  materials(
                               id INTEGER AUTO_INCREMENT PRIMARY KEY,
                               lecture_id INT,
                               FOREIGN KEY (lecture_id) REFERENCES lecture (id),
                               material VARCHAR(150)
    );
    CREATE TABLE  homework(
                              id INTEGER AUTO_INCREMENT PRIMARY KEY,
                              lecture_id INT,
                              FOREIGN KEY (lecture_id) REFERENCES lecture (id),
                              task VARCHAR(150)
    );
    CREATE TABLE  additional_materials(
                              id INTEGER AUTO_INCREMENT PRIMARY KEY,
                              lecture_id INT,
                              FOREIGN KEY (lecture_id) REFERENCES lecture (id),
                              add_mat_name VARCHAR(30),
                              resource_type VARCHAR(30)
    );

