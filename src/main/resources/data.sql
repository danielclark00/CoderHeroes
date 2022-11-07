insert into roles (role_name) values ('super_admin');
insert into roles (role_name) values ('admin');
insert into roles (role_name) values ('instructor');
insert into roles (role_name) values ('parent');
insert into roles (role_name) values ('child');

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama001@maildrop.cc', 'Test001 User', '00ulthapbErVUwVJy4x6', 1, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama002@maildrop.cc', 'Test002 User', '00ultwew80Onb2vOT4x6', 2, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama003@maildrop.cc', 'Test003 User', '00ultx74kMUmEW8054x6', 3, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama004@maildrop.cc', 'Test004 User', '00ultwqjtqt4VCcS24x6', 4, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama005@maildrop.cc', 'Test005 User', '00ultwz1n9ORpNFc04x6', 5, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama006@maildrop.cc', 'Test006 User', '00u13omswyZM1xVya4x7', 1, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama007@maildrop.cc', 'Test007 User', '00u13ol5x1kmKxVJU4x7', 2, false);

insert into profiles(email, name, okta_id, role_id, pending)
    values('llama008@maildrop.cc', 'Test008 User', '00u13oned0U8XP8Mb4x7', 3, false);


insert into conversations (profile_id) values (1);

insert into conversations (profile_id) values (2);

insert into conversations (profile_id) values (3);

insert into conversations (profile_id) values (4);

insert into conversations (profile_id) values (5);

insert into conversations (profile_id) values (6);

insert into conversations (profile_id) values (7);

insert into conversations (profile_id) values (8);


insert into messages (title, read, message, conversation_id, sent_by_profile_id)
    values ('Help with Homework?', true, 'I need the answers to the assignment please.', 7, 1);

insert into messages (title, read, message, conversation_id, sent_by_profile_id)
    values ('What''s my grade?', true, 'Hey Ms. Teacher can you tell me my grade?', 7, 5);

insert into messages (title, message, conversation_id, sent_by_profile_id)
    values ('When is class?', 'I noticed the time was funky and had to ask.', 8, 4);

insert into messages (title, message, conversation_id, sent_by_profile_id)
    values ('Is this a yoga course?', 'How is yoga and coding taught together?', 4, 1);

insert into messages (title, message, conversation_id, sent_by_profile_id)
    values ('Where is my achievement?', 'my achievement didn''t pop up when I did course.', 5, 8);

-- SEED programs

insert into programs (program_name, program_description)
    values ('Codercamp', 'Students build their own app based on their own interest');

insert into programs (program_name, program_description)
    values ('Codersitters', 'Coding through play, coding + babysitting (not just code, also creativity)');

insert into programs (program_name, program_description)
    values ('Coderyoga', 'Kids learn coding basics through yoga stories and exercise');

-- SEED admins

insert into admins (profile_id) values (7);

-- SEED instructors

insert into instructors (profile_id, instructor_name, rating, bio, status, approved_by)
    values (3, 'Brianne Caplan', 2, 'I love spaghetti and code, but not the two together.', false, 1);

insert into instructors (profile_id, instructor_name, rating, bio, status, approved_by)
    values (8, 'Adam Smith', 5, 'Coding is life.', true, 1);

-- SEED instructors_program_types

insert into instructors_program_types (instructor_id, program_id) values (1, 1);

insert into instructors_program_types (instructor_id, program_id) values (1, 2);

insert into instructors_program_types (instructor_id, program_id) values (2, 3);

-- SEED courses

insert into courses (course_name, course_description, days_of_week, max_size, enrolled_students, min_age,
    max_age, instructor_id, program_id, start_time, end_time, start_date, end_date, number_of_sessions,
    difficulty, session_type, syllabus_link)
    values ('App Building Fundamentals', 'A month-long course where students with design, build, and deploy an app from beginning to end!',
    '{"Monday"}', 20, 0, 7, 12, 1, 1, '08:00:00', '12:30:00', '04/04/2022', '04/28/2022', 4, 'Easy', 'Group',
    'https://docs.google.com');

insert into courses (course_name, course_description, days_of_week, max_size, enrolled_students, min_age,
    max_age, instructor_id, program_id, start_time, end_time, start_date, end_date, number_of_sessions,
    difficulty, session_type, syllabus_link)
    values ('Mindful Design', 'Students will learn about creativity and web design basics',
    '{"Wednesday", "Friday"}', 12, 0, 6, 10, 2, 3, '15:30:00', '17:45:00', '04/04/2022', '04/28/2022', 8, 'Medium', '1-on-1',
    'https://docs.google.com');

-- SEED newsfeed

insert into newsfeed (title, link, description)
    values('Check out these coding camps!', 'https://www.idtech.com/', 'This is great way to learn more about the coding world!');

insert into newsfeed (title, link, description)
    values('Always check the docs!', 'https://developer.mozilla.org/en-US/',
    'Make sure to check the documentations if you are looking for a method to use and are not sure what the precise name is!');

insert into newsfeed (title, link, description)
    values('Practice your algorithms!', 'https://leetcode.com/', 'Sign up and test your problem-solving skills!');

insert into newsfeed (title, link, description)
    values('Robot dog learns to walk in one hour', 'https://www.sciencedaily.com/releases/2022/07/220718122229.htm',
    'Like a newborn animal, a four-legged robot stumbles around during its first walking attempts. But while a foal or a giraffe needs much longer to master walking, the robot learns to move forward fluently in just one hour. A computer program acts as the artificial presentation of the animal''s spinal cord, and learns to optimize the robot''s movement in a short time. The artificial neural network is not yet ideally adjusted at the beginning, but rapidly self-adjusts.');
