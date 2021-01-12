<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Collective\Html\Eloquent\FormAccessible;
use Carbon\Carbon;

class News extends Model
{
    protected $table = 'news';

    protected $fillable = [
        'title',
        'author',
        'source',
        'url',
        'url_image',
        'description',
        'content',
        'published_at'
    ];

    use HasFactory;
    use FormAccessible;

    /**
     * Get the published_at for forms.
     *
     * @param  string  $value
     * @return string
     */
    public function formPublishedAtAttribute($value)
    {
        return Carbon::parse($value)->format('Y-m-d\\Th:i');
    }

    /**
     * Formatting published_at
     *
     * @param $value
     */
    public function setPublishedAtAttribute($value)
    {
        $this->attributes['published_at'] = Carbon::parse($value)->format('Y-m-d h:i:s');
    }
}
