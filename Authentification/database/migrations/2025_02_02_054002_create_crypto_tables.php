<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('portefeuille', function (Blueprint $table) {
            $table->id('id_portefeuille');
            $table->decimal('solde', 15, 2);
            $table->dateTime('date_creation');
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
            $table->unsignedBigInteger('id_utilisateur');
            $table->foreign('id_utilisateur')->references('id_users')->on('users');
        });

        Schema::create('cryptomonnaie', function (Blueprint $table) {
            $table->id('id_cryptomonnaie');
            $table->string('nom', 50);
            $table->string('symbole', 50)->nullable();
            $table->dateTime('date_creation');
            $table->string('icon', 255)->nullable();
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
        });

        Schema::create('portefeuille_crypto', function (Blueprint $table) {
            $table->id('id_portefeuille_crypto');
            $table->unsignedBigInteger('id_utilisateur');
            $table->decimal('quantite', 15, 2);
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
            $table->unsignedBigInteger('id_cryptomonnaie');
            $table->foreign('id_utilisateur')->references('id_users')->on('users');
            $table->foreign('id_cryptomonnaie')->references('id_cryptomonnaie')->on('cryptomonnaie');
        });

        Schema::create('type_transaction', function (Blueprint $table) {
            $table->id('id_type_transaction');
            $table->string('nom', 50);
        });

        Schema::create('statut', function (Blueprint $table) {
            $table->id('id_statut');
            $table->string('nom', 50);
        });

        Schema::create('transaction_crypto', function (Blueprint $table) {
            $table->id('id_transaction_crypto');
            $table->unsignedBigInteger('id_utilisateur');
            $table->decimal('quantite', 15, 2);
            $table->decimal('prix_unitaire', 15, 2);
            $table->decimal('montant_total', 15, 2);
            $table->dateTime('date_transaction');
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
            $table->unsignedBigInteger('id_type_transaction');
            $table->unsignedBigInteger('id_cryptomonnaie');
            $table->foreign('id_utilisateur')->references('id_users')->on('users');
            $table->foreign('id_type_transaction')->references('id_type_transaction')->on('type_transaction');
            $table->foreign('id_cryptomonnaie')->references('id_cryptomonnaie')->on('cryptomonnaie');
        });

        Schema::create('historique_cours', function (Blueprint $table) {
            $table->id('id_historique_cours');
            $table->decimal('prix', 15, 2);
            $table->dateTime('date_enregistrement');
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
            $table->unsignedBigInteger('id_cryptomonnaie');
            $table->foreign('id_cryptomonnaie')->references('id_cryptomonnaie')->on('cryptomonnaie');
        });

        Schema::create('transaction_fonds', function (Blueprint $table) {
            $table->id('id_transaction_fonds');
            $table->unsignedBigInteger('id_utilisateur');
            $table->decimal('montant', 15, 2);
            $table->dateTime('date_transaction');
            $table->string('token_validation', 50)->nullable();
            $table->boolean('is_sync_from_firestore')->default(false); // Synchronisation avec Firestore
            $table->unsignedBigInteger('id_statut');
            $table->unsignedBigInteger('id_type_transaction');
            $table->foreign('id_statut')->references('id_statut')->on('statut');
            $table->foreign('id_utilisateur')->references('id_users')->on('users');
            $table->foreign('id_type_transaction')->references('id_type_transaction')->on('type_transaction');
        });

        Schema::create('commission', function (Blueprint $table) {
            $table->id('id_commission');
            $table->unsignedBigInteger('id_cryptomonnaie');
            $table->decimal('commission_achat', 5, 2);
            $table->decimal('commission_vente', 5, 2);
            $table->dateTime('date_modification');
            $table->foreign('id_cryptomonnaie')->references('id_cryptomonnaie')->on('cryptomonnaie');
        });

        Schema::create('cryptofav', function (Blueprint $table) {
            $table->id('id_cryptoofav');;
            $table->unsignedBigInteger('id_cryptomonnaie');
            $table->unsignedBigInteger('id_utilisateur');
            $table->dateTime('date_ajout');
            $table->foreign('id_utilisateur')->references('id_users')->on('users');
            $table->foreign('id_cryptomonnaie')->references('id_cryptomonnaie')->on('cryptomonnaie');
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('commission');
        Schema::dropIfExists('transaction_fonds');
        Schema::dropIfExists('historique_cours');
        Schema::dropIfExists('transaction_crypto');
        Schema::dropIfExists('statut');
        Schema::dropIfExists('type_transaction');
        Schema::dropIfExists('portefeuille_crypto');
        Schema::dropIfExists('cryptomonnaie');
        Schema::dropIfExists('portefeuille');
    }
};
