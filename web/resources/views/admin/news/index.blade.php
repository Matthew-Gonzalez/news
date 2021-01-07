<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('News List') }}
        </h2>
    </x-slot>
    <div class="py-8 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="py-2">
            <a class="px-4 py-2 bg-green-400 float-right rounded" href="{{ route('admin.news.create') }}">
                Add News
            </a>
        </div>
        <table class="table-auto w-full">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Source</th>
                    <th>Published At</th>
                </tr>
            </thead>
            <tbody>
                @foreach($news as $newsItem)
                    <tr @if($loop->iteration % 2 == 0)class="bg-gray-200"@endif>
                        <td class="py-4">{{$newsItem->title}}</td>
                        <td class="py-4">{{$newsItem->author}}</td>
                        <td class="py-4">{{$newsItem->source}}</td>
                        <td class="py-4">{{$newsItem->published_at}}</td>
                        <td class="px-2 py-4">
                            <a class="px-4 py-2 bg-blue-400 rounded" href="{{ route('admin.news.edit', $newsItem) }}">
                                Edit
                            </a>
                        </td>
                        <td class="px-2 py-4">
                            <form action="{{ route('admin.news.destroy', $newsItem) }}" method="POST">
                                @csrf
                                @method('delete')
                                <button type="button" class="px-4 py-2 bg-red-400 rounded">
                                    Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                @endforeach
            </tbody>
        </table>
        <div class="mt-4">
            {{ $news->links() }}
        </div>
    </div>
</x-app-layout>