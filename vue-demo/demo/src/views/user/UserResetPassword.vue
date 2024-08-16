<script setup>
import { ref } from 'vue'

let userPw=ref({old_pw:'', new_pw:'', rePw:''})
const rules = {
    old_pw: [
        { required: true, message: '请输入原密码', trigger: 'blur' },
        {
            pattern: /^\S{5,10}$/,
            message: '密码必须是5-10位的非空字符串',
            trigger: 'blur'
        }
    ],
    new_pw: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        {
            pattern: /^\S{5,10}$/,
            message: '密码必须是5-10位的非空字符串',
            trigger: 'blur'
        }
    ],
    rePw: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        {
            pattern: /^\S{5,10}$/,
            message: '密码必须是5-10位的非空字符串',
            trigger: 'blur'
        }
    ],
}

import {userPwUpdateService} from '@/api/user.js'
import {ElMessage} from 'element-plus'
const updateUserPw = async ()=>{
    let result = await userPwUpdateService(userPw.value);
    ElMessage.success(result.msg? result.msg : '修改成功');

}
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>修改密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userPw" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="原密码" prop="old_pw">
                        <el-input type="password" v-model="userPw.old_pw"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pw">
                        <el-input type="password" v-model="userPw.new_pw"></el-input>
                    </el-form-item>
                    <el-form-item label="再次输入" prop="rePw">
                        <el-input type="password" v-model="userPw.rePw"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updateUserPw">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>