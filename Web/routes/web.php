<?php

//use Illuminate\Routing\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

// Route::get('/', function () {
//     return view('welcome');
// });

Route::get ('/', function () {
	return redirect('login');
});

Route::get('/login','LandingController@index');

Route::group(['middleware' => 'auth'], function(){
	Route::group(['prefix' => 'admin'], function(){
		Route::get('/issue', 'PostController@index');
	});
});

Route::get('/sms/send/{to}', function(\Nexmo\Client $nexmo, $to){
	$message = $nexmo->verify()->start([
		'number' => $to,
		'brand' => 'Mail'
	]);
	Log::info('sent message: ' . $message['message-id']);
	return 'Started verification with an id of:'. $message->getRequestId().' oke';
});

Route::get('/sms/send/{to}', function(\Nexmo\Client $nexmo, $to){
	$verification = new \Nexmo\Verify\Verification('b50d9c7b145749d6b4269fb9301b66ea');
	$message = $nexmo->verify()->check($verification, '3068');
	Log::info('sent message: ' . $message['message-id']);
	return 'test';
});

Auth::routes();

Route::get('/home', 'HomeController@index');
