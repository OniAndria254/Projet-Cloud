<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Brouillon;
use App\Models\Config;
use App\Models\Tentatives;
use App\Models\User;

class firstController extends Controller
{
    public function updateBrouillon(Request $request, $id)
    {
        $brouillon = Brouillon::findOrFail($id);

        $brouillon->update([
            'email' => $request->input('email'),
            'username' => $request->input('username'),
            'password' => bcrypt($request->input('password')), // Hash le mot de passe
        ]);

        return response()->json(['message' => 'Brouillon mis à jour avec succès', 'data' => $brouillon], 200);
    }

    /**
     * @OA\Put(
     *     path="/api/config",
     *     summary="Mettre à jour la configuration",
     *     description="Met à jour les paramètres de configuration. Cette méthode met à jour la configuration avec un ID fixe (1).",
     *     tags={"Config"},
     *     @OA\RequestBody(
     *         required=true,
     *         @OA\JsonContent(
     *             required={"compteur", "dureePIN"},
     *             @OA\Property(property="compteur", type="integer", description="Nouvelle valeur du compteur"),
     *             @OA\Property(property="dureePIN", type="integer", description="Nouvelle durée PIN")
     *         )
     *     ),
     *     @OA\Response(
     *         response=200,
     *         description="Configuration mise à jour avec succès",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Configuration mise à jour avec succès"),
     *             @OA\Property(property="data", type="object", description="Détails de la configuration mise à jour")
     *         )
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Configuration non trouvée",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string", example="Configuration non trouvée")
     *         )
     *     )
     * )
     */
    public function updateConfig(Request $request)
    {
        $id = 1;
        $config = Config::findOrFail(1);

        $config->update([
            'compteur' => $request->input('compteur'),
            'dureePIN' => $request->input('dureePIN'),
        ]);

        return response()->json(['message' => 'Configuration mise à jour avec succès', 'data' => $config], 200);
    }

    public function updateTentatives(Request $request, $id)
    {
        $tentative = Tentatives::findOrFail($id);

        $tentative->update([
            'tentatives' => $request->input('tentatives'),
        ]);

        return response()->json(['message' => 'Tentatives mises à jour avec succès', 'data' => $tentative], 200);
    }

    /**
     * @OA\Put(
     *     path="/api/users/{id_users}",
     *     summary="Mettre à jour un utilisateur",
     *     description="Met à jour les informations d'un utilisateur dans la base de données.",
     *     tags={"User"},
     *     @OA\Parameter(
     *         name="id_users",
     *         in="path",
     *         description="ID de l'utilisateur à mettre à jour",
     *         required=true,
     *         @OA\Schema(type="integer")
     *     ),
     *     @OA\RequestBody(
     *         required=true,
     *         @OA\JsonContent(
     *             required={"username", "password"},
     *             @OA\Property(property="username", type="string"),
     *             @OA\Property(property="password", type="string", format="password"),
     *             @OA\Property(property="id_tentatives", type="integer", nullable=true)
     *         )
     *     ),
     *     @OA\Response(
     *         response=200,
     *         description="Utilisateur mis à jour avec succès",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string"),
     *             @OA\Property(property="data", type="object")
     *         )
     *     ),
     *     @OA\Response(
     *         response=404,
     *         description="Utilisateur non trouvé",
     *         @OA\JsonContent(
     *             @OA\Property(property="message", type="string")
     *         )
     *     ),
     *     @OA\Response(
     *         response=422,
     *         description="Erreur de validation des données",
     *         @OA\JsonContent(
     *             @OA\Property(property="errors", type="object")
     *         )
     *     )
     * )
     */
    public function updateUser(Request $request, $id_users)
    {
        try {
            $request->validate([
                'username' => 'required|string',
                'password' => 'required|min:6',
                'id_tentatives' => 'nullable|exists:tentatives,id_tentatives', // Rendre id_tentatives facultatif
            ]);

            $user = User::findOrFail($id_users);

            // Préparer les données à mettre à jour
            $updateData = [
                'username' => $request->input('username'),
                'password' => bcrypt($request->input('password')),
            ];

            // Conserver la valeur précédente de id_tentatives si aucune nouvelle valeur n'est fournie
            if ($request->has('id_tentatives')) {
                $updateData['id_tentatives'] = $request->input('id_tentatives');
            }

            // Mettre à jour uniquement les champs modifiables
            $user->update($updateData);

            return response()->json(['message' => 'Utilisateur mis à jour avec succès', 'data' => $user], 200);
        } catch (\Exception $e) {
            return response()->json(['error' => $e->getMessage()], 500);
        }
    }
}
