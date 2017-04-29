<?php

namespace App;

use Illuminate\Database\Eloquent\Model; 

class Posts extends Model{

  /**
   * Table database
   */
  protected $table = 'posts';

  /**
   * The attributes that are mass assignable.
   *
   * @var array
   */
  protected $fillable = [
  	'user_id','judul','isi','gambar','latitude','longitude'
  ];

}
