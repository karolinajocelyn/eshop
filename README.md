# eshop

## Reflection 1: Coding Standards and Secure Coding

1. You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code. If you find any mistake in your source code, please explain how to improve your code

Beberapa *coding standards* yang saya pelajari adalah:
- **Separation of Concern**: Aplikasi dibagi ke dalam beberapa lapisan seperti model, repository, service, dan controller. Pendekatan ini membuat kode lebih terstruktur, mudah dikelola, serta fleksibel untuk pengembangan lebih lanjut.
- **Meaningful Names**: Setiap variabel, metode, dan kelas diberi nama yang bermakna agar mudah dipahami tanpa perlu banyak komentar, sehingga meningkatkan keterbacaan dan pemeliharaan kode. Namun, saya belum meletakkan lebih banyak komentar dalam kode agar lebih jelas ketika dibaca oleh orang lain.
- **Consistent Formatting and Indentation**: Kode ditulis dengan standar Java yang konsisten, termasuk indentasi, spasi, dan pemisahan baris yang rapi. Ini membantu menjaga keteraturan, mengurangi kompleksitas, dan mempermudah kerja sama tim.

Beberapa praktik *secure coding* yang saya pelajari adalah:
- **Error Handling**: Saya menerapkan mekanisme penanganan error yang baik dengan memberikan pesan yang jelas, mencatat kesalahan, dan menangani situasi tak terduga dengan cara yang aman. Ini membantu mencegah kebocoran data sensitif, meningkatkan pengalaman pengguna, dan menjaga kestabilan aplikasi. Namun, lebih baik jika website error Whitespace dapat digantikan agar tidak tampil kepada user dan tidak memberikan informasi yang berpotensi untuk disalahgunakan oleh penyerang.
- **Encrypted UUID**: Untuk melindungi data, saya menggunakan UUID yang telah dienkripsi sebagai ID produk. Hal ini memastikan bahwa informasi penting tidak mudah diakses atau dimanipulasi oleh pihak yang tidak berwenang, sehingga meningkatkan keamanan dan privasi aplikasi.

## Reflection 2: Unit Test and Functional Test

1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Setelah menulis unit test, rasanya cukup puas karena bisa memastikan bahwa kode berjalan sesuai harapan. Jumlah unit test dalam satu kelas tergantung pada kompleksitasnya. Kalau kelasnya punya banyak metode dengan berbagai skenario, tentu jumlah unit test juga harus lebih banyak. Namun, harus dipastikan bahwa semua fungsi utama dan kemungkinan kasus error harus diuji. Untuk memastikan unit test cukup untuk memverifikasi program, bisa menggunakan code coverage. Code coverage mengukur seberapa banyak kode yang sudah diuji lewat unit test. Semakin tinggi angkanya, semakin banyak bagian kode yang diuji. Walaupaun mencapai 100% code coverage, itu bukan jaminan bahwa kode benar-benar bebas bug. 

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

Ketika membuat functional test suite baru yang serupa dengan yang sudah ada, penting untuk tetap menjaga kebersihan kode dan menghindari duplikasi. Jika kode baru hanya menyalin struktur dan prosedur tanpa optimasi, maka dapat menyebabkan redundansi yang memperumit pemeliharaan di masa depan. Semakin banyak duplikasi, semakin sulit melakukan perubahan atau perbaikan tanpa memengaruhi beberapa bagian kode sekaligus. Untuk memastikan kode tetap bersih dan berkualitas, perlu diterapkan prinsip DRY (Don't Repeat Yourself) dengan menghindari pengulangan setup, variabel instance, atau logika pengujian yang sama. 

