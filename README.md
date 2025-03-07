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

## Reflection 3 - CI/CD & DevOps

1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Sebelumnya, code coverage pada proyek ini hanya 16% karena kurangnya pengujian unit yang mencakup semua skenario dalam kode. Hal ini terjadi karena beberapa metode tidak memiliki tes yang cukup, terutama pada ProductRepository, ProductServiceImpl, ProductController, dan beberapa bagian lain dari aplikasi. Untuk meningkatkan code coverage hingga 100%, saya melakukan:

- **Menambahkan Unit Test pada ProductRepository** karena beberapa cabang kondisi dalam metode edit() dan delete() tidak diuji dengan baik, sehingga ada branch yang tidak tercover dalam laporan JaCoCo.
- **Memperbaiki Unit Test pada ProductServiceImpl** karena sebelumnya metode findById(), edit(), dan beberapa metode lain belum diuji.
- **Menambahkan Unit Test pada ProductController** karena seagian besar metodenya belum diuji.
- **Menambahkan Unit Test untuk Home Page**

Dengan semua perbaikan ini, code coverage meningkat dari 16% menjadi 100%, memastikan bahwa semua kode diuji dengan baik dan tidak ada bagian kode yang lolos dari pengujian.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Saat ini, implementasi CI/CD dalam proyek ini sudah cukup baik dalam aspek Continuous Integration (CI), tetapi belum sepenuhnya mendukung Continuous Deployment (CD). Setiap kali ada push atau pull request, pipeline otomatis menjalankan code scanning dengan SonarCloud dan unit test menggunakan JaCoCo, memastikan bahwa setiap perubahan kode diuji sebelum digabungkan ke branch utama. Hal ini membantu menjaga kualitas kode dan mencegah bug masuk ke dalam kode produksi. Namun, dari sisi Continuous Deployment (CD), pipeline masih belum sepenuhnya otomatis karena belum ada langkah build dan deployment ke production atau staging environment. Untuk mencapai Continuous Deployment yang sesungguhnya, perlu ditambahkan automated deployment ke server setelah semua pengujian berhasil.

## Reflection 4 - OO Principles & Software Maintainability

1. Explain what principles you apply to your project!

- **Single Responsibility Principle (SRP)** artinya setiap kelas hanya memiliki satu alasan untuk berubah. Saat ini, kode yang saya implementasikan di setiap kelas memiliki tanggung jawabnya sendiri, seperti ProductController hanya mengatur logika kontrol produk dan ProductServiceImpl hanya menangani logika bisnis.
- **Open/Closed Principle (OCP)** artinya bahwa kelas harus terbuka untuk ekstensi tetapi tertutup untuk modifikasi. Dengan memiliki interface seperti CrudRepository<T> untuk operasi CRUD yang generik, kita memungkinkan penambahan jenis entitas baru tanpa harus mengubah implementasi repository secara langsung. Jika suatu hari kita menambahkan jenis barang lain, kita cukup membuat repository baru yang mengimplementasikan interface tersebut tanpa perlu mengubah repository yang sudah ada.
- **Liskov Substitution Principle (LSP)** mengharuskan subclass bisa menggantikan superclassnya tanpa mengubah perilaku yang diharapkan. Sebelumnya, ada kasus di mana CarController sebelumnya mewarisi ProductController, yang melanggar prinsip ini karena mobil bukan subtipe dari produk. Dengan refaktorisasi agar setiap controller berdiri sendiri dan tidak menggunakan pewarisan yang tidak semestinya, kode menjadi lebih sesuai dengan prinsip ini.
- **Interface Segregation Principle (ISP)** menyatakan bahwa interface seharusnya tidak memaksa kelas untuk mengimplementasikan metode yang tidak mereka butuhkan. Dalam kode ini, membuat CrudRepository<T> membantu memastikan bahwa setiap repository hanya mengimplementasikan metode yang relevan, tanpa memaksakan metode yang tidak berguna. Misalnya, jika suatu entitas baru tidak memerlukan metode update, kita bisa membuat repository yang hanya mengimplementasikan metode yang dibutuhkan.
- **Dependency Inversion Principle (DIP)** mengatakan bahwa kode harus bergantung pada abstraksi, bukan implementasi konkret. Dengan menggunakan interface seperti ProductService dan CrudRepository<T>, kita memastikan bahwa ProductController dan ProductServiceImpl tidak bergantung langsung pada implementasi ProductRepository, tetapi pada abstraksi yang bisa diganti-ganti.

2. Explain the advantages of applying SOLID principles to your project with examples.

- Dengan menerapkan SOLID, setiap kelas memiliki tanggung jawab yang jelas. Misalnya, dalam kode yang sudah diperbaiki, ProductServiceImpl hanya menangani logika bisnis produk, sedangkan ProductRepository hanya bertanggung jawab atas penyimpanan data. Jika ada perubahan pada aturan bisnis, kita hanya perlu mengubah ProductServiceImpl tanpa menyentuh repository atau controller.
- Dengan adanya interface seperti ProductService, kita dapat dengan mudah membuat mock untuk unit test tanpa bergantung pada implementasi konkret. Contohnya, dalam ProductControllerTest, kita bisa mengganti ProductService dengan mock sehingga pengujian lebih cepat dan tidak memerlukan koneksi ke database.
- Dengan menerapkan CrudRepository<T>, kita bisa menambahkan jenis produk baru tanpa harus mengubah repository yang sudah ada. Jika ingin menambahkan entitas baru seperti Book, kita cukup membuat BookRepository yang mengimplementasikan CrudRepository<Book> tanpa perlu mengubah ProductRepository atau CarRepository.
- Dengan menerapkan Dependency Inversion Principle, kita memastikan bahwa ProductController tidak bergantung langsung pada ProductRepository, tetapi pada ProductService. Hal ini memungkinkan kita mengganti implementasi service di masa depan tanpa perlu mengubah controller.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.

- Tanpa SOLID, jika kita ingin mengubah aturan bisnis untuk produk, kita mungkin harus mengubah banyak bagian kode sekaligus, seperti controller, repository, dan service, karena tidak ada pemisahan tanggung jawab yang jelas.
- Jika tidak ada abstraksi melalui interface seperti ProductService, maka dalam pengujian ProductController, kita harus menggunakan ProductRepository yang sebenarnya, yang bisa memperlambat pengujian karena bergantung pada database atau sumber daya lain.
- Jika tidak menerapkan prinsip Open/Closed, maka untuk menambahkan jenis produk seperti Book, kita harus mengubah ProductRepository atau bahkan ProductController, yang berisiko menimbulkan bug di bagian lain.
- Jika ProductController langsung bergantung pada ProductRepository, maka kita tidak bisa mengganti penyimpanan data dengan cara lain, seperti mengganti database atau menggunakan caching, tanpa memodifikasi controller secara langsung. Hal ini bertentangan dengan prinsip Dependency Inversion dan membuat sistem sulit berkembang.

## Reflection 5 - TDD & Refactoring

1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

Dalam penerapan Test-Driven Development (TDD) pada latihan ini, saya merefleksikan beberapa aspek berdasarkan pertanyaan reflektif dari Percival (2017). Secara umum, TDD membantu memastikan bahwa kode yang ditulis benar-benar memenuhi kebutuhan yang diharapkan, karena pengujian dibuat sebelum implementasi. Selain itu, proses ini juga mempermudah deteksi kesalahan sejak awal, sehingga mengurangi kemungkinan bug tersembunyi di kemudian hari. Dengan menulis tes terlebih dahulu, saya merasa lebih fokus dalam mendesain kode yang modular dan lebih mudah diuji.

Namun, ada beberapa tantangan dalam penerapan TDD yang perlu diperbaiki di masa mendatang. Salah satunya adalah memastikan cakupan pengujian yang lebih luas, terutama untuk edge cases yang mungkin terlewat. Selain itu, saya perlu lebih disiplin dalam menulis tes yang tidak hanya menguji skenario umum, tetapi juga skenario ekstrem yang bisa terjadi di dunia nyata. Ke depannya, saya juga ingin lebih menyeimbangkan antara menulis tes yang cukup spesifik tanpa menghambat fleksibilitas kode dalam jangka panjang.

2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.

Berdasarkan prinsip F.I.R.S.T. (Fast, Independent, Repeatable, Self-Validating, and Timely), saya merefleksikan bahwa unit test yang saya buat telah memenuhi sebagian besar prinsip tersebut. Tes berjalan cepat karena menggunakan mocking pada dependensi, memastikan bahwa pengujian tidak bergantung pada database atau jaringan. Selain itu, tes bersifat independen, dengan setiap skenario memiliki data uji sendiri tanpa ketergantungan pada hasil tes lain. Tes juga repeatable, karena dengan kondisi awal yang sama, hasil pengujian akan selalu konsisten.

Namun, ada beberapa aspek yang bisa diperbaiki.
- Beberapa exception handling tests menggunakan assertThrows, tetapi belum memverifikasi pesan error yang muncul. Hal ini melanggar prinsip Self-Validating sehingga sebaiknya menambahkan pemeriksaan/validasi yang lebih spesifik.
- Beberapa tes menggunakan objek yang sama (misalnya Order atau Payment) yang bisa terpengaruh oleh perubahan status dalam tes lain. Hal ini melanggar prinsip Independent, sebaiknya dibuat instance baru dalam setiap metode @Test, atau pastikan data diuji dalam keadaan clean setiap kali dijalankan.
- Beberapa tes menggunakan nilai hardcoded yang bisa berubah seiring pengembangan. Sebaiknya, menggunakan constant variables atau test data factory agar tidak perlu mengganti nilai secara manual saat kode diperbarui agar data testing konsisten (Repeatable).
- Beberapa tes masih menggunakan pengolahan data secara langsung, padahal bisa dipercepat dengan Mockito untuk mencegah eksekusi yang tidak perlu agar lebih optimal (Fast).