# Queens Solver (CLI)

Deskripsi singkat
-----------------
Program Java (CLI) untuk menyelesaikan problem papan (Queens Solver) menggunakan pendekatan brute-force. Program membaca file test-case teks yang berisi representasi papan, menampilkan papan awal, menjalankan solver, dan menampilkan hasil. Pengguna dapat memilih menyimpan solusi ke file.

Persyaratan
-----------
- Java JDK 11 atau lebih baru
- Apache Maven (opsional, untuk kemudahan kompilasi)

Kompilasi
---------
1. Buka terminal di folder proyek (folder yang berisi `pom.xml`).
2. Jalankan:

```
mvn compile
```

Atau untuk membuat paket:

```
mvn package
```

Catatan: proyek dikonfigurasi agar output build ditempatkan di folder `bin` (mis. `bin/classes`).

Menjalankan
----------
1. Pastikan proyek sudah dikompilasi (lihat bagian Kompilasi).
2. Opsi menjalankan dari root proyek:

- Menjalankan kelas hasil kompilasi (classpath menunjuk ke `bin/classes`):

```
java -cp bin/classes tucil1.Main
```

- Jika Anda membuat JAR (`mvn package`) dan paket JAR berada di `bin`:

```
java -jar bin/tucil1-1.0-SNAPSHOT.jar
```

- Menjalankan lewat Maven (tidak tergantung folder output):

```
mvn -Dexec.mainClass=tucil1.Main exec:java
```

3. Saat diminta "Masukkan nama file test case:", isi path file input, mis. `test/TestCase1.txt`.
4. Jika solusi ditemukan, Anda dapat memilih untuk menyimpan solusi; file akan disimpan di lokasi input dengan suffix `_YYYYMMDD_HHMMSS_solution.txt`.

Contoh singkat
--------------
- Kompilasi: `mvn compile`
- Jalankan: `java -cp target/classes tucil1.Main`
- Ketika diminta, masukkan: `test/TestCase1.txt`

Author
------
Nathanael Gunawan — 13524066

Catatan
------
- File contoh test-case tersedia di folder `test/`.
- Jika menjalankan dari IDE, pastikan working directory adalah root proyek agar path relatif `test/...` ditemukan.