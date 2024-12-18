<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\firstController;
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

Route::get('/validate', [AuthController::class, 'validateEmail']);

Route::post('/login', [AuthController::class, 'login']);

Route::post('/verify-mfa', [AuthController::class, 'verifyMfaToken']);

Route::get('/reset-attempts', [AuthController::class, 'resetAttemptsByEmail'])->name('reset.attempts');

Route::put('/utilisateur/{id}', [firstController::class, 'updateUser']);


