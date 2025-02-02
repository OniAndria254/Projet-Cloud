INSERT INTO type_transaction (nom) VALUES
('depot'),
('retrait'),
('achat'),
('vente');

update type_transaction set nom = 'depot' where Id_type_transaction = 1;


INSERT INTO statut (nom) VALUES
('en attente'),
('accepte'),
('refuse');

INSERT INTO cryptomonnaie (nom, symbole, date_creation, icon) VALUES
('Bitcoin', 'BTC', '2009-01-03', 'BTC.png'),
('Ethereum', 'ETH', '2015-07-30', 'ETH.png'),
('Ripple', 'XRP', '2012-01-01', 'XRP.png'),
('Litecoin', 'LTC', '2011-10-07', 'LTC.png'),
('Cardano', 'ADA', '2017-09-29', 'ADA.png'),
('Polkadot', 'DOT', '2020-05-26', 'DOT.png'),
('Chainlink', 'LINK', '2017-09-19', 'LINK.png'),
('Stellar', 'XLM', '2014-07-31', 'XLM.png'),
('Dogecoin', 'DOGE', '2013-12-06', 'DOGE.png'),
('Binance Coin', 'BNB', '2017-07-25', 'BNB.png'),
('Tron', 'TRX', '2017-09-13', 'TRX.png'),
('Enjin', 'ENJ', '2017-11-01', 'ENJ.png');


-- Bitcoin (BTC)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(50000.00, '2023-10-01', 1),
(51000.00, '2023-10-02', 1),
(52000.00, '2023-10-03', 1);

-- Ethereum (ETH)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(3000.00, '2023-10-01', 2),
(3100.00, '2023-10-02', 2),
(3200.00, '2023-10-03', 2);

-- Ripple (XRP)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(1.00, '2023-10-01', 3),
(1.10, '2023-10-02', 3),
(1.20, '2023-10-03', 3);

-- Litecoin (LTC)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(150.00, '2023-10-01', 4),
(155.00, '2023-10-02', 4),
(160.00, '2023-10-03', 4);

-- Cardano (ADA)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(2.50, '2023-10-01', 5),
(2.60, '2023-10-02', 5),
(2.70, '2023-10-03', 5);

-- Polkadot (DOT)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(25.00, '2023-10-01', 6),
(26.00, '2023-10-02', 6),
(27.00, '2023-10-03', 6);

-- Chainlink (LINK)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(20.00, '2023-10-01', 7),
(21.00, '2023-10-02', 7),
(22.00, '2023-10-03', 7);

-- Stellar (XLM)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(0.30, '2023-10-01', 8),
(0.31, '2023-10-02', 8),
(0.32, '2023-10-03', 8);

-- Dogecoin (DOGE)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(0.20, '2023-10-01', 9),
(0.21, '2023-10-02', 9),
(0.22, '2023-10-03', 9);

-- Binance Coin (BNB)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(400.00, '2023-10-01', 10),
(410.00, '2023-10-02', 10),
(420.00, '2023-10-03', 10);

-- Tron (TRX)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(0.10, '2023-10-01', 11),
(0.11, '2023-10-02', 11),
(0.12, '2023-10-03', 11);

-- Enjin (ENJ)
INSERT INTO historique_cours (prix, date_enregistrement, Id_cryptomonnaie) VALUES
(2.00, '2023-10-01', 12),
(2.10, '2023-10-02', 12),
(2.20, '2023-10-03', 12);

INSERT INTO utilisateur (nom, email, mot_de_passe)
VALUES ('Jean Dupont', 'jean.dupont@example.com', 'motdepasse123');