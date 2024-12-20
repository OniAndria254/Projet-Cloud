<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Tentatives extends Model
{
    use HasFactory;

    protected $table = 'tentatives';

    protected $primaryKey = 'id_tentatives';

    protected $fillable = [
        'tentatives',
    ];

    public $timestamps = false;

    /**
     * Relation avec Users.
     */
    public function users()
    {
        return $this->hasMany(User::class, 'id_tentatives', 'id_tentatives');
    }
}
