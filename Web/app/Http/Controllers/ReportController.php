<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use App\Posts;

class ReportController extends Controller
{
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
		$items = Posts::orderBy('created_at', 'desc')->paginate(25);

        return view('report.report', compact('items'));
    }

    public function show($id)
    {
        $item = Posts::FindOrFail($id);

        return view('report.detail', compact('item'));
    }
}
