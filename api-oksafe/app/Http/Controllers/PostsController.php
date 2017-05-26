<?php 

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Posts;
use App\Categories;
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
	public function index(Request $request,$id_user)
	{

		$model = Posts::where('user_id',$id_user)->get();

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

	public function read(Request $request, $id)
    {
      $posts = Posts::where('id',$id)->first();
      if ($item_ads !== null) {
        $res['success'] = true;
        $res['result'] = $posts;

        return response($res);
      }else{
        $res['success'] = false;
        $res['result'] = 'Posts not found!';

        return response($res);
      }
    }

    public function create(Request $request)
    {
      $posts = new posts;
      $posts->fill([
        'user_id' => $request->input('user_id'),
        'judul' => $request->input('judul'),
        'isi' => $request->input('isi'),
        'gambar' => $request->input('gambar'),
        'latitude' => $request->input('latitude'),
        'longitude' => $request->input('longitude')
      ]);
      if($posts->save()){
        $res['success'] = true;
        $res['result'] = 'Success add new posts!';

        return response($res);
      }
    }

}