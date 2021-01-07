<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Add News') }}
        </h2>
    </x-slot>
    <div class="py-8 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {!! Form::open(['route' => 'admin.news.store']) !!}
        <div class="py-2">
            {!! Form::label('title', 'Title', ['class' => 'font-bold']) !!}
            {!! Form::text('title', null, ['class' => 'w-full rounded', 'maxlength' => 255]) !!}
            @error('title')
                <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('author', 'Author', ['class' => 'font-bold']) !!}
            {!! Form::text('author', null, ['class' => 'w-full rounded', 'maxlength' => 50]) !!}
            @error('author')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('source', 'Source', ['class' => 'font-bold']) !!}
            {!! Form::text('source', null, ['class' => 'w-full rounded', 'maxlength' => 50]) !!}
            @error('source')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('url', 'URL', ['class' => 'font-bold']) !!}
            {!! Form::text('url', null, ['class' => 'w-full rounded']) !!}
            @error('url')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('url_image', 'Image URL', ['class' => 'font-bold']) !!}
            {!! Form::text('url_image', null, ['class' => 'w-full rounded']) !!}
            @error('url_image')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('description', 'Description', ['class' => 'font-bold']) !!}
            {!! Form::textarea('description', null, ['class' => 'w-full rounded', 'maxlength' => 255, 'style' => 'height: 126px']) !!}
            @error('description')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('content', 'Content', ['class' => 'font-bold']) !!}
            {!! Form::textarea('content', null, ['class' => 'w-full rounded']) !!}
            @error('content')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::label('published_at', 'Published At', ['class' => 'font-bold']) !!}
            {!! Form::datetimeLocal('published_at', null, ['class' => 'w-full rounded']) !!}
            @error('published_at')
            <span class="text-red-600">{{ $message }}</span>
            @enderror
        </div>
        <div class="py-2">
            {!! Form::submit('Add News', ['class' => 'px-4 py-2 bg-green-400 rounded']) !!}
        </div>
        {!! Form::close() !!}
    </div>
</x-app-layout>