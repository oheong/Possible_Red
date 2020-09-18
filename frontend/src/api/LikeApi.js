import Axios from "axios";

const requestLikeList = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/likey/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestIsLike = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/likey/islike', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestAddLike = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/likey', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestRemoveLike = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/likey/delete', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const LikeApi = {
    requestLikeList: (data, callback, errorCallback) => requestLikeList(data, callback, errorCallback),
    requestIsLike: (data, callback, errorCallback) => requestIsLike(data, callback, errorCallback),
    requestAddLike: (data, callback, errorCallback) => requestAddLike(data, callback, errorCallback),
    requestRemoveLike: (data, callback, errorCallback) => requestRemoveLike(data, callback, errorCallback),
}

export default LikeApi
