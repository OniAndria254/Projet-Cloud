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
