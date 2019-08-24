-- roles
INSERT INTO role (id, name) VALUES (NEXTVAL('role_id_seq'), 'ADMIN');
INSERT INTO role (id, name) VALUES (NEXTVAL('role_id_seq'), 'USER');
INSERT INTO role (id, name) VALUES (NEXTVAL('role_id_seq'), 'MODERATOR');

-- users
INSERT INTO "user" (id, account_non_expired, account_non_locked, created, credentials_non_expired, email, enabled, password, username) VALUES (nextval('user_id_seq'), true, true, '2019-04-06 21:28:04.105000', true, 'a@example.com', true, '$2a$10$Dkg/6znNnqIgx1jPhdgkIeLgtVh0AGXw0NxPaY8Bi322meJnJSOVK', 'a');
INSERT INTO "user" (id, account_non_expired, account_non_locked, created, credentials_non_expired, email, enabled, password, username) VALUES (nextval('user_id_seq'), true, true, '2019-04-13 14:07:28.254000', true, 'u@example.com', true, '$2a$10$jgYbtOWXWASyo8WPCz31FOwK378Dl3bXF8lSGkz9OCu19lblw5TOe', 'u');

-- assign roles to users
INSERT INTO user_role (user_id, authorities) VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, authorities) VALUES (2, 'USER');
