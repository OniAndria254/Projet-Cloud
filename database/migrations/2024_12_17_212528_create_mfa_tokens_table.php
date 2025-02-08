<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('mfa_tokens', function (Blueprint $table) {
            $table->id();
            $table->foreignId('user_id')
                  ->constrained('users', 'id_users') // Spécifie le nom de la colonne clé primaire
                  ->onDelete('cascade');
            $table->string('token');
            $table->timestamp('expires_at');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('mfa_tokens');
    }
};
