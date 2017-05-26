<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- CSRF Token -->
	<meta name="csrf-token" content="{{ csrf_token() }}">
	
    <title>OKSafe | Sign In</title>
	
	<!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	
    <link rel="icon" href="dist/img/ic_launcher.png">
    
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="{{ asset('bootstrap/css/bootstrap.min.css') }}">
	
	<!-- Theme style -->
	<link rel="stylesheet" href="{{ asset('dist/css/AdminLTE.min.css') }}">
	
	<!-- iCheck -->
	<link rel="stylesheet" href="{{ asset('plugins/iCheck/square/blue.css') }}">
	
	<!-- Scripts -->
	<script>
    window.Laravel = {!! json_encode([
       'csrfToken' => csrf_token(),
    ]) !!};
	</script>

</head>

<body>
    <div class="login-box">
        <div class="login-logo">
            <img src="{{ asset('dist\img\ic_logo_with_text.png') }}">
        </div>
        <!-- /.login-logo -->
        <div class="login-box-body">
            <p class="login-box-msg">Sig in</p>

            <form class="form-horizontal" role="form" method="POST" action="{{ route('login') }}">
              {{ csrf_field() }}
			  <div class="form-group has-feedback{{ $errors->has('email') ? ' has-error' : '' }}">
				<input type="email" name="email" id="email" class="form-control" placeholder="Email" value="{{ old('email') }}" required autofocus>
				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				@if ($errors->has('email'))
                    <span class="help-block">
                        <strong>{{ $errors->first('email') }}</strong>
                    </span>
                @endif
			  </div>
			  <div class="form-group has-feedback">
				<input placeholder="Password" id="password" type="password" class="form-control" name="password" required>
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				@if ($errors->has('password'))
                    <span class="help-block">
                        <strong>{{ $errors->first('password') }}</strong>
                    </span>
                @endif
			  </div>
			  <div class="row">
				<div class="col-xs-8">
				  <div class="checkbox icheck">
					<label>
                        <input type="checkbox" name="remember" {{ old('remember') ? 'checked' : '' }}> Remember Me
                    </label>
				  </div>
				</div>
				<!-- /.col -->
				<div class="col-xs-4">
				  <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
				</div>
				<!-- /.col -->
			  </div>
			</form>

			<a class="btn btn-link" href="{{ route('password.request') }}">Forgot Your Password?</a>
        </div>
    </div>

    <!-- jQuery 2.2.3 -->
	<script src="{{ asset('plugins/jQuery/jquery-2.2.3.min.js') }}"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="{{ asset('bootstrap/js/bootstrap.min.js') }}"></script>
	<!-- iCheck -->
	<script src="{{ asset('plugins/iCheck/icheck.min.js') }}"></script>
	<script>
	  $(function () {
		$('input').iCheck({
		  checkboxClass: 'icheckbox_square-blue',
		  radioClass: 'iradio_square-blue',
		  increaseArea: '20%' // optional
		});
	  });
	</script>
</body>

</html>
