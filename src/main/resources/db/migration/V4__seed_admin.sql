INSERT INTO usuarios (nome, email, senha_hash, role)
SELECT 'Admin', 'admin@admin.com', '202cb962ac59075b964b07152d234b70', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM usuarios WHERE email = 'admin@admin.com');

INSERT INTO administradores (id)
SELECT id FROM usuarios WHERE email = 'admin@admin.com'
AND NOT EXISTS (
    SELECT 1 FROM administradores
    WHERE id = (SELECT id FROM usuarios WHERE email = 'admin@admin.com')
);
