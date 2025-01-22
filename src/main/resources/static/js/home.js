async function fetchNotes() {
    const response = await fetch('http://localhost:8080/note/get-all');
    const notes = await response.json();

    const limitedNotes = notes.slice(0, 5);

    const container = document.querySelector('.notes-container');
    limitedNotes.forEach(note => {
        const noteElement = document.createElement('div');

        noteElement.innerHTML = `
					<div class="container2">
					<div class="kutu">
	                    <div class="left">
	                        <div class="code">${note.code}</div>
	                        <div class="ders">${note.title}</div>
	                        <div class="name">${note.user.name} • 👁️ 78</div>
	                    </div>
	                    <div class="right">
	                        <div class="Acıklama">${note.description} </div>
	                        <div class="tarih">8 Nisan</div>
	                    </div>
					</div>
					</div>
	                `;
        container.appendChild(noteElement);
    });
}

// Sayfa yüklendiğinde notları getir
fetchNotes();


document.addEventListener('DOMContentLoaded', async function () {
    try {



        const response = await fetch("http://localhost:8080/user/current-user");
        if (response.ok) {
            const user = await response.json();

            const userNameElement = document.getElementById('userName');
            if (userNameElement) {
                userNameElement.innerText = user.name;
            } else {
                console.error("Kullanıcı adı için HTML elemanı bulunamadı.");
            }
        } else {
            // Kullanıcı oturum açmadıysa giriş sayfasına yönlendirme
            window.location.href = "/login";
        }
    } catch (error) {
        console.error('Hata:', error);
        window.location.href = "/login";
    }
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


const courseCards = document.querySelectorAll('.kutu');

courseCards.forEach(card => {
    card.addEventListener('mouseover', () => {
        card.style.boxShadow = '0px 5px 10px rgba(0, 0, 0, 0.2)';
        card.style.transform = 'scale(1.02)';
    });

    card.addEventListener('mouseout', () => {
        card.style.boxShadow = '0 12px 12px rgba(149, 22, 22, 0.1)';
        card.style.transform = 'scale(1)';
    });
});
$(document).ready(function () {
    $(".item").hover(
        function () {
            $(this).animate({fontSize: '1.2em'}, 200);
        },
        function () {
            $(this).animate({fontSize: '1em'}, 200);
        }
    );
});






		
		
	

		    
		
