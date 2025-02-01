<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class MfaToken extends Model
{
    protected $fillable = ['user_id', 'token', 'expires_at'];

    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class);
    }

    // Vérifie si le token est encore valide
    public function isValid(): bool
    {
        return $this->expires_at > now();
    }
}
