document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const loginData = {
        mail: email,
        password: password
    };

    try {
        const response = await fetch('http://localhost:8080/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        if (response.ok) {
            window.location.href = "http://localhost:8080/home";
        } else {
            const errorMessage = await response.text();
            document.getElementById('message').textContent = errorMessage;
            document.getElementById('message').style.color = 'red';
        }
    } catch (error) {
        console.error('Hata:', error);
        document.getElementById('message').textContent = 'Bir hata oluştu!';
        document.getElementById('message').style.color = 'red';
    }
});