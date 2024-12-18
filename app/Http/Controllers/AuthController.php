<?php

namespace App\Http\Controllers;

use App\Models\Brouillon;
use App\Models\Tentatives;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Mail;

class AuthController extends Controller
{
    public function register(Request $request)
    {
        // Valider les données entrantes
        $validatedData = $request->validate([
            'email' => 'required|email|unique:brouillon,email',
            'username' => 'required|string|max:50',
            'password' => 'required|string|min:6',
        ]);

        // Insérer les données dans la table brouillon
        $brouillon = Brouillon::create([
            'email' => $validatedData['email'],
            'username' => $validatedData['username'],
            'password' => bcrypt($validatedData['password']), // Toujours hasher le mot de passe
        ]);

        // Générer un token unique pour le lien de validation
        $validationToken = base64_encode($brouillon->email . '|' . now());

        // Envoi de l'email
        Mail::send('emails.validation', ['token' => $validationToken], function ($message) use ($brouillon) {
            $message->to($brouillon->email)
                    ->subject('Validation de votre inscription');
        });

        return response()->json([
            'message' => 'Un email de validation a été envoyé.',
        ], 201);
    }


    public function validateEmail(Request $request)
    {
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

        // Créer une tentative dans la table Tentatives
        $tentative = Tentatives::create([
            'tentatives' => 0 // Valeur par défaut
        ]);

        // Insérer l'utilisateur dans la table Users
        $user = User::create([
            'email' => $brouillon->email,
            'username' => $brouillon->username,
            'password' => $brouillon->password, // Le mot de passe est déjà hashé
            'id_tentatives' => $tentative->id_tentatives // Associer l'ID de la tentative
        ]);

        // Authentifier l'utilisateur
        Auth::login($user);

        // Supprimer le brouillon
        $brouillon->delete();

        return response()->json(['message' => 'Inscription validée avec succès.'], 200);
    }


}
