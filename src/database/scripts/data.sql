-- country
INSERT INTO country(id, iso_alpha3code, iso_number, name) VALUES (NEXTVAL('country_id_seq'), 'UKR', '803', 'Ukraine');

-- region
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Вінницька область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Волинська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Дніпропетровська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Донецька область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Житомирська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Закарпатська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Запорізька область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Івано-Франківська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Київська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Кіровоградська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Луганська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Львівська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Миколаївська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Одеська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Полтавська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Рівненська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Сумська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Тернопільська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Харківська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Херсонська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Хмельницька область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Черкаська область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Чернівецька область', (SELECT id from country where iso_alpha3code = 'UKR'));
INSERT INTO region (id, name, country_id) VALUES (NEXTVAL('region_id_seq'), 'Чернігівська область', (SELECT id from country where iso_alpha3code = 'UKR'));

-- subregion
INSERT INTO region (id, name, country_id, parent_region_id) VALUES (NEXTVAL('region_id_seq'), 'Кагарлицький район', (SELECT id from country where iso_alpha3code = 'UKR'), (SELECT id from region where name = 'Київська область'));
INSERT INTO region (id, name, country_id, parent_region_id) VALUES (NEXTVAL('region_id_seq'), 'Червоноармійський район', (SELECT id from country where iso_alpha3code = 'UKR'), (SELECT id from region where name = 'Житомирська область'));

-- address
INSERT INTO address (id, type, zip_code, city, street, latitude, longitude, info, country_id, region_id, sub_region_id)
VALUES (
    NEXTVAL('address_id_seq'),
    'G',
    null,
    'м. Кагарлик',
    'вул. Лісова 2',
    49.875233,
    30.837870,
    'Кагарлицький район',
    (SELECT id from country where iso_alpha3code = 'UKR'),
    (SELECT id from region where name = 'Київська область'),
    (SELECT id from region where name = 'Кагарлицький район')
);
INSERT INTO address (id, type, zip_code, city, street, latitude, longitude, info, country_id, region_id, sub_region_id)
VALUES (
     NEXTVAL('address_id_seq'),
    'G',
    null,
    'смт. Червоноармійськ',
    'вул. Манзенка 12',
    50.58001,
    28.15266,
     null,
    (SELECT id from country where iso_alpha3code = 'UKR'),
    (SELECT id from region where name = 'Житомирська область'),
    (SELECT id from region where name = 'Червоноармійський район')
);
INSERT INTO address (id, type)
VALUES (
    NEXTVAL('address_id_seq'),
    'G'
);

