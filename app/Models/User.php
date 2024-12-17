<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class User extends Model
{
    use HasFactory;

    protected $table = 'Users';

    protected $primaryKey = 'Id_Users';

    protected $fillable = [
        'email',
        'username',
        'password',
        'Id_tentatives',
    ];

    public $timestamps = false;

    /**
     * Relation avec Tentatives.
     */
    public function tentatives()
    {
        return $this->belongsTo(Tentatives::class, 'Id_tentatives', 'Id_tentatives');
    }
}
