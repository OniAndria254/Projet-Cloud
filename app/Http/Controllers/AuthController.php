<?php

namespace App\Http\Controllers;

use App\Models\Brouillon;
use App\Models\Tentatives;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Mail;
use App\Models\MfaToken;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\Validator;
use App\Models\Config;

/**
 * @OA\Info(title="Auth API", version="1.0")
 */
class AuthController extends Controller
{
    /**
     * @OA\Post(
     *     path="/register",
     *     summary="Register a new user",
     *     description="Registers a new user and sends a validation email",
     *     operationId="register",
     *     tags={"Auth"},
     *     @OA\RequestBody(
     *         required=true,
     *         @OA\JsonContent(
     *             required={"email", "username", "password"},
     *             @OA\Property(property="email", type="string", format="email", example="user@example.com"),
     *             @OA\Property(property="username", type="string", example="username123"),
     *             @OA\Property(property="password", type="string", format="password", example="password123")
     *         )
     *     ),
     *     @OA\Response(
     *         response=201,
     *         description="Registration successful",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Un email de validation a été envoyé.")
     *         )
     *     ),
     *     @OA\Response(
     *         response=400,
     *         description="Bad request",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Token manquant.")
     *         )
     *     )
     * )
     */
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

    /**
     * @OA\Post(
     *     path="/validate-email",
     *     summary="Validate the user's email",
     *     description="Validates the user's email and converts the brouillon entry to a user",
     *     operationId="validateEmail",
     *     tags={"Auth"},
     *     @OA\RequestBody(
     *         required=true,
     *         @OA\JsonContent(
     *             required={"token"},
     *             @OA\Property(property="token", type="string", example="sample_token")
     *         )
     *     ),
     *     @OA\Response(
     *         response=200,
     *         description="Email successfully validated",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Inscription validée avec succès.")
     *         )
     *     ),
     *     @OA\Response(
     *         response=400,
     *         description="Token manquant",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Token manquant.")
     *         )
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Token invalid or expired",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Lien de validation invalide ou expiré.")
     *         )
     *     )
     * )
     */
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

    /**
     * @OA\Post(
     *     path="/login",
     *     summary="User login",
     *     description="Logs in the user and generates an MFA token",
     *     operationId="login",
     *     tags={"Auth"},
     *     @OA\RequestBody(
     *         required=true,
     *         @OA\JsonContent(
     *             required={"email", "password"},
     *             @OA\Property(property="email", type="string", format="email", example="user@example.com"),
     *             @OA\Property(property="password", type="string", format="password", example="password123")
     *         )
     *     ),
     *     @OA\Response(
     *         response=200,
     *         description="Login successful, MFA token sent",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Connexion réussie. Code MFA envoyé."),
     *             @OA\Property(property="user_id", type="integer", example=1)
     *         )
     *     ),
     *     @OA\Response(
     *         response=422,
     *         description="Validation errors",
     *         @OA\JsonContent(
     *             @OA\Property(property="errors", type="object")
     *         )
     *     )
     * )
     */
    public function login(Request $request)
    {
        // Validation des inputs
        $validator = Validator::make($request->all(), [
            'email' => 'required|email',
            'password' => 'required|string',
        ]);
    
        if ($validator->fails()) {
            return response()->json(['errors' => $validator->errors()], 422);
        }
    
        // Récupération de l'utilisateur par email
        $user = User::where('email', $request->email)->first();
    
        if (!$user || !Hash::check($request->password, $user->password)) {
            return $this->incrementAttempts($request->email, 'Email ou mot de passe incorrect.');
        }
    
        // Succès : Réinitialiser les tentatives
        $this->resetAttempts($user);
    
        // Générer le token MFA
        $pin = Str::random(6);
    
        MfaToken::updateOrCreate(
            ['user_id' => $user->id_users],
            ['token' => $pin, 'expires_at' => now()->addSeconds(90)]
        );
    
        // Envoyer le PIN par email
        Mail::raw("Votre code MFA est : $pin", function ($message) use ($user) {
            $message->to($user->email)
                    ->subject('Votre code de confirmation MFA');
        });
    
        return response()->json([
            'message' => 'Connexion réussie. Code MFA envoyé.',
            'user_id' => $user->id_users, // Tu peux ajouter un identifiant temporaire si nécessaire.
        ]);
    }

    // Les autres méthodes peuvent être annotées de la même manière
}
