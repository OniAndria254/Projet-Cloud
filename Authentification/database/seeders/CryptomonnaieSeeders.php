<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class CryptomonnaieSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('cryptomonnaie')->insert([
            [
                'nom' => 'Bitcoin',
                'symbole' => 'BTC',
                'date_creation' => '2009-01-03',
                'icon' => 'BTC.png',
            ],
            [
                'nom' => 'Ethereum',
                'symbole' => 'ETH',
                'date_creation' => '2015-07-30',
                'icon' => 'ETH.png',
            ],
            [
                'nom' => 'Ripple',
                'symbole' => 'XRP',
                'date_creation' => '2012-01-01',
                'icon' => 'XRP.png',
            ],
            [
                'nom' => 'Litecoin',
                'symbole' => 'LTC',
                'date_creation' => '2011-10-07',
                'icon' => 'LTC.png',
            ],
            [
                'nom' => 'Cardano',
                'symbole' => 'ADA',
                'date_creation' => '2017-09-29',
                'icon' => 'ADA.png',
            ],
            [
                'nom' => 'Polkadot',
                'symbole' => 'DOT',
                'date_creation' => '2020-05-26',
                'icon' => 'DOT.png',
            ],
            [
                'nom' => 'Chainlink',
                'symbole' => 'LINK',
                'date_creation' => '2017-09-19',
                'icon' => 'LINK.png',
            ],
            [
                'nom' => 'Stellar',
                'symbole' => 'XLM',
                'date_creation' => '2014-07-31',
                'icon' => 'XLM.png',
            ],
            [
                'nom' => 'Dogecoin',
                'symbole' => 'DOGE',
                'date_creation' => '2013-12-06',
                'icon' => 'DOGE.png',
            ],
            [
                'nom' => 'Binance Coin',
                'symbole' => 'BNB',
                'date_creation' => '2017-07-25',
                'icon' => 'BNB.png',
            ],
            [
                'nom' => 'Tron',
                'symbole' => 'TRX',
                'date_creation' => '2017-09-13',
                'icon' => 'TRX.png',
            ],
            [
                'nom' => 'Enjin',
                'symbole' => 'ENJ',
                'date_creation' => '2017-11-01',
                'icon' => 'ENJ.png',
            ],
        ]);
    }
}