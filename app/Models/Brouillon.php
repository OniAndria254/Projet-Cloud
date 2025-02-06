<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Brouillon extends Model
{
    use HasFactory;

    protected $table = 'brouillon';

    // Spécifiez la clé primaire et son nom en minuscules
    protected $primaryKey = 'id_brouillon';

    protected $fillable = [
        'email',
        'username',
        'password',
    ];

    public $timestamps = false;

    // Ajoutez cette ligne si votre clé primaire n'est pas un entier auto-incrémenté
    public $incrementing = true;

    // Si nécessaire, indiquez le type de la clé primaire
    protected $keyType = 'int';
}
