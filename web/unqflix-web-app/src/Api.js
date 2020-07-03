import axios from 'axios'

const host = 'http://localhost:7000';

function get(location){
    return axios.get(host.concat(location))
}

function post(location, payload){
    return axios.post(host.concat(location), payload)
}

function getBanners(token){
    return axios.get(host.concat('/banners'), token)
}

function register(payload){
    return post('/register', payload)
}

function searchAPI(payload, token){
    return axios.get(host.concat('/search'), payload, token)
}

function getContentId(id){
    return axios.get(host.concat('/content/'.concat(id)))
}

export default {
    get,
    post,
    getBanners,
    register,
    searchAPI,
    getContentId
    }