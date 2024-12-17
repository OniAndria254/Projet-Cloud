<?php

namespace App\Http\Controllers;

use App\Models\Brouillon;
use App\Models\User;
use Illuminate\Http\Request;
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


    public function validateEmail($token)
    {
        $email = base64_decode($token);

        // Validation fictive (vous pourriez ajouter d'autres vérifications ici)
        return response()->json([
            'message' => "L'email $email a été validé avec succès."
        ], 200);
    }


}
