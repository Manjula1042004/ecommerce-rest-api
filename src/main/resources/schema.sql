-- Create table first
CREATE TABLE IF NOT EXISTS items (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price DOUBLE NOT NULL,
    quantity INTEGER NOT NULL,
    category VARCHAR(100) NOT NULL,
    brand VARCHAR(100),
    rating DOUBLE,
    image_url VARCHAR(500),
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);