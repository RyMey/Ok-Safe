URL API android

=> LOGIN
	- URL : servername/api-oksafe/login
	- method : POST
	- Params :
		-- email
		-- password
=> REGISTER
	- URL : servername/api-oksafe/register
	- method : POST
	- Params :
		-- email
		-- password
		-- username
=> HISTORY LAORAN
	- URL : servername/api-oksafe/posts/id_user
	- method : GET

=>POSTS LAPORAN
	- URL : servername/api-oksafe/posts/create
	- Headers : api_token
	- method : POST
	- Params :
		-- user_id
		-- judul
		-- isi
		-- gambar
		-- latitude
		-- longitude
=> DETAIL LAPORAN
	- URL : servername/api-oksafe/posts/id_post
	- Headers : api_token
	- method : GET

=> POSTS COMMENT
- URL : servername/api-oksafe/comment/create
	- method : POST
	- Params :
		-- id_post
		-- komen