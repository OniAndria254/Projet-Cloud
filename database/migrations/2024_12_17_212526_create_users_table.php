<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateUsersTable extends Migration
{
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id('id_users'); // Clé primaire
            $table->string('email', 50)->unique();
            $table->string('username', 50);
            $table->string('password', 255);
            
            // Spécifier que la clé étrangère correspond à 'id_tentatives' dans la table 'tentatives'
            $table->unsignedBigInteger('id_tentatives');
            $table->foreign('id_tentatives')->references('id_tentatives')->on('tentatives');
        });
    }

    public function down()
    {
        Schema::dropIfExists('users');
    }
}
