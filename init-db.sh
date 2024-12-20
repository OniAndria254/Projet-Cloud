#!/bin/bash
set -e

# Ajoutez ici les commandes pour insérer les configurations par défaut
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    INSERT INTO config VALUES (1, 3, 90);
EOSQL