-- Insertion dans la table statut
INSERT INTO statut (nom) VALUES 
(1), 
(2), 
(3);

-- Insertion dans la table listecrypto
INSERT INTO listecrypto (nom, montant) VALUES 
('Bitcoin', '1000.00'), 
('Ethereum', '500.00'), 
('Litecoin', '200.00');

-- Insertion dans la table cours
INSERT INTO cours (idCrypto, daty, variation) VALUES 
(1, '2024-12-01', 5.23), 
(2, '2024-12-01', -3.10), 
(3, '2024-12-01', 2.50);

-- Insertion dans la table Fond
INSERT INTO Fond (statut, idUtilisateur, daty, montant) VALUES 
(1, 101, '2024-12-01', 10000.50), 
(2, 102, '2024-12-01', 5000.00), 
(3, 103, '2024-12-01', 2500.75);
