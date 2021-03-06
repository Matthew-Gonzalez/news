<?php

namespace App\Http\Controllers;

use App\Models\News;
use App\Models\TimeZone;
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
    public function list(Request $request)
    {
        $pageSize = $request->query('pageSize', 20);
        $pageN = $request->query('page', 1);
        $q = $request->query('q', null);
        $apiKey = $request->query('apiKey', null);

        // First obtain how many news can be returned
        $newsCount = ($q == null) ? News::count() : News::where('title', 'LIKE', '%' . $q . '%')->orWhere('content', 'LIKE', '%' . $q . '%')->count();

        // Validation of pageSize
        if (is_numeric($pageSize)){

            $temp = (int)$pageSize;

            if ($temp < 1){
                $pageSize = 1;
            }elseif ($temp > 100){
                $pageSize = 100;
            }else{
                $pageSize = $temp;
            }

        }else{
            $pageSize = 20;
        }


        // Validation page
        if (is_numeric($pageN)){

            $temp = (int)$pageN;
            $maxPages = $newsCount / $pageSize;

            if ($temp < 1 || $maxPages < 1){
                $pageN = 1;
            }elseif ($temp > $maxPages){
                $pageN = (int)$maxPages + 1;
            }else{
                $pageN = $temp;
            }

        }else{
            $pageN = 1;
        }


        if ($q == null){
            return News::orderBy('published_at', 'DESC')->simplePaginate($pageSize);
        }else{
            return News::orderBy('published_at', 'DESC')->where('title', 'LIKE', '%' . $q . '%')->orWhere('content', 'LIKE', '%' . $q . '%')->simplePaginate($pageSize);
        }
    }

    /*
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $timeZones = TimeZone::Orderby('offset')->get();

        return view('admin.news.create', compact('timeZones'));
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
            'published_at' => 'required|date|before:tomorrow'
        ]);

        $news = News::create([
            'title' => $request->title,
            'author' => $request->author,
            'source' => $request->source,
            'url' => $request->url,
            'url_image' => $request->url_image,
            'description' => $request->description,
            'content' => $request->input('content'),
            'time_zone_id' => $request->time_zone_id,
            'published_at' => $request->published_at
        ]);

        return redirect()->route('admin.news.index')->with('store_msg', 'stored');
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
        $timeZones = TimeZone::Orderby('offset')->get();

        return view('admin.news.edit', compact('news', 'timeZones'));
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
            'published_at' => 'required|date|before:tomorrow'
        ]);

        $news->update([
            'title' => $request->title,
            'author' => $request->author,
            'source' => $request->source,
            'url' => $request->url,
            'url_image' => $request->url_image,
            'description' => $request->description,
            'content' => $request->input('content'),
            'time_zone_id' => $request->time_zone_id,
            'published_at' => $request->published_at
        ]);

        return redirect()->route('admin.news.index')->with('update_msg', 'updated');
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

        return redirect()->route('admin.news.index')->with('delete_msg', 'deleted');
    }
}
