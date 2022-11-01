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

