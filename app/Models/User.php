<?php

namespace App\Models;

use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class User extends Authenticatable
{
    use HasFactory;

    protected $table = 'users';

    protected $primaryKey = 'id_users';

    protected $fillable = [
        'email',
        'username',
        'password',
        'id_tentatives',
    ];

    public $timestamps = false;

    /**
     * Relation avec Tentatives.
     */
    public function tentatives()
    {
        return $this->belongsTo(Tentatives::class, 'id_tentatives', 'id_tentatives');
    }
}
