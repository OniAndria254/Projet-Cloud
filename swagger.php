<?php
require 'vendor/autoload.php';

use OpenApi\Generator;

// Indiquez le répertoire où se trouvent vos contrôleurs
$openapi = Generator::scan(['app/Http/Controllers']);

// Enregistrez la documentation dans un fichier JSON
file_put_contents('swagger.json', $openapi->toJson());
