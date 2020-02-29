CREATE TABLE compra (
    id INTEGER PRIMARY KEY,
    nick TEXT NOT NULL,
    productos TEXT NOT NULL,
    pago TEXT NOT NULL,
    grado TEXT NOT NULL,
    cantidad INTEGER DEFAULT 0
);