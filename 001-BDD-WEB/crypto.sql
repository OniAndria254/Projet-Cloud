CREATE TABLE portefeuille(
   Id_portefeuille SERIAL,
   solde NUMERIC(15,2)   NOT NULL,
   date_creation DATE NOT NULL,
   id_utilisateur INTEGER NOT NULL,
   PRIMARY KEY(Id_portefeuille)
);

CREATE TABLE cryptomonnaie(
   Id_cryptomonnaie SERIAL,
   nom VARCHAR(50)  NOT NULL,
   symbole VARCHAR(50) ,
   date_creation DATE NOT NULL,
   icon VARCHAR(255) ,
   PRIMARY KEY(Id_cryptomonnaie)
);

CREATE TABLE portefeuille_crypto(
   Id_portefeuille_crypto SERIAL,
   id_utilisateur INTEGER NOT NULL,
   quantite NUMERIC(15,2)   NOT NULL,
   Id_cryptomonnaie INTEGER NOT NULL,
   PRIMARY KEY(Id_portefeuille_crypto),
   FOREIGN KEY(Id_cryptomonnaie) REFERENCES cryptomonnaie(Id_cryptomonnaie)
);

CREATE TABLE type_transaction(
   Id_type_transaction SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(Id_type_transaction)
);

CREATE TABLE statut(
   Id_statut SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(Id_statut)
);

CREATE TABLE transaction_crypto(
   Id_transaction_crypto SERIAL,
   id_utilisateur INTEGER NOT NULL,
   quantite NUMERIC(15,2)   NOT NULL,
   prix_unitaire NUMERIC(15,2)   NOT NULL,
   montant_total NUMERIC(15,2)   NOT NULL,
   date_transaction DATE NOT NULL,
   Id_type_transaction INTEGER NOT NULL,
   Id_cryptomonnaie INTEGER NOT NULL,
   PRIMARY KEY(Id_transaction_crypto),
   FOREIGN KEY(Id_type_transaction) REFERENCES type_transaction(Id_type_transaction),
   FOREIGN KEY(Id_cryptomonnaie) REFERENCES cryptomonnaie(Id_cryptomonnaie)
);

CREATE TABLE historique_cours(
   Id_historique_cours SERIAL,
   prix NUMERIC(15,2)   NOT NULL,
   date_enregistrement DATE NOT NULL,
   Id_cryptomonnaie INTEGER NOT NULL,
   PRIMARY KEY(Id_historique_cours),
   FOREIGN KEY(Id_cryptomonnaie) REFERENCES cryptomonnaie(Id_cryptomonnaie)
);

CREATE TABLE transaction_fonds(
   Id_transaction_fonds SERIAL,
   id_utilisateur INTEGER NOT NULL,
   montant NUMERIC(15,2)   NOT NULL,
   date_transaction DATE NOT NULL,
   token_validation VARCHAR(50) ,
   Id_statut INTEGER NOT NULL,
   Id_type_transaction INTEGER NOT NULL,
   PRIMARY KEY(Id_transaction_fonds),
   FOREIGN KEY(Id_statut) REFERENCES statut(Id_statut),
   FOREIGN KEY(Id_type_transaction) REFERENCES type_transaction(Id_type_transaction)
);
CREATE TABLE commission (
    Id_commission SERIAL,
    Id_cryptomonnaie INTEGER NOT NULL,
    commission_achat NUMERIC(5,2) NOT NULL,
    commission_vente NUMERIC(5,2) NOT NULL,
    date_modification TIMESTAMP NOT NULL,
    PRIMARY KEY(Id_commission),
    FOREIGN KEY(Id_cryptomonnaie) REFERENCES cryptomonnaie(Id_cryptomonnaie)
); 

SELECT
    MIN(quantite) AS min_quantite,
    MAX(quantite) AS max_quantite,
    AVG(quantite) AS moyenne_quantite,
    PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY quantite) AS premier_quartile,
    STDDEV(quantite) AS ecart_type
FROM
    transaction_crypto
WHERE
    Id_cryptomonnaie = 1
    AND date_transaction BETWEEN '2025-02-01' AND '2025-02-30';

SELECT
    MIN(quantite) AS min_quantite,
    MAX(quantite) AS max_quantite,
    AVG(quantite) AS moyenne_quantite,
    PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY quantite) AS premier_quartile,
    STDDEV(quantite) AS ecart_type
FROM
    transaction_crypto;

SELECT
    MIN(quantite) AS min_quantite,
    MAX(quantite) AS max_quantite,
    AVG(quantite) AS moyenne_quantite,
    PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY quantite) AS premier_quartile,
    STDDEV(quantite) AS ecart_type
FROM
    transaction_crypto
WHERE
    Id_cryptomonnaie = 1;


SELECT 
    id_utilisateur,
    SUM(CASE WHEN Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombre_achats,
    SUM(CASE WHEN Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombre_ventes
FROM 
    transaction_crypto
GROUP BY 
    id_utilisateur;

SELECT 
    t.id_utilisateur,
    SUM(CASE WHEN t.Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombre_achats,
    SUM(CASE WHEN t.Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombre_ventes,
    COALESCE(SUM(CASE 
                    WHEN f.Id_type_transaction = 1 AND f.Id_statut = 2 THEN f.montant 
                    WHEN f.Id_type_transaction = 2 AND f.Id_statut = 2 THEN -f.montant 
                    ELSE 0 
                 END), 0) AS valeur_porte_feuille
FROM 
    transaction_crypto t
LEFT JOIN 
    transaction_fonds f ON t.id_utilisateur = f.id_utilisateur
GROUP BY 
    t.id_utilisateur;


SELECT 
    t.id_utilisateur,
    SUM(CASE WHEN t.Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombre_achats,
    SUM(CASE WHEN t.Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombre_ventes,
    COALESCE(SUM(CASE 
                    WHEN f.Id_type_transaction = 1 AND f.Id_statut = 2 THEN f.montant 
                    WHEN f.Id_type_transaction = 2 AND f.Id_statut = 2 THEN -f.montant 
                    ELSE 0 
                 END), 0) 
    + COALESCE(SUM(CASE 
                    WHEN t.Id_type_transaction = 4 THEN t.montant_total 
                    WHEN t.Id_type_transaction = 3 THEN -t.montant_total 
                    ELSE 0 
                 END), 0) AS valeur_porte_feuille
FROM 
    transaction_crypto t
LEFT JOIN 
    transaction_fonds f ON t.id_utilisateur = f.id_utilisateur
GROUP BY 
    t.id_utilisateur;

WITH fonds_aggreg AS (
    SELECT 
        id_utilisateur,
        COALESCE(SUM(CASE 
                        WHEN Id_type_transaction = 1 AND Id_statut = 2 THEN montant 
                        WHEN Id_type_transaction = 2 AND Id_statut = 2 THEN -montant 
                        ELSE 0 
                     END), 0) AS valeur_fonds
    FROM transaction_fonds
    GROUP BY id_utilisateur
)

SELECT 
    t.id_utilisateur,
    SUM(CASE WHEN t.Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombre_achats,
    SUM(CASE WHEN t.Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombre_ventes,
    COALESCE(fa.valeur_fonds, 0) 
    + SUM(CASE 
            WHEN t.Id_type_transaction = 4 THEN t.montant_total  -- Ajoute le montant des ventes
            WHEN t.Id_type_transaction = 3 THEN -t.montant_total -- Soustrait le montant des achats
            ELSE 0 
         END) AS valeur_porte_feuille
FROM transaction_crypto t
LEFT JOIN fonds_aggreg fa ON t.id_utilisateur = fa.id_utilisateur
GROUP BY t.id_utilisateur, fa.valeur_fonds;

WITH fonds_aggreg AS (
    SELECT 
        id_utilisateur,
        COALESCE(SUM(CASE 
                        WHEN Id_type_transaction = 1 AND Id_statut = 2 THEN montant 
                        WHEN Id_type_transaction = 2 AND Id_statut = 2 THEN -montant 
                        ELSE 0 
                     END), 0) AS valeur_fonds
    FROM transaction_fonds
    WHERE date_transaction <= '2025-03-06 23:59:59'  -- Remplacez par la date limite souhaitée
    GROUP BY id_utilisateur
)

SELECT 
    t.id_utilisateur,
    SUM(CASE WHEN t.Id_type_transaction = 3 THEN 1 ELSE 0 END) AS nombre_achats,
    SUM(CASE WHEN t.Id_type_transaction = 4 THEN 1 ELSE 0 END) AS nombre_ventes,
    COALESCE(fa.valeur_fonds, 0) 
    + SUM(CASE 
            WHEN t.Id_type_transaction = 4 THEN t.montant_total  -- Ajoute le montant des ventes
            WHEN t.Id_type_transaction = 3 THEN -t.montant_total -- Soustrait le montant des achats
            ELSE 0 
         END) AS valeur_porte_feuille
FROM transaction_crypto t
LEFT JOIN fonds_aggreg fa ON t.id_utilisateur = fa.id_utilisateur
WHERE t.date_transaction <= '2025-03-06 23:59:59'  -- Remplacez par la date limite souhaitée
GROUP BY t.id_utilisateur, fa.valeur_fonds;

