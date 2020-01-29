-- roles
INSERT INTO role (name)
VALUES ('ADMIN'),
       ('USER');

-- users
INSERT INTO "user" (id, email, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES (nextval('user_id_seq'), 'a@example.com', 'a', '$2a$10$Dkg/6znNnqIgx1jPhdgkIeLgtVh0AGXw0NxPaY8Bi322meJnJSOVK', true, true, true, true),
       (nextval('user_id_seq'), 'u@example.com', 'u', '$2a$10$jgYbtOWXWASyo8WPCz31FOwK378Dl3bXF8lSGkz9OCu19lblw5TOe', true, true, true, true);

-- assign roles to users
INSERT INTO user_role (user_id, role)
VALUES ((select id from "user" where username = 'a'), 'ADMIN'),
       ((select id from "user" where username = 'u'), 'USER');
