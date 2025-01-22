document.getElementById("upload-form").addEventListener("submit", function (event) {
    event.preventDefault(); // Formun otomatik submit edilmesini engelle

    const formData = new FormData();
    formData.append("courseName", document.getElementById("course-name").value);
    formData.append("department", document.getElementById("department").value);
    formData.append("courseCode", document.getElementById("course-code").value);
    formData.append("description", document.getElementById("description").value);
    formData.append("price", document.getElementById("price").value);
    formData.append("file", document.getElementById("pdf-upload").files[0]);

    console.log(formData);

    fetch("http://localhost:8080/note/upload", {
        method: "POST",
        body: formData
    })
        .then(response => response.json())
        .then(data => {

            alert("Not başarıyla yüklendi!");
            form.reset();
        })
        .catch(error => {

        });
});

document.getElementById("logoutButton").addEventListener('click', async function () {
    try {
        const response = await fetch('http://localhost:8080/user/logout');
        if (response.ok) {
            window.location.href = "http://localhost:8080/start"; // Çıkış yaptıktan sonra giriş sayfasına yönlendirme
        }
    } catch (error) {
        console.error('Hata:', error);
    }
});
