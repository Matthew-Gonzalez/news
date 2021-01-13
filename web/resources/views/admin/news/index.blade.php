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
                        <td class="py-4">{{Carbon\Carbon::parse($newsItem->published_at)->format('d-m-Y h:i')}}</td>
                        <td class="px-2 py-4">
                            <a class="px-4 py-2 bg-blue-400 rounded" href="{{ route('admin.news.edit', $newsItem) }}">
                                Edit
                            </a>
                        </td>
                        <td class="px-2 py-4">
                            <form action="{{ route('admin.news.destroy', $newsItem) }}" class="delete-form-validation" method="POST" >
                                @csrf
                                @method('DELETE')
                                <button type="submit" class="px-4 py-2 bg-red-400 rounded">
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

    @section('js')

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

        <!-- Delete news alert confirmation -->
        <script>
          $(document).ready(function(){
            $('.delete-form-validation').submit(function (e){
              e.preventDefault();

              Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                if (result.isConfirmed) {
                  this.submit();
                }
              })
            });
          });
        </script>

        <!-- News deleted successful alert -->
        @if(session('delete_msg'))
            <script>
              Swal.fire(
                  'Deleted!',
                  'The news has been deleted.',
                  'success'
              )
            </script>
        @endif

        <!-- News stored successful alert -->
        @if(session('store_msg'))
            <script>
              Swal.fire(
                  'Stored!',
                  'The news has been stored.',
                  'success'
              )
            </script>
        @endif

        <!-- News updated successful alert -->
        @if(session('update_msg'))
            <script>
              Swal.fire(
                  'Updated!',
                  'The news has been updated.',
                  'success'
              )
            </script>
        @endif

    @endsection
</x-app-layout>