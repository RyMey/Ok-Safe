<?php

namespace App;

use Illuminate\Database\Eloquent\Model; 

class Comment extends Model{

  /**
   * Table database
   */
  protected $table = 'comments';

  /**
   * The attributes that are mass assignable.
   *
   * @var array
   */
  protected $fillable = [
  	'id_post','komen'
  ];

}
