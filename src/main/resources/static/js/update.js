// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault(); // 폼태그 액션을 막아서 더 이상 진행되지 않게 함
    let data = $("#profileUpdate").serialize();
    console.log(data);

    $.ajax({ // ajax 통신 시 Http 상태 코드를 같이 던져주는 것이 좋다.그래서 ControllerExceptionHandler에서 Http.BAD_REQUEST 함께 반환
        type:"put",
        url:`/api/user/${userId}`,
        data: data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{ // HttpStatus 상태코드 200번대
        console.log("update 성공",res);
        location.href=`/user/${userId}`;
    }).fail(error=>{ // HttpStatus 상태코드 200번대 아닐 때
        if(error.data==null){
            alert(error.responseJSON.message);
        } else {
            alert(JSON.stringify(error.responseJSON.data));
        }
        console.log("update 실패",error);
    });
}