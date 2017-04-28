Hai teman-teman....

Tutorial Cara Instalasi
1. Buat database baru kemudian import oksafedb ke database tsb
2. Di composer copy file ".env.example" menjadi ".env" dengan perintah command:
   cp .env.example .env 
   Kemudian buka file .env yang td telah di copy dan rubah pada bagian database name, username, dan password
3. Masih di composer, ketikkan perintah command berikut:
   composer update
4. Kemudian ketikkan perintah command berikut:
   php artisan key:generate
5. Selesai