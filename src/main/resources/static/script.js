
$(document).ready(function () {
    // id 가 query 인 녀석 위에서 엔터를 누르면 execSearch() 함수를 실행하라는 뜻입니다.
    $('#close').on('click', function () {
        $('#container').removeClass('active');
        console.log("active2");
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');

        $('#see-area').show();
        $('#main-area').hide();
        console.log("들어옴1");
    })
    $('.background-headertitle').on('click', function () {
        window.location.reload();
    })
    $('#see-area').hide();
    $('#main-area').show();

})
    // 미리 작성된 영역 - 수정하지 않으셔도 됩니다.
    // 사용자가 내용을 올바르게 입력하였는지 확인합니다.
function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
        }
    if (contents.trim().length > 140) {
        alert('공백 포함 140자 이하로 입력해주세요');
            return false;
        }
    return true;
}

// 메모를 불러와서 보여줍니다.
function getMessages() {
// 1. 기존 메모 내용을 지웁니다.
    $('#cards-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
    type: 'GET',
    url: '/api/boards',
    success: function (response) {
    for(let i =0; i<response.length; i++){
    let memo = response[i];
    let id = memo.id;
    let username = memo.username;
    let title =memo.title;
    let contents = memo.contents;
    let modifiedAt = memo.modifiedAt;
    addHTML(id, username, title, contents, modifiedAt);
    }
    console.log(response);
}
})
}

    // 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
    // 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, username, title,  contents, modifiedAt) {
// 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="card">
                        <!-- date/username 영역 -->
                        <div class="metadata">
                            <div class="date">
                                ${modifiedAt}
                            </div>
                            <div id="${id}-username" class="username">
                                ${username}
                            </div>
                        </div>
                        <div class="title">
                            <div id="${id}-title" class="text">
                                ${title}
                            </div>
                        </div>
                        <div class ="modal">
                            <div onclick="execSearch('${id}')" id="${id}-submit" class="modal-search">
                                 <svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-arrow-bar-up" viewBox="0 0 16 16">
                                     <path fill-rule="evenodd" d="M8 10a.5.5 0 0 0 .5-.5V3.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 3.707V9.5a.5.5 0 0 0 .5.5zm-7 2.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 0 1h-13a.5.5 0 0 1-.5-.5z"/>
                                 </svg>
                            </div>
                        </div>
                    </div>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#cards-box').append(tempHtml);
}
    // 메모를 생성합니다.
function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let username = $('#exampleFormControlInput1').val();
    let title = $('#exampleFormControlInput2').val();
    let contents = $('#contents').val();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }
    // 3. genRandomName 함수를 통해 익명의 username을 만듭니다.
    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'username': username, 'title': title, 'contents': contents};
    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/api/boards",
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    })
}


function execSearch(id) {
    /**
     * 검색어 input id: query
     * 검색결과 목록: #search-result-box
     * 검색결과 HTML 만드는 함수: addHTML
     */
    // 3. GET /api/search?query=${query} 요청
    $('#search-area').empty();
    console.log("active");
    $.ajax({
        type: 'GET',
        url: `/api/boards/${id}`,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let itemDto = response[i];
                let tempHtml = addProduct(itemDto);
                console.log(tempHtml);
                $('#search-area').append(tempHtml);
            }
            $('#container').addClass('active');
            $('#close').click(function(d) {
                $('#container').removeClass('active');
                console.log(d);
            });
        }
    })
    // 4. for 문마다 itemDto를 꺼내서 HTML 만들고 검색결과 목록에 붙이기!

}

function addProduct(itemDto) {
    /**
     * class="search-itemDto" 인 녀석에서
     * image, title, lprice, addProduct 활용하기
     * 참고) onclick='addProduct(${JSON.stringify(itemDto)})'
     */
    return `<div id="container" class="popup-container">
                <div class="popup">
                    <button id="close" class="close">
                        X
                    </button>
                    <div class="metadata">
                        <div class="popup_title">${itemDto.title}</div>
                        <div class="popup_username">${itemDto.username}</div>
                        <div class="popup_date">${itemDto.modifiedAt}</div>
                    </div>
                    <div class="contents">
                        <div>${itemDto.contents}</div>
                    </div>
                </div>
            </div>`
}
