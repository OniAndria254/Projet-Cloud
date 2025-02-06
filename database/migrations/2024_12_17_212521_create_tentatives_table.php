<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateTentativesTable extends Migration
{
    public function up()
    {
        Schema::create('tentatives', function (Blueprint $table) {
            $table->id('id_tentatives'); // Clé primaire
            $table->integer('tentatives'); // Nombre de tentatives
        });
    }

    public function down()
    {
        Schema::dropIfExists('tentatives');
    }
}

