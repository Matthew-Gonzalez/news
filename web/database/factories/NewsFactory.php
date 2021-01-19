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
        $imagesArray = [
            'https://static.zerochan.net/Firis.Mistlud.full.2690752.jpg',
            'https://static.zerochan.net/Firis.Mistlud.full.2343689.jpg',
            'https://a.disquscdn.com/uploads/mediaembed/images/3694/5433/original.jpg',
            'https://i.redd.it/7fti0alwugq21.png',
            'https://i.redd.it/un5af18egtl21.jpg',
            'https://pbs.twimg.com/media/Chnoly5XIAEItVX.jpg',
            'https://pbs.twimg.com/media/C57YigiWcAANXDi.jpg',
            'https://s3.us-east-2.amazonaws.com/portal-laneta/luisgou/te-perdiste-el-e-3-el-youtuber-alexelcapo-te-lo-muestra-en-su-canal-01-20190619849404940',
            'https://4.bp.blogspot.com/-UO1zNmz139U/Wl3KsWmFy3I/AAAAAAAAHB4/YmPcJ_C60DA1qJ8WPPmgcB1bVtIvuTevwCLcBGAs/s640/26068208_1544967132205006_3733057124096278528_n.jpg',
            'https://pbs.twimg.com/media/CycBIFWWgAABjXh.jpg',
            'https://pbs.twimg.com/media/EPnmc3HWkAACUNT.jpg',
            'https://pbs.twimg.com/media/EMzRl35WkAElSc3.jpg'
        ];

        $timeZoneID = TimeZone::where('name', 'UTC')->first()->id;

        return [
            'title' => $this->faker->unique()->sentence(4),
            'author' => $this->faker->name,
            'source' => $this->faker->company,
            'url' => $this->faker->unique()->url,
            'url_image' => $imagesArray[array_rand($imagesArray)],
            'description' => $this->faker->sentence(10),
            'content' => $this->faker->text(),
            'time_zone_id' => $timeZoneID,
            'published_at' => $this->faker->dateTimeThisMonth()
        ];
    }
}
