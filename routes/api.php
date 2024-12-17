<?php

use App\Http\Controllers\AuthController;
use App\Models\Brouillon;
use App\Models\Tentatives;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/register', [AuthController::class, 'register']);
Route::get('/validate-email/{token}', [AuthController::class, 'validateEmail'])->name('validate-email');


Route::get('/validate', function (Request $request) {
    $token = $request->query('token');

    if (!$token) {
        return response()->json(['message' => 'Token manquant.'], 400);
    }

    // Décoder le token
    [$email, $timestamp] = explode('|', base64_decode($token));

    // Vérifier si l'email existe dans la table brouillon
    $brouillon = Brouillon::where('email', $email)->first();

    if (!$brouillon) {
        return response()->json(['message' => 'Lien de validation invalide ou expiré.'], 404);
    }

    $tentative = Tentatives::create([
        'tentatives' => 3  // Par exemple, vous définissez la valeur de tentatives à 3
    ]);
    
    // 2. Insérer une ligne dans la table users avec id_tentatives
    $user = User::create([
        'email' => $brouillon->email,
        'username' => $brouillon->username,
        'password' => $brouillon->password, // Le mot de passe est déjà hashé
        'id_tentatives' => 1  // Ajouter l'ID de la tentative
    ]);

    // Supprimer le brouillon
    $brouillon->delete();

    return response()->json(['message' => 'Inscription validée avec succès.'], 200);
});