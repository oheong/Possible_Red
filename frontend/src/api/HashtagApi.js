import Axios from "axios";

const requestAddHashtag = (data, callback, errorCallback) => {
    Axios.post('http://i3d201.p.ssafy.io:8080/hashtag', data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestFindAllHastags = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/hashtag/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const HashtagApi = {
    requestAddHashtag: (data, callback, errorCallback) => requestAddHashtag(data, callback, errorCallback),
    requestFindAllHastags: (data, callback, errorCallback) => requestFindAllHastags(data, callback, errorCallback)
}

export default HashtagApi