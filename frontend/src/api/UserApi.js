import Axios from "axios";

const requestLogin = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/login', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestRegister = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/member', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestUpdate = (data, callback, errorCallback) => {
    Axios.put('http://i3d201.p.ssafy.io:8080/member', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestEmailCheck = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/member/email/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestMemberByNo = (data, callback, errorCallback) => {
    // 사용자의 상세 정보를 반환한다.
    Axios.get('http://i3d201.p.ssafy.io:8080/member/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestUpdatePoint = (data, callback, errorCallback) => {
    // 사용자의 포인트 업데이트
    Axios.put('http://i3d201.p.ssafy.io:8080/member/point/' + data.memberNo + "/" + data.point)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestUpdatePassword = (data, callback, errorCallback) => {
    Axios.put('http://i3d201.p.ssafy.io:8080/member/password', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
} // 사용자 비밀번호 수정

const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    requestRegister: (data, callback, errorCallback) => requestRegister(data, callback, errorCallback),
    requestUpdate: (data, callback, errorCallback) => requestUpdate(data, callback, errorCallback),
    requestEmailCheck: (data, callback, errorCallback) => requestEmailCheck(data, callback, errorCallback),
    requestMemberByNo: (data, callback, errorCallback) => requestMemberByNo(data, callback, errorCallback),
    requestUpdatePoint: (data, callback, errorCallback) => requestUpdatePoint(data, callback, errorCallback),
    requestUpdatePassword: (data, callback, errorCallback) => requestUpdatePassword(data, callback, errorCallback),
}

export default UserApi
