INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
    ('John Doe', '1990-05-15', 'Senior', 6000),
    ('Jane Smith', '1985-10-20', 'Middle', 4000),
    ('Alex Johnson', '1995-02-10', 'Junior', 2000),
    ('Emily Davis', '2000-07-30', 'Trainee', 800),
    ('Michael Brown', '1980-12-25', 'Senior', 7000),
    ('Emma Wilson', '1993-04-18', 'Middle', 4500),
    ('Daniel Taylor', '1998-09-05', 'Junior', 2500),
    ('Olivia Lee', '1987-03-12', 'Middle', 4300),
    ('William Moore', '1975-06-08', 'Senior', 7500),
    ('Sophia Taylor', '1988-11-03', 'Middle', 5000);


INSERT INTO client (NAME) VALUES
    ('Client A'),
    ('Client B'),
    ('Client C'),
    ('Client D'),
    ('Client E');


INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
    (1, '2024-01-01', '2024-07-01'),
    (2, '2024-02-15', '2024-09-15'),
    (3, '2024-03-10', '2024-03-10'),
    (4, '2024-04-20', '2024-04-20'),
    (5, '2024-06-01', '2024-12-01');


INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (3, 7),
    (4, 8),
    (5, 9),
    (5, 10);