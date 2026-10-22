CREATE TABLE audit_logs (
                            request_id UUID PRIMARY KEY,
                            attempt_id UUID,
                            attempt_number INTEGER DEFAULT 0,
                            request_from UUID NOT NULL,
                            request_type VARCHAR(255),
                            request_state VARCHAR(255)
);