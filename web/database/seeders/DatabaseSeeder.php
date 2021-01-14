<?php
/*
 * Copyright 2020 Matthew González-Mansilla matthew.gonzalez@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

namespace Database\Seeders;

use Illuminate\Database\Seeder;
Use \App\Models\User;
Use \App\Models\News;
use \App\Models\TimeZone;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        $this->populateTimeZones();;
        $this->populateUsers();
        $this->populateNews();
    }

    /**
     * Populate time zones table
     *
     * @return void
     */
    public function populateTimeZones()
    {
        $timestamp = time();
        foreach (timezone_identifiers_list() as $zone) {
            date_default_timezone_set($zone);
            $zones['offset'] = date('P', $timestamp);
            $zones['diff_from_gtm'] = 'UTC/GMT '.date('P', $timestamp);

            TimeZone::updateOrCreate(['name' => $zone], $zones);
        }
    }

    /**
     * Populate users table
     *
     * @return void
     */
    public function populateUsers()
    {
        // Create the default admin user
        User::create([
            'name' => 'admin',
            'email' => 'admin@admin.admin',
            'password' => bcrypt('admin123')
        ]);
    }

    /**
     * Populate news table
     *
     * @return void
     */
    public function populateNews()
    {
        // Populate with news
        News::factory(100)->create();
    }
}
