@extends('layouts.app')

@section('content')
<!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>Laporan</h1>
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header with-border">
                                <h3 class="box-title">&nbsp;</h3>
                                <div class="box-tools">
                                    <div class="input-group input-group-sm" style="width: 150px;">
                                        <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Judul</th>
                                        <th>Pelapor</th>
                                        <th>Area</th>
                                        <th>Status Posting</th>
                                        <th>Status Valid</th>
                                        <th>Tanggal</th>
                                        <th></th>
                                    </tr>
									@foreach($items as $item)
                                    <tr>
                                        <td><a href="{{ url('admin/reports/'. $item->id) }}">{{ $item->judul }}</a></td>
                                        <td>{{ $item->user->username }}</td>
                                        <td>Baranangsiang</td>
                                        <td>
											@if($item->status_post == 0)
												New
											@else
												Old
											@endif
										</td>
                                        <td>
											@if($item->status_valid == 0)
												<span class="badge bg-yellow">Unconfirmed</span>
											@elseif($item->status_valid == 1)
												<span class="badge bg-green">Valid</span>
											@else
												<span class="badge bg-red">Invalid</span>
											@endif
                                        </td>
                                        <td>{{ date('d F Y', strtotime($item->created_at)) }}</td>
										<td></td>
                                    </tr>
									@endforeach
                                </table>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer clearfix">
                                {{ $items->links() }}
                            </div>
                        </div>
                        <!-- /.box -->
                    </div>
                    <!-- /.col -->
                </div>
            </section>
            <!-- /.content -->
@endsection