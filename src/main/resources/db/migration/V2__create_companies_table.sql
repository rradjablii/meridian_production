CREATE TABLE companies (
                           id UUID PRIMARY KEY,
                           company_name VARCHAR(255) NOT NULL UNIQUE,
                           company_tin VARCHAR(10) NOT NULL UNIQUE CHECK (company_tin ~ '^[0-9]{10}$'),
                           company_address VARCHAR(255) NOT NULL,
                           company_phone VARCHAR(16) NOT NULL UNIQUE,
                           registered_date TIMESTAMP WITH TIME ZONE NOT NULL,
                           representative_id UUID NOT NULL UNIQUE
);