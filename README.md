# News App
The News Android app developed in DSM-2020-2 for educational purposes.

&nbsp;

## Setup Instructions 
In order to use our app it is necessary to follow the following instructions:

1. [Configure .env file for the Laravel local News API](https://github.com/Matthew-Gonzalez/news#1-configure-env-file).
2. [Run migrations into the Laravel local News API database](https://github.com/Matthew-Gonzalez/news#2-run-migrations-into-the-laravel-local-news-api-database).
3. [Poblate the Laravel local News API database](https://github.com/Matthew-Gonzalez/news#3-poblate-the-laravel-local-news-api-databe).
4. [Set local News API base URL and public News API API key in the News app](https://github.com/Matthew-Gonzalez/news#4-set-local-news-api-base-url-and-public-news-api-api-key-in-the-news-app).

&nbsp;

### 1. Configure .env file
First you need to add a .env file in the next proyect folder `news\web\.env`. You can download an example from [here](https://github.com/laravel/laravel/blob/8.x/.env.example).

The next step is to configure the database you will use in this section of the .env file (the following block is an sqlite example):

`line 10  to line 15`

```env
DB_CONNECTION=sqlite
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE="C:/Users/Inexpro Gamer/AndroidStudioProjects/news/web/database/database.sqlite"
DB_USERNAME=root
DB_PASSWORD=
```

&nbsp;

### 2. Run migrations into the Laravel local News API database
To run the migrations execute the following command in the command promp from `news\web` directory:

`php artisan migrate`

&nbsp;

### 3. Poblate the Laravel local News API databe
There are two ways to populate the local News API database:

#### 1. Using the seeder
Run the seeder to populare the datebase using Faker executing the following command in the command promp from `news\web` directory:

`php artisan db:seed`

If you want to drop all the news before populating the database execute the following command in the command promp from `news\web` directory:

`php artisan migrate:fresh --seed`

#### 2. Manually
Running the Laravel local server you can access to the `/news` directory from a web browser and add news using a form. You can see how to run the Laravel local server [here](https://github.com/Matthew-Gonzalez/news#local-news-api-instructions).

&nbsp;

### 4. Set local News API base URL and public News API API key in the News app
The last step is set the base URL and the API key (obtained from https://newsapi.org) you are going to use to connect and consume the local and public News APIs, for this going to the `MainActivity` class in the follow directory `\news\app\src\main\java\cl\ucn\disc\dsn\mgonzalez\news\activities` and put them in these methods as an argument:

`line 78 to line 81`

```java
ContractsImplNewsAPIs contracts = new ContractsImplNewsAPIs(
    "API key provided for the public News API https://newsapi.org",
    "base URL for local News API for ex: http://192.168.1.84:8000/"
);
```

`line 106 to line 109`

```java
ContractsImplNewsAPIs contracts = new ContractsImplNewsAPIs(
    "API key provided for the public News API https://newsapi.org",
    "base URL for local News API for ex: http://192.168.1.84:8000/"
);
```

&nbsp;

## Local News API Instructions
To gain access to the local News API server you must run a local Laravel server from the command promp follow the following instructions, unless you whant use your own public domain:

1. [Get your IPV4 address](https://github.com/Matthew-Gonzalez/news#1-get-your-ipv4-address).
2. [Run Laravel local server](https://github.com/Matthew-Gonzalez/news#2-run-laravel-local-server).
3. [Optionals API parameters](https://github.com/Matthew-Gonzalez/news#3-optionals-api-parameters).

&nbsp;

### 1. Get your IPV4 address
To run a local Laravel server that the News app can access, both of them must be on the same internet domain and for this the PC where you have the local Laravel News API must be the local host, so use the following command at the command prompt to get your IPV4 address:

`ipconfig`

&nbsp;

### 2. Run Laravel local server
Once you get your IPV4 address execute the follwing command in the command promp from `news\web` directory:

`php artisan serv --host [your IPV4 address without the the brackets]`

Doing this you can access from a web browser to the `/news` directory to administrator all the news in the database and access to the `/api/news` directory to consume the local News API.

&nbsp;

### 3. Optional API parameters
Here are the optional parameters for the query:

- pageSize: How many news The number of results to return per page (request). 20 is the default, 100 is the maximum.
- page: Use this to page through the results if the total results found is greater than the page size.
- q: Keywords or a phrase to search for in the title or in the content of the news

Here is an example: `/api/news?pageSize=30?page=2?q=Lorem`
