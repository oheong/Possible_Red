import Axios from "axios";

// 메일로 보냈던 난수를 api로 같이 받아옴
const requestAddComment = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/comment', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestGetAllComment = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/comment/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestCommentDelete = (data, callback, errorCallback) => {
    Axios.delete('http://i3d201.p.ssafy.io:8080/comment/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const CommentApi = {
    requestAddComment: (data, callback, errorCallback) => requestAddComment(data, callback, errorCallback),
    requestGetAllComment: (data, callback, errorCallback) => requestGetAllComment(data, callback, errorCallback),
    requestCommentDelete: (data, callback, errorCallback) => requestCommentDelete(data, callback, errorCallback),
}

export default CommentApi