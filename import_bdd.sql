-- ------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- CREATE TABLE ------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------------

CREATE TABLE course_type (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE course (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	time BIGINT NOT NULL,
	id_course_type BIGINT NOT NULL,
	id_subject_year BIGINT NOT NULL,
	id_teacher BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE course_stream (
	id_course BIGINT PRIMARY KEY,
	state TINYINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE deposit (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_course BIGINT NOT NULL,
	id_student BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE file (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE,
	filename VARCHAR(150) NOT NULL,
	MIME VARCHAR(50) NOT NULL,
	time BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE file_course (
	id_file BIGINT PRIMARY KEY,
	id_course BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE file_deposit (
	id_file BIGINT PRIMARY KEY,
	id_deposit BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE history (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	message VARCHAR(50) NOT NULL, 
	time BIGINT NOT NULL,
	id_student BIGINT NOT NULL, 
	id_course BIGINT NOT NULL 
) ENGINE=InnoDB;

CREATE TABLE note (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	note FLOAT NOT NULL,
	id_course BIGINT NOT NULL,
	id_student BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE offer_subject (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	price FLOAT NOT NULL,
	nb_school_subject INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE offer_mode (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE,
	description TEXT NOT NULL,
	price FLOAT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE parent_assignment (
	id_parent BIGINT NOT NULL,
	id_student BIGINT NOT NULL,
	PRIMARY KEY(id_parent, id_student)
) ENGINE=InnoDB;

CREATE TABLE planning (
	id_course BIGINT PRIMARY KEY,
	begin BIGINT NOT NULL,
	end BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE planning_assignment (
	id_user BIGINT NOT NULL,
	id_planning BIGINT NOT NULL,
	PRIMARY KEY(id_user, id_planning)
) ENGINE=InnoDB;

CREATE TABLE school_subject_year (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	id_school_subject BIGINT NOT NULL,
	id_school_year BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE school_subject (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE school_year (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE session (
	session_key VARCHAR(32) PRIMARY KEY,
	time BIGINT NOT NULL,
	id_user BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE student_assignment (
	id_student BIGINT NOT NULL,
	id_subject_year BIGINT NOT NULL,
	PRIMARY KEY(id_student, id_subject_year)
) ENGINE=InnoDB;

CREATE TABLE student_support (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	year BIGINT NOT NULL,
	id_student BIGINT NOT NULL,
	id_teacher BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE teacher_assignment (
	id_teacher BIGINT NOT NULL,
	id_subject_year BIGINT NOT NULL,
	PRIMARY KEY(id_teacher, id_subject_year)
) ENGINE=InnoDB;

CREATE TABLE user_type (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE user (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(60) NOT NULL UNIQUE,
	student_key VARCHAR(16) UNIQUE,
	last_name VARCHAR(50) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	id_user_type BIGINT NOT NULL
) ENGINE=InnoDB;

-- ------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- CREATE INDEX ------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------------

-- ------------------------- COURSE ---------------------------
CREATE INDEX IDX_COURSE_TYPE ON course (id_course_type);
CREATE INDEX IDX_COURSE_SUBJECT_YEAR ON course (id_subject_year);
CREATE INDEX IDX_COURSE_TEACHER ON course (id_teacher);

-- ------------------------- DEPOSIT ---------------------------
CREATE INDEX IDX_DEPOSIT_COURSE ON deposit (id_course);
CREATE INDEX IDX_DEPOSIT_STUDENT ON deposit (id_student);

-- ------------------------- HISTORY ---------------------------
CREATE INDEX IDX_HISTORY_COURSE ON history (id_course);
CREATE INDEX IDX_HISTORY_STUDENT ON history (id_student);

-- ------------------------- FILE_COURSE ---------------------------
CREATE INDEX IDX_FILE_COURSE_COURSE ON file_course (id_course);

-- ------------------------- FILE_DEPOSIT ---------------------------
CREATE INDEX IDX_FILE_DEPOSIT_DEPOSIT ON file_deposit (id_deposit);

-- ------------------------- NOTE ---------------------------
CREATE INDEX IDX_NOTE_COURSE ON note (id_course);
CREATE INDEX IDX_NOTE_STUDENT ON note (id_student);

-- ------------------------- PLANNING ---------------------------
CREATE INDEX IDX_PLANNING_COURSE ON planning (id_course);

-- ------------------------- SCHOOL_SUBJECT_YEAR ---------------------------
CREATE INDEX IDX_SUBJECT_YEAR_SCHOOL_SUBJECT ON school_subject_year (id_school_subject);
CREATE INDEX IDX_SUBJECT_YEAR_SCHOOL_YEAR ON school_subject_year (id_school_year);

-- ------------------------- SESSION ---------------------------
CREATE INDEX IDX_SESSION_USER ON session (id_user);

-- ------------------------- STUDENT_SUPPORT ---------------------------
CREATE INDEX IDX_STUDENT_SUPPORT_STUDENT ON student_support (id_student);
CREATE INDEX IDX_STUDENT_SUPPORT_TEACHER ON student_support (id_teacher);

-- ------------------------- USER ---------------------------
CREATE INDEX IDX_USER_LOGIN ON user (login);
CREATE INDEX IDX_USER_TYPE ON user (id_user_type);
CREATE INDEX IDX_USER_STUDENT_KEY ON user (student_key);

-- ------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- CREATE VIEW ------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------------

CREATE VIEW view_file_course AS
	SELECT *
	FROM file
	INNER JOIN file_course
		ON file.id = file_course.id_file;
		
CREATE VIEW view_file_deposit AS
	SELECT *
	FROM file
	INNER JOIN file_deposit
		ON file.id = file_deposit.id_file;

-- -----------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- INSERT INTO ------------------------------------------------------
-- -----------------------------------------------------------------------------------------------------------------------


-- ------------------------- COURSE ---------------------------
INSERT INTO course (id, title, time, id_course_type, id_subject_year, id_teacher) VALUES
(5, "Cours A", 1556482304, 1, 1, 2),
(6, "Cours B", 1556482304, 1, 1, 2),
(7, "Cours C", 1556482304, 1, 1, 2),
(8, "Cours D", 1556482304, 1, 1, 2);

-- ------------------------- COURSE_TYPE ---------------------------
INSERT INTO course_type (name) VALUES 
("Cours"),
("Exercice"),
("Streaming");

-- ------------------------- FILE ---------------------------
INSERT INTO file (id, name, filename, MIME, time) VALUES
(1, "Introduction logique du premier ordre", "fol.pdf", "application/pdf", 0),
(2, "Conjecture de Poincaré", "besson.pdf", "application/pdf", 0),
(3, "Problèmes NP", "np.pdf", "application/pdf", 1556482304),
(4, "Algortihme de Dijkstra", "dijkstra.pdf", "application/pdf", 1556482304);

-- ------------------------- FILE_COURSE ---------------------------
INSERT INTO file_course (id_file, id_course) VALUES
(1, 5),
(2, 6),
(3, 7),
(4, 8);

-- ------------------------- PARENT_ASSIGN ---------------------------
INSERT INTO parent_assignment (id_parent, id_student) VALUES
(3, 4);

-- ------------------------- SCHOOL_SUBJECT_ ---------------------------
INSERT INTO school_subject (id, name) VALUES
(1, "Mathématique");

-- ------------------------- SCHOOL_SUBJECT_YEAR ---------------------------
INSERT INTO school_subject_year (id, id_school_subject, id_school_year) VALUES
(1, 1, 1);

-- ------------------------- SCHOOL_YEAR ---------------------------
INSERT INTO school_year (id, name) VALUES
(1, "Terminal STI2D");

-- ------------------------- STUDENT_ASSIGNMENT ---------------------------
INSERT INTO student_assignment (id_student, id_subject_year) VALUES
(4, 1);

-- ------------------------- TEACHER_ASSIGNMENT ---------------------------
INSERT INTO teacher_assignment (id_teacher, id_subject_year) VALUES
(2, 1);

-- ------------------------- USER ---------------------------
INSERT INTO user (id, login, password, student_key, last_name, first_name, id_user_type) VALUES
(1, "admin@mooc.fr", "$2y$10$nYe9jj37JYdHyJV090LJcuQPR8EhL0j4nZv5NidUlWZ2Vgqrfjc8y", NULL, "admin", "admin", 1),
(2, "teacher@mooc.fr", "$2y$10$.HXdUlxW/12RonCrGKp20.WADRGZQcro3uhhnXxGil6JAUh/ZOvui", NULL, "teacher", "teacher", 2),
(3, "parent@mooc.fr", "$2y$10$gIJU5.fPKvyLs0Twq9Bd9OwVrXVBWjxXQQNv4LQsir3Dozg51Ly7K", NULL, "parent", "parent", 3),
(4, "student@mooc.fr", "$2y$10$OCYC6faMclI.IBq6SIlY5OLmYr93slb4l7VagztgtN2a6.DLfraVO", "2e8c72d93597f573", "student", "student", 4);


-- ------------------------- USER_TYPE ---------------------------
INSERT INTO user_type (name) VALUES 
("administrateur"),
("professeur"),
("parent"),
("élève");

-- -----------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- FOREIGN KEY ------------------------------------------------------
-- -----------------------------------------------------------------------------------------------------------------------

-- ------------------------- FILE_COURSE ---------------------------
ALTER TABLE file_course
	ADD CONSTRAINT FK_FILE_COURSE_FILE FOREIGN KEY (id_file) REFERENCES file(id) ON DELETE CASCADE;

ALTER TABLE file_course
	ADD CONSTRAINT FK_FILE_COURSE_COURSE FOREIGN KEY (id_course) REFERENCES course(id) ON DELETE CASCADE;

-- ------------------------- FILE_DEPOSIT ---------------------------
ALTER TABLE file_deposit
	ADD CONSTRAINT FK_FILE_DEPOSIT_FILE FOREIGN KEY (id_file) REFERENCES file(id) ON DELETE CASCADE;

ALTER TABLE file_deposit
	ADD CONSTRAINT FK_FILE_DEPOSIT_DEPOSIT FOREIGN KEY (id_deposit) REFERENCES deposit(id) ON DELETE CASCADE;

-- ------------------------- COURSE ---------------------------
ALTER TABLE course
	ADD CONSTRAINT FK_COURSE_TYPE FOREIGN KEY (id_course_type) REFERENCES course_type(id) ON DELETE RESTRICT;

ALTER TABLE course
	ADD CONSTRAINT FK_COURSE_SUBJECT_YEAR FOREIGN KEY (id_subject_year) REFERENCES school_subject_year(id) ON DELETE RESTRICT;

ALTER TABLE course
	ADD CONSTRAINT FK_COURSE_TEACHER FOREIGN KEY (id_teacher) REFERENCES user(id) ON DELETE RESTRICT;
	
-- ------------------------- COURSE_STREAM ---------------------------
ALTER TABLE course_stream
	ADD CONSTRAINT FK_STREAM_COURSE FOREIGN KEY (id_course) REFERENCES course(id) ON DELETE CASCADE;

-- ------------------------- DEPOSIT ---------------------------
ALTER TABLE deposit
	ADD CONSTRAINT FK_DEPOSIT_COURSE FOREIGN KEY (id_course) REFERENCES course(id) ON DELETE CASCADE;

ALTER TABLE deposit
	ADD CONSTRAINT FK_DEPOSIT_STUDENT FOREIGN KEY (id_student) REFERENCES user(id) ON DELETE CASCADE;

-- ------------------------- HISTORY ---------------------------
ALTER TABLE history
	ADD CONSTRAINT FK_HISTORY_STUDENT FOREIGN KEY (id_student) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE history
	ADD CONSTRAINT FK_HISTORY_COURSE FOREIGN KEY (id_course) REFERENCES course(id) ON DELETE CASCADE;

-- ------------------------- NOTE ---------------------------
ALTER TABLE note
	ADD CONSTRAINT FK_NOTE_COURSE FOREIGN KEY (id_course) REFERENCES course(id) ON DELETE CASCADE;

ALTER TABLE note
	ADD CONSTRAINT FK_NOTE_STUDENT FOREIGN KEY (id_student) REFERENCES user(id) ON DELETE CASCADE;

-- ------------------------- PARENT_ASSIGNMENT ---------------------------
ALTER TABLE parent_assignment
	ADD CONSTRAINT FK_PARENT_ASSIGNMENT_PARENT FOREIGN KEY (id_parent) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE parent_assignment
	ADD CONSTRAINT FK_PARENT_ASSIGNMENT_STUDENT FOREIGN KEY (id_student) REFERENCES user(id) ON DELETE CASCADE;

-- ------------------------- PLANNING ---------------------------
ALTER TABLE planning
	ADD CONSTRAINT FK_PLANNING_COURSE FOREIGN KEY (id_course) REFERENCES course(id) ON DELETE CASCADE;

-- ------------------------- PLANNING_ASSIGNMENT ---------------------------
ALTER TABLE planning_assignment
	ADD CONSTRAINT FK_PLANNING_ASSIGNMENT_USER FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE planning_assignment
	ADD CONSTRAINT FK_PLANNING_ASSIGNMENT_PLANNING FOREIGN KEY (id_planning) REFERENCES planning(id_course) ON DELETE CASCADE;

-- ------------------------- SCHOOL_SUBJECT_YEAR ---------------------------
ALTER TABLE school_subject_year
	ADD CONSTRAINT FK_SUBJECT_YEAR_SCHOOL_SUBJECT FOREIGN KEY (id_school_subject) REFERENCES school_subject(id) ON DELETE RESTRICT;

ALTER TABLE school_subject_year
	ADD CONSTRAINT FK_SUBJECT_YEAR_SCHOOL_YEAR FOREIGN KEY (id_school_year) REFERENCES school_year(id) ON DELETE RESTRICT;

-- ------------------------- SESSION ---------------------------
ALTER TABLE session
	ADD CONSTRAINT FK_SESSION_USER FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE;

-- ------------------------- STUDENT_ASSIGNMENT ---------------------------
ALTER TABLE student_assignment
	ADD CONSTRAINT FK_STUDENT_ASSIGNMENT_STUDENT FOREIGN KEY (id_student) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE student_assignment
	ADD CONSTRAINT FK_STUDENT_ASSIGNMENT_SUBJECT_YEAR FOREIGN KEY (id_subject_year) REFERENCES school_subject_year(id) ON DELETE CASCADE;

-- ------------------------- STUDENT_SUPPORT ---------------------------
ALTER TABLE student_support
	ADD CONSTRAINT FK_STUDENT_SUPPORT_STUDENT FOREIGN KEY (id_student) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE student_support
	ADD CONSTRAINT FK_STUDENT_SUPPORT_TEACHER FOREIGN KEY (id_teacher) REFERENCES user(id) ON DELETE CASCADE;

-- ------------------------- TEACHER_ASSIGNMENT ---------------------------
ALTER TABLE teacher_assignment
	ADD CONSTRAINT FK_TEACHER_ASSIGNMENT_TEACHER FOREIGN KEY (id_teacher) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE teacher_assignment
	ADD CONSTRAINT FK_TEACHER_ASSIGNMENT_SUBJECT_YEAR FOREIGN KEY (id_subject_year) REFERENCES school_subject_year(id) ON DELETE CASCADE;

-- ------------------------- USER ---------------------------
ALTER TABLE user
	ADD CONSTRAINT FK_USER_TYPE FOREIGN KEY (id_user_type) REFERENCES user_type(id) ON DELETE RESTRICT;

-- -------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- PROCEDURE ----------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------------------------

DELIMITER // 
CREATE PROCEDURE PROC_CHECK_USER (IN id_user BIGINT, IN expected_user_type BIGINT)
BEGIN
	DECLARE user_type BIGINT;
	
	IF id_user IS NOT NULL THEN
		SELECT id_user_type INTO user_type
		FROM user
		WHERE id = id_user;

		IF (user_type <> expected_user_type) THEN
			signal sqlstate '27000' set message_text = "Type d'utilisateur innatendu";
		END IF;
	END IF;
END
//
DELIMITER ;

DELIMITER // 
CREATE PROCEDURE PROC_CHECK_COURSE (IN id_course BIGINT, IN expected_course_type BIGINT)
BEGIN
	DECLARE course_type BIGINT;
	
	IF id_course IS NOT NULL THEN
		SELECT id_course_type INTO course_type
		FROM course
		WHERE id = id_course;

		IF (course_type <> expected_course_type) THEN
			signal sqlstate '27000' set message_text = "Type de cours innatendu";
		END IF;
	END IF;
END
//
DELIMITER ;

-- -------------------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------- TRIGGER/EVENT ------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------------------------

-- -------------------------- INSERT ---------------------------

-- ------------ COURSE --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_COURSE
BEFORE INSERT ON course FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_teacher, 2);
END
//
DELIMITER ;

-- ------------ COURSE_STREAM --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_STREAM
BEFORE INSERT ON course_stream FOR EACH ROW
BEGIN
	CALL PROC_CHECK_COURSE(NEW.id_course, 3);
END
//
DELIMITER ;

-- ------------ DEPOSIT --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_DEPOSIT
BEFORE INSERT ON deposit FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_student, 4);
	CALL PROC_CHECK_COURSE(NEW.id_course, 2);
END
//
DELIMITER ;

-- ------------ HISTORY --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_HISTORY
BEFORE INSERT ON history FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_student, 4);
END
//
DELIMITER ;

-- ------------ NOTE --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_NOTE
BEFORE INSERT ON note FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_student, 4);
	CALL PROC_CHECK_COURSE(NEW.id_course, 2);
END
//
DELIMITER ;

-- ------------ PARENT_ASSIGNMENT --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_PARENT_ASSIGNMENT
BEFORE INSERT ON parent_assignment FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_parent, 3);
	CALL PROC_CHECK_USER(NEW.id_student, 4);
END
//
DELIMITER ;

-- ------------ STUDENT_ASSIGNMENT --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_STUDENT_ASSIGNMENT
BEFORE INSERT ON student_assignment FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_student, 4);
END
//
DELIMITER ;

-- ------------ STUDENT_SUPPORT --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_STUDENT_SUPPORT
BEFORE INSERT ON student_support FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_teacher, 2);
	CALL PROC_CHECK_USER(NEW.id_student, 4);
END
//
DELIMITER ;

-- ------------ TEACHER_ASSIGNMENT --------------

DELIMITER // 
CREATE TRIGGER TRG_INS_TEACHER_ASSIGNMENT
BEFORE INSERT ON teacher_assignment FOR EACH ROW
BEGIN
	CALL PROC_CHECK_USER(NEW.id_teacher, 2);
END
//
DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------------
-- --------------------------------------------------------- EVENT --------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------------

-- ------------ SESSION --------------

DELIMITER //
CREATE EVENT EVENT_CLEAN_SESSION
ON SCHEDULE EVERY 12 HOUR STARTS '2017-11-29 00:00:00'
ON COMPLETION NOT PRESERVE ENABLE DO
DELETE FROM session WHERE (time + 604800) < UNIX_TIMESTAMP(CURRENT_TIMESTAMP)
//
DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------------
-- --------------------------------------------------------- END ----------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------------------
