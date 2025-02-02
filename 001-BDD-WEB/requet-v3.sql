SELECT
    MIN(quantite) AS min_quantite,
    MAX(quantite) AS max_quantite,
    AVG(quantite) AS moyenne_quantite,
    PERCENTILE_CONT(0.25) WITHIN GROUP (ORDER BY quantite) AS premier_quartile,
    STDDEV(quantite) AS ecart_type
FROM
    transaction_crypto
WHERE
    Id_cryptomonnaie = :idCrypto
    AND date_transaction BETWEEN :dateMin AND :dateMax;


SELECT
    SUM(commission_achat) AS somme_commission_achat,
    AVG(commission_achat) AS moyenne_commission_achat,
    SUM(commission_vente) AS somme_commission_vente,
    AVG(commission_vente) AS moyenne_commission_vente
FROM
    commission
WHERE
    Id_cryptomonnaie = :idCrypto
    AND date_modification BETWEEN :dateMin AND :dateMax;