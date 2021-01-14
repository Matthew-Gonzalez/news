<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\TimeZone;
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
        'published_at',
        'time_zone_id'
    ];

    protected $hidden = ['time_zone_id'];

    use HasFactory;
    use FormAccessible;

    /**
     * Get the news time zone
     *
     * @return TimeZone
     */
    public function timeZone()
    {
        return $this->belongsTo(TimeZone::class, 'time_zone_id');
    }



    /**
     * Get the published_at for forms.
     *
     * @param  string  $value
     * @return string
     */
    public function formPublishedAtAttribute($value)
    {
        return Carbon::parse($value)->setTimezone($this->timeZone->name)->format('Y-m-d\\TH:i');
    }

    /**
     * Formatting published_at
     *
     * @param $value
     */
    public function setPublishedAtAttribute($value)
    {
        $this->attributes['published_at'] = Carbon::parse($value, $this->timeZone->name)->toJSON();
    }
}
