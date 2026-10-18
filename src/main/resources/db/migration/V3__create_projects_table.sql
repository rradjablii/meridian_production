CREATE TABLE projects (
                          id UUID PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                          company_id UUID NOT NULL,
                          freelancer_id UUID NOT NULL
);