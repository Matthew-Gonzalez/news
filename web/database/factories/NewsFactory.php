<?php

namespace Database\Factories;

use App\Models\News;
use App\Models\TimeZone;
use Illuminate\Database\Eloquent\Factories\Factory;
use Carbon\Carbon;

class NewsFactory extends Factory
{
    /**
     * The name of the factory's corresponding model.
     *
     * @var string
     */
    protected $model = News::class;

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        $timeZoneID = TimeZone::where('name', 'UTC')->first()->id;

        return [
            'title' => $this->faker->unique()->sentence(4),
            'author' => $this->faker->name,
            'source' => $this->faker->company,
            'url' => $this->faker->unique()->url,
            'url_image' => $this->faker->imageUrl(),
            'description' => $this->faker->sentence(10),
            'content' => $this->faker->text(),
            'time_zone_id' => $timeZoneID,
            'published_at' => $this->faker->dateTimeThisMonth()
        ];
    }
}
