<?php
use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBrouillonTable extends Migration
{
    public function up()
    {
        Schema::create('brouillon', function (Blueprint $table) {
            $table->id('id_brouillon'); // Clé primaire
            $table->string('email', 50)->unique(); // Email unique
            $table->string('username', 50);
            $table->string('password', 255);
        });
    }

    public function down()
    {
        Schema::dropIfExists('brouillon');
    }
}

