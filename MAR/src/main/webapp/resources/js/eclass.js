/**
 * 
 */
EClass = {
    /**
     * <pre>     * Ajax Wrapper 입니다. (Method : POST)
     *
     * 필수) _URL - 전송 URL (String)
     * 필수) _PARAMETERS - 전송 파라미터 (object or String)
     * 필수) _CALLBACK - 콜백함수 (function)
     * 선택) _async - 동기화 여부 (boolean : 미지정시 true)
     * 선택) _errorMsg - 전송 실패시 메시지 (String)
     * </pre>
     */
    callAjax : function(_URL, _PARAMETERS, _METHOD, _ASYNC_STATUS, _CALLBACK){
        if(_URL != null){
 
            var _async = _ASYNC_STATUS;
            if(_async == null) _async = true;
 
            var _errorMsg = "서버에 요청중 문제가 발생!!";
 
 
 			console.log("====callAjax====");
 			
 			console.log("_URL: "+_URL);
 			console.log("_PARAMETERS: "+_PARAMETERS);
 			console.log("_METHOD: "+_METHOD);
 			console.log("_ASYNC_STATUS: "+_ASYNC_STATUS);
 			
 			let paramArray = Object.keys(_PARAMETERS);
 			console.log(paramArray);
 			
 			console.log("====//callAjax====");
 			
            $.ajax({
	            url     : _URL,
                type    : _METHOD,
                data    : _PARAMETERS,
                async   : _async,
                success : function(rst){
                    _CALLBACK(rst);
                },
                error   : function(){
                    if(_errorMsg != null){
                        alert(_errorMsg);
                    }else{
                        alert("서버에 요청중 문제가 발생했습니다.\n관리자에게 문의하여 주십시오.");
                    }
                }
            });
        }else{
            alert("올바른 요청이 아닙니다.");
            return false;
        }
    }
};