<?php

namespace App\Http\Controllers;

use App\Models\News;
use Illuminate\Http\Request;
use Carbon\Carbon;

class NewsController extends Controller
{
    /**
     * Instantiate a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {

    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $news = News::orderBy('published_at', 'DESC')->paginate(8);

        return view('admin.news.index', compact('news'));
    }

    /**
     * Get the list of news
     *
     * @return News[]|\Illuminate\Database\Eloquent\Collection
     */
    public function list()
    {
        return News::orderBy('published_at', 'DESC')->get();
    }

    /*
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('admin.news.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $request->validate([
            'title' => 'required|string|max:255',
            'author' => 'required|string|max:50',
            'source' => 'required|string|max:50',
            'url' => 'required|unique:news',
            'description' => 'required|string|max:255',
            'content' => 'required|string',
            'published_at' => 'required|date'
        ]);

        News::create($request->all());

        return redirect()->route('admin.news.index');
    }

    /**
     * Display the specified resource.
     *
     * @param News $news
     * @return \Illuminate\Http\Response
     */
    public function show(News $news)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param News $news
     * @return \Illuminate\Http\Response
     */
    public function edit(News $news)
    {
        return view('admin.news.edit', compact('news'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param \Illuminate\Http\Request $request
     * @param News $news
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, News $news)
    {
        $request->validate([
            'title' => 'required|string|max:255',
            'author' => 'required|string|max:50',
            'source' => 'required|string|max:50',
            'url' => "required|unique:news,url,$news->id",
            'description' => 'required|string|max:255',
            'content' => 'required|string',
            'published_at' => 'required|date'
        ]);

        $news->update($request->all());

        return redirect()->route('admin.news.index');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param News $news
     * @return \Illuminate\Http\Response
     */
    public function destroy(News $news)
    {
        $news->delete();

        return redirect()->route('admin.news.index');
    }
}
