#!/bin/bash
set -e

# Attendre que PostgreSQL soit prêt
/usr/local/bin/wait-for-it.sh db:5432 -- echo "PostgreSQL est prêt"

# Exécuter les migrations et les seeders
php artisan migrate --force
php artisan db:seed --force