import {defineStore} from 'pinia'
import {ref} from 'vue'

//某些数据可能需要在多个组件之间共享
const useUserInfoStore = defineStore('userInfo',()=>{
    //定义状态相关的内容

    const info = ref({})

    const setInfo = (newInfo)=>{
        info.value = newInfo
    }


    const removeInfo = ()=>{
        info.value = {}
    }

    return {info,setInfo,removeInfo}

},{persist:true})  //即使刷新页面，状态也会被保留

export default useUserInfoStore;