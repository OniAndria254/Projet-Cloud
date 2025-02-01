<?php
use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateConfigTable extends Migration
{
    public function up()
    {
        Schema::create('config', function (Blueprint $table) {
            $table->id('id_config'); // Clé primaire
            $table->integer('compteur'); // Compteur obligatoire
            $table->integer('dureePIN'); // Durée obligatoire
        });
    }

    public function down()
    {
        Schema::dropIfExists('config');
    }
}
