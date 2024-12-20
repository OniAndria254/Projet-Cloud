<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class ConfigSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('config')->insert([
            [
                'compteur' => 1,
                'dureePIN' => 3,
            ],
            [
                'compteur' => 90,
                'dureePIN' => 45,
            ],
        ]);
    }
}
