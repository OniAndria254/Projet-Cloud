<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

class CreateUsersTable extends Migration
{
    public function up()
    {
        // Création de la table roles
        Schema::create('roles', function (Blueprint $table) {
            $table->id('id_role'); // Clé primaire
            $table->string('nom', 50); // Nom du rôle
        });

        // Création de la table users
        Schema::create('users', function (Blueprint $table) {
            $table->id('id_users'); // Clé primaire
            $table->string('email', 50)->unique();
            $table->string('username', 50);
            $table->string('password', 255);
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
            $table->string('avatar', 255)->default('avatar.png');;

            // Clé étrangère pour tentatives
            $table->unsignedBigInteger('id_tentatives');
            $table->foreign('id_tentatives')->references('id_tentatives')->on('tentatives');

            // Clé étrangère pour le rôle
            $table->unsignedBigInteger('id_role');
            $table->foreign('id_role')->references('id_role')->on('roles');
        });
    }

    public function down()
    {
        // Suppression de la table users
        Schema::dropIfExists('users');

        // Suppression de la table roles
        Schema::dropIfExists('roles');
    }
}
