-- Insertar un usuario
INSERT INTO public.users (name, email, password, credit_card_type, credit_card_number)
VALUES ('Juan Pérez', 'juan@example.com', '123456', 'Visa', '4111111111111111');

-- Insertar un pedido para ese usuario
INSERT INTO public.orders (email, total_amount)
VALUES ('juan@example.com', 199.99);

-- Consultar todos los pedidos con información del usuario
SELECT 
    o.order_id,
    u.name,
    u.email,
    o.total_amount,
    o.order_date
FROM 
    public.orders o
JOIN 
    public.users u ON o.email = u.email;