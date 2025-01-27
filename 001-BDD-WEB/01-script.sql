drop database springsolo;
create database springsolo;
\c springsolo;

CREATE TABLE Fond(
   idFond SERIAL,
   statut INTEGER,
   idUtilisateur INTEGER,
   daty DATE,
   montant NUMERIC(15,2)  ,
   PRIMARY KEY(idFond)
);

CREATE TABLE statut(
   idStatut SERIAL,
   nom INTEGER,
   PRIMARY KEY(idStatut)
);

CREATE TABLE listecrypto(
   idCrypto SERIAL,
   nom VARCHAR(50) ,
   montant VARCHAR(50) ,
   PRIMARY KEY(idCrypto)
);

CREATE TABLE cours(
   idCours SERIAL,
   idCrypto INTEGER,
   daty VARCHAR(50) ,
   variation NUMERIC(15,2)  ,
   PRIMARY KEY(idCours)
);
