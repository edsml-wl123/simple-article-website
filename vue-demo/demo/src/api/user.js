//导入request.js请求工具
import request from '../utils/request.js'

//提供调用注册接口的函数
export const userRegisterService = (registerData)=>{
    //借助于UrlSearchParams完成传递
    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }
    console.log(registerData);
    return request.post('/user/register',params);
}

//提供调用登录接口的函数
export const userLoginService = (loginData)=>{
    const params = new URLSearchParams();
    for(let key in loginData){
        params.append(key,loginData[key])
    }
    console.log(params);
    return request.post('/user/login',params)
}


//获取用户详细信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

//修改个人信息
export const userInfoUpdateService = (userInfoData)=>{
   return request.put('/user/update',userInfoData)
}

//修改个人信息
export const userPwUpdateService = (passwords)=>{
    const params = new URLSearchParams();
    for(let key in passwords){
        params.append(key,passwords[key])
    }

    return request.patch('/user/update_password',passwords)
 }

//修改头像
export const userAvatarUpdateService = (avatarUrl)=>{
    const params = new URLSearchParams();
    params.append('userPic',avatarUrl)
    return request.patch('/user/update_avatar',params)
}