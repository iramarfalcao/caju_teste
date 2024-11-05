CREATE TABLE account (
    account_id VARCHAR(255) PRIMARY KEY,
    food_balance NUMERIC(38, 2),
    meal_balance NUMERIC(38, 2),
    cash_balance NUMERIC(38, 2)
);

CREATE TABLE transaction (
    id UUID PRIMARY KEY,
    account_id VARCHAR(255) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    mcc VARCHAR(4) NOT NULL,
    merchant VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE
);

INSERT INTO account (account_id, food_balance, meal_balance, cash_balance)
VALUES ('123', 100.00, 50.00, 200.00);

INSERT INTO account (account_id, food_balance, meal_balance, cash_balance)
VALUES ('124', 100.00, 500.00, 200.00);

INSERT INTO account (account_id, food_balance, meal_balance, cash_balance)
VALUES ('125', 100.00, 100.00, 1000.00);