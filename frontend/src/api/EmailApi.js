import Axios from "axios";

// 메일로 보냈던 난수를 api로 같이 받아옴
const requestRandomNumber = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/email/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const EmailApi = {
    requestRandomNumber: (data, callback, errorCallback) => requestRandomNumber(data, callback, errorCallback)
}

export default EmailApi