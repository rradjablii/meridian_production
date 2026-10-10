CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255),
                       surname VARCHAR(255),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone VARCHAR(255) UNIQUE,
                       registered_date TIMESTAMP WITH TIME ZONE NOT NULL
);

INSERT INTO users (
    id,
    name,
    surname,
    email,
    password,
    phone,
    registered_date
)
VALUES
    (
        gen_random_uuid(),
        'John',
        'Smith',
        'john.smith@example.com',
        '$2a$10$REPLACE_WITH_A_REAL_BCRYPT_HASH',
        '+994501112233',
        CURRENT_DATE
    ),
    (
        gen_random_uuid(),
        'Jane',
        'Doe',
        'jane.doe@example.com',
        '$2a$10$REPLACE_WITH_A_REAL_BCRYPT_HASH',
        '+994502223344',
        CURRENT_DATE
    );
