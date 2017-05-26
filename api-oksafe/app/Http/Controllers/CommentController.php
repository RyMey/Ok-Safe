<?php 

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Comment;
use App\Posts;
use DB;

/**
 * Created by ocittwo
 *
 * Movie Controller
 */
class PostsController extends Controller{

    /**
     * Create a new auth instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

	/**
	 * Index general movie
	 *
	 * URL /movie
	 */
	public function index(Request $request,$id_post)
	{

		$model = Posts::where('id_post',$id_post)->get();

		if ($model) {
          $res['success'] = true;
          $res['result'] = $model;

          return response($res);
      }else{
          $res['success'] = true;
          $res['result'] = 'No posts published!';

          return response($res);
      }
	}

	public function create(Request $request)
    {
      $comment = new comment;
      $comment->fill([
        'id_post' => $request->input('id_post'),
        'komen' => $request->input('komen'),
      ]);
      if($comment->save()){
        $res['success'] = true;
        $res['result'] = 'Success add new comment!';

        return response($res);
      }
    }



}