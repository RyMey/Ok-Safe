@extends('layouts.app')

@section('content')
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Detail Laporan
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-files-o"></i> Laporan</a></li>
        <li class="active">Detail Laporan</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box box-primary">
            <div class="box-header with-border">

              <div class="box-tools pull-right">
				<a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Print"><i class="fa fa-print"></i> Print</a>
                <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Previous"><i class="fa fa-chevron-left"></i></a>
                <a href="#" class="btn btn-box-tool" data-toggle="tooltip" title="Next"><i class="fa fa-chevron-right"></i></a>
              </div>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal">
              <div class="box-body">
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">Judul</label>

                  <div class="col-sm-10">
                    <h5>{{ $item->judul }}</h5>
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Pelapor</label>

                  <div class="col-sm-10">
                    <h5>{{ $item->user->username }}</h5>
                  </div>
                </div>
				        <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Tanggal Kejadian</label>

                  <div class="col-sm-10">
                    <h5><span class="mailbox-read-time pull-left">{{ date('d F Y', strtotime($item->created_at)) }}</span></h5>
                  </div>
                </div>
				        <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Kronologi Kejadian</label>

                  <div class="col-sm-10">
                    <div class="mailbox-read-message">
                      {{ $item->isi }}
					          </div>
                  </div>
                </div>
				        <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Status Posting</label>

                  <div class="col-sm-10">
                    @if($item->status_post == 0)
                    <h5><span class="badge bg-blue">New</span></h5>
                    @elseif($item->status_post == 1)
                    <h5><span class="badge bg-green">In Progress</span></h5>
                    @else
                    <h5><span class="badge bg-red">Done</span></h5>
                    @endif
                  </div>
                </div>
				        <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Status Valid</label>

                  <div class="col-sm-10">
                    @if($item->status_valid == 0)
                    <h5><span class="badge bg-blue">Haven't confirmed</span></h5>
                    @elseif($item->status_valid == 1)
                    <h5><span class="badge bg-green">Valid</span></h5>
                    @else
                    <h5><span class="badge bg-red">Invalid</span></h5>
                    @endif
                  </div>
                </div>
        				<div class="form-group">
        				  <label class="col-sm-2 control-label">Gambar Kejadian</label>
        				  
        				  <div class="col-sm-10">
        				    <ul class="mailbox-attachments clearfix">
          						<li>
          						  <span class="mailbox-attachment-icon has-img"><img src="dist/img/photo1.png" alt="Attachment"></span>

          						  <div class="mailbox-attachment-info">
          							<a href="#" class="mailbox-attachment-name"><i class="fa fa-camera"></i> photo1.png</a>
          								<span class="mailbox-attachment-size">
          								  2.67 MB
          								  <a href="#" class="btn btn-default btn-xs pull-right"><i class="fa fa-cloud-download"></i></a>
          								</span>
          						  </div>
          						</li>
        					  </ul>
        				  </div>
        				</div>
              </div>
              <!-- /.box-body -->
			     <div class="box-footer">
				    <div class="form-group">
				      <label for="inputPassword3" class="col-sm-2 control-label">
				        <ul class="list-inline">
                  <li class="pull-right">
                    <a href="#" class="link-black"><i class="fa fa-comments-o margin-r-5"></i> Comments (2)</a>
						      </li>
                </ul>
				      </label>

              <div class="col-sm-10">
					      <!-- Post -->
                <div class="post">
                  <div class="user-block">
                    <span class="username">
                      <a href="#">Jonathan Burke Jr.</a>
                      <a href="#" class="pull-right btn-box-tool"><i class="fa fa-times"></i></a>
                    </span>
                    <span class="description">Sent you a comment - 7:30 PM today</span>
					        </div>
                <!-- /.user-block -->
                  <p>
                    Lorem ipsum represents a long-held tradition for designers,
                    typographers and the like. Some people hate it and argue for
                  </p>
                      					  
                </div>
					
					      <div class="post">
                  <div class="user-block">
                    <span class="username">
                      <a href="#">Admin</a>
                      <a href="#" class="pull-right btn-box-tool"><i class="fa fa-times"></i></a>
                    </span>
                    <span class="description">7:30 PM today</span>
					        </div>
                  <!-- /.user-block -->
                  <p>
                    Lorem ipsum represents a long-held tradition for designers,
                    typographers and the like. Some people hate it and argue for
                  </p>
                      					  
                </div>
                <!-- /.post -->
        			<div class="box-footer">
        			  <form class="form-horizontal">
                  <div class="form-group margin-bottom-none">
        			      <div class="col-sm-10">
                      <input class="form-control input-sm" type="text" placeholder="Type a comment">
                    </div>
                    <div class="col-sm-2">
                      <button type="submit" class="btn btn-primary pull-right btn-block btn-sm">Send</button>
                    </div>
        					</div>
        			  </form>
        			</div>
            </div>
        				  
        	</div>
        </div>
        <!-- /.box-footer -->
            
        <div class="box-footer">
				  <button type="button" class="btn btn-default"><i class="fa fa-print"></i> Print</button>
          <button type="submit" class="btn btn-default">Cancel</button>
          <button type="submit" class="btn btn-default"><a href="detail.html">Edit</a></button>
        </div>
        <!-- /.box-footer -->
      </form>
    </div>
    <!-- /. box -->
  </div>
  <!-- /.col -->
</div>
<!-- /.row -->
</section>
@endsection
