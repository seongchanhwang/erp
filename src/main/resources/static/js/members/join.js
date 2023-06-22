

/*******************************
 * 인증 메일 발송
 *******************************/
function sendAuthMail(){

    let authKey;
    let isChecked = false;
    const memberId = document.getElementById('memberId').value;
    const url = `/auth/email/${memberId}`;

    /*유효성 검사*/
    console.log("memberId=",memberId);
    if(memberId.length === 0 ){
        Swal.fire("Oops, ID를 입력하지 않았네요!","Email id를 입력해 주세요 :)","warning");
        return ;
    }

    Swal.fire({
        title : '메일을 전송중입니다!'
        , text :'잠시만 기다려주세요 :-) '
        , timer : 2000
        , timerProgressBar : true
        , showConfirmButton : false
        , allowOutsideClick : false
        , didOpen : () => {  // 팝업이 표시된 후 이벤트 실행
            // 로딩 바 표시
            Swal.showLoading()
            fetch(url,{
                method : "POST"
            }).then(response => {
                if(!response.ok) {
                    throw new Error(response.statusText)
                }
                return response.json();
            }).catch(error => {
                console.log("error=",error);
                Swal.fire('메일 전송 실패', '인증메일 전송에 실패했어요 :*(. 다시 시도해주세요.' ,'error')
            }).then((result) => {
                console.log(result);
            })
        }, allowOutsideClick: () => !Swal.isLoading()

    }).then((result)=> { // 앞서 이벤트가 어떻게 끝났는지에 따라 결과를 처리한다.
        console.log("result authKey" ,authKey);


        if(result.dismiss === Swal.DismissReason.timer){ // 타이머가 끝난 경우.
            Swal.fire({
                icon:'success'
                ,title:'메일 전송 완료!!'
                ,text : '인증 코드를 입력해주세요'
                ,input: 'text'
                ,showCancelButton : true
                ,confirmButtonText : "확인"
                ,cancelButtonText : "취소"
                ,preConfirm: (inputKey) => {
                    console.log(authKey)

                    if(result.authKey === inputKey) {
                        isChecked = true;
                    } else {
                        Swal.showValidationMessage('인증 코드가 올바르지 않습니다.');
                    }
                }

            }).then((result)=>{
                if(result.isConfirmed){
                    Swal.fire( {title : "인증이 완료되었습니다"
                        , time: 2000 });
                } else {
                    // 취소 버튼 클릭 시

                }
            })
        }
    });

}