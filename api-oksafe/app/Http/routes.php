<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$app->get('/', function () use ($app) {
  $res['success'] = true;
  $res['result'] = "Hello there welcome to web api using lumen tutorial!";
  return response($res);
});

$app->post('/login', 'LoginController@index');
$app->post('/register', 'UserController@register');
$app->get('/user/{id}', ['middleware' => 'auth', 'uses' =>  'UserController@get_user']);

/*
 | ------------------------------------------
 | CRUD Routes using auth middleware
 | ------------------------------------------
 */

// Posts routes
$app->get('/posts/{id_user}', 'PostsController@index');
$app->get('/posts/{id}', 'PostsController@read');
$app->post('/posts/create', 'PostsController@create');

// Comment routes
$app->get('/comment/{id_posts}', 'CommentController@index');
$app->post('/comment/create', 'CommentController@create');