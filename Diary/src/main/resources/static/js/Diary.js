// 삭제 기능
// id가 delete-btn인 엘리먼트 조회
const deleteButton = document.getElementById('delete-btn');

if (deleteButton){
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('diary-id').value;
        fetch(`/api/diaries/${id}`,{
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/diaries');
            });
    });
}

// 수정 가능
// id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if(modifyButton){
    // 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/diaries/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/diaries`);
            });
    });
}

// 등록 기능
// id가 create-btn인 엘리먼트
const createButton = document.getElementById("create-btn");

if(createButton){
    // 클릭 이벤트가 감지되면 생성 API 요청
    createButton.addEventListener("click", (event) => {

        let params = new URLSearchParams(location.search);

        fetch("/api/diaries", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
                date: params.get('date')

            }),
        }).then(() => {
            alert("등록 완료되었습니다.");
            location.replace("/diaries");
        });
    });
}