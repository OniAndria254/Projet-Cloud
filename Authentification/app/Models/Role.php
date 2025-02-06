<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Role extends Model
{
    use HasFactory;

    protected $table = 'roles';

    // Définir la clé primaire comme étant "idRole"
    protected $primaryKey = 'id_role';

    // Désactiver les timestamps si non utilisés
    public $timestamps = false;

    // Les champs pouvant être assignés massivement
    protected $fillable = ['nom'];

    /**
     * Relation avec le modèle User.
     * Un rôle peut être associé à plusieurs utilisateurs.
     */
    // public function users()
    // {
    //     return $this->hasMany(User::class, 'id_role', 'id_role');
    // }
}
