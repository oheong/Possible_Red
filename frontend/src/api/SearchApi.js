import Axios from "axios";

const requestCategory = (callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/category')
        .then(res => callback(res))
        .catch(error => errorCallback(error))
};

const requestMissionByCategory = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/mission/category/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestMemberBySearch = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/search/member/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestHashtagBySearch = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/search/hashtag/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const requestPostBySearch = (data, callback, errorCallback) => {
    Axios.get('http://i3d201.p.ssafy.io:8080/search/post/' + data)
        .then(res => callback(res))
        .catch(error => errorCallback(error))
}

const SearchApi = {
    requestCategory: (data, callback, errorCallback) => requestCategory(data, callback, errorCallback),
    requestMissionByCategory: (data, callback, errorCallback) => requestMissionByCategory(data, callback, errorCallback),
    requestMemberBySearch: (data, callback, errorCallback) => requestMemberBySearch(data, callback, errorCallback),
    requestHashtagBySearch: (data, callback, errorCallback) => requestHashtagBySearch(data, callback, errorCallback),
    requestPostBySearch: (data, callback, errorCallback) => requestPostBySearch(data, callback, errorCallback),
};

export default SearchApi