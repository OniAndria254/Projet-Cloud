# Utiliser une image PHP 8.2 avec Apache
FROM php:8.2-apache

# Installer les dépendances nécessaires
RUN apt-get update && apt-get install -y \
    libpq-dev \
    zip \
    unzip \
    git \
    && docker-php-ext-install pdo pdo_pgsql

# Copier le contenu de votre application dans le conteneur
COPY . /var/www/html

# Installer Composer
COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Définit la variable d'environnement pour autoriser Composer à s'exécuter en tant que root
ENV COMPOSER_ALLOW_SUPERUSER 1

# Exécuter l'installation des dépendances Composer
RUN composer install

# Définir le répertoire de travail
WORKDIR /var/www/html

# Définir les permissions appropriées
RUN chown -R www-data:www-data /var/www/html \
    && a2enmod rewrite

# Copier le fichier de configuration Apache
COPY ./docker/apache/vhost.conf /etc/apache2/sites-available/000-default.conf

# Copier les scripts d'initialisation
COPY init-app.sh /usr/local/bin/
COPY wait-for-it.sh /usr/local/bin/

# Rendre les scripts exécutables
RUN chmod +x /usr/local/bin/init-app.sh /usr/local/bin/wait-for-it.sh

# Exposer le port 80
EXPOSE 80

# Commande pour démarrer Apache et exécuter le script d'initialisation
CMD ["bash", "-c", "/usr/local/bin/init-app.sh && apache2-foreground"]