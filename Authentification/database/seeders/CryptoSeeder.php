<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class CryptoSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        // Insérer les rôles
        DB::table('roles')->insert([
            ['id_role' => 1, 'nom' => 'Admin'],
            ['id_role' => 2, 'nom' => 'User'],
        ]);

        // Insérer les types de transaction
        DB::table('type_transaction')->insert([
            ['nom' => 'depot'],
            ['nom' => 'retrait'],
            ['nom' => 'achat'],
            ['nom' => 'vente'],
        ]);

        // Insérer les statuts
        DB::table('statut')->insert([
            ['nom' => 'en attente'],
            ['nom' => 'accepte'],
            ['nom' => 'refuse'],
        ]);

        // Insérer les cryptomonnaies
        DB::table('cryptomonnaie')->insert([
            ['nom' => 'Bitcoin', 'symbole' => 'BTC', 'date_creation' => '2009-01-03', 'icon' => 'BTC.png'],
            ['nom' => 'Ethereum', 'symbole' => 'ETH', 'date_creation' => '2015-07-30', 'icon' => 'ETH.png'],
            ['nom' => 'Ripple', 'symbole' => 'XRP', 'date_creation' => '2012-01-01', 'icon' => 'XRP.png'],
            ['nom' => 'Litecoin', 'symbole' => 'LTC', 'date_creation' => '2011-10-07', 'icon' => 'LTC.png'],
            ['nom' => 'Cardano', 'symbole' => 'ADA', 'date_creation' => '2017-09-29', 'icon' => 'ADA.png'],
            ['nom' => 'Polkadot', 'symbole' => 'DOT', 'date_creation' => '2020-05-26', 'icon' => 'DOT.png'],
            ['nom' => 'Chainlink', 'symbole' => 'LINK', 'date_creation' => '2017-09-19', 'icon' => 'LINK.png'],
            ['nom' => 'Stellar', 'symbole' => 'XLM', 'date_creation' => '2014-07-31', 'icon' => 'XLM.png'],
            ['nom' => 'Dogecoin', 'symbole' => 'DOGE', 'date_creation' => '2013-12-06', 'icon' => 'DOGE.png'],
            ['nom' => 'Binance Coin', 'symbole' => 'BNB', 'date_creation' => '2017-07-25', 'icon' => 'BNB.png'],
            ['nom' => 'Tron', 'symbole' => 'TRX', 'date_creation' => '2017-09-13', 'icon' => 'TRX.png'],
            ['nom' => 'Enjin', 'symbole' => 'ENJ', 'date_creation' => '2017-11-01', 'icon' => 'ENJ.png'],
        ]);

        // Liste des utilisateurs avec leurs rôles
        $users = [
            ['username' => 'Mercia', 'email' => 'bousoldekaetmi@gmail.com', 'password' => '123456', 'id_role' => 2],
            ['username' => 'admin', 'email' => 'admin@gmail.com', 'password' => 'admin123', 'id_role' => 1],
            ['username' => 'user1', 'email' => 'user1@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user2', 'email' => 'user2@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user3', 'email' => 'user3@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user4', 'email' => 'user4@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user5', 'email' => 'user5@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user6', 'email' => 'user6@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user7', 'email' => 'user7@gmail.com', 'password' => 'user123', 'id_role' => 2],
            ['username' => 'user8', 'email' => 'user8@gmail.com', 'password' => 'user123', 'id_role' => 2],
        ];

        // Insérer l'historique des cours
        $cours = [
            [1, 50000.00, '2023-10-01'], [1, 51000.00, '2023-10-02'], [1, 52000.00, '2023-10-03'], // BTC
            [2, 3000.00, '2023-10-01'], [2, 3100.00, '2023-10-02'], [2, 3200.00, '2023-10-03'], // ETH
            [3, 1.00, '2023-10-01'], [3, 1.10, '2023-10-02'], [3, 1.20, '2023-10-03'], // XRP
            [4, 150.00, '2023-10-01'], [4, 155.00, '2023-10-02'], [4, 160.00, '2023-10-03'], // LTC
            [5, 2.50, '2023-10-01'], [5, 2.60, '2023-10-02'], [5, 2.70, '2023-10-03'], // ADA
            [6, 25.00, '2023-10-01'], [6, 26.00, '2023-10-02'], [6, 27.00, '2023-10-03'], // DOT
            [7, 20.00, '2023-10-01'], [7, 21.00, '2023-10-02'], [7, 22.00, '2023-10-03'], // LINK
            [8, 0.30, '2023-10-01'], [8, 0.31, '2023-10-02'], [8, 0.32, '2023-10-03'], // XLM
            [9, 0.20, '2023-10-01'], [9, 0.21, '2023-10-02'], [9, 0.22, '2023-10-03'], // DOGE
            [10, 400.00, '2023-10-01'], [10, 410.00, '2023-10-02'], [10, 420.00, '2023-10-03'], // BNB
            [11, 0.10, '2023-10-01'], [11, 0.11, '2023-10-02'], [11, 0.12, '2023-10-03'], // TRX
            [12, 2.00, '2023-10-01'], [12, 2.10, '2023-10-02'], [12, 2.20, '2023-10-03'], // ENJ
        ];

        foreach ($cours as $data) {
            DB::table('historique_cours')->insert([
                'id_cryptomonnaie' => $data[0],
                'prix' => $data[1],
                'date_enregistrement' => $data[2],
            ]);
        }

        foreach ($users as $user) {
            // Insérer une tentative pour chaque utilisateur et récupérer son ID unique
            $idTentative = DB::table('tentatives')->insertGetId([
                'tentatives' => 0,
            ], 'id_tentatives');

            // Insérer l'utilisateur avec son id_tentatives unique
            DB::table('users')->insert([
                'username' => $user['username'],
                'email' => $user['email'],
                'password' => bcrypt($user['password']),
                'id_tentatives' => $idTentative,
                'id_role' => $user['id_role'],
            ]);
        }   
    }
}
