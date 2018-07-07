<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    欢迎登录
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input v-model="form.userName" placeholder="请输入用户名">
                                <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="请输入密码">
                                <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">输入任意用户名和密码即可</p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
import Cookies from 'js-cookie';
import userSrv from "../libs/user.service";
export default {
    data () {
        return {
            form: {
                userName: '',
                password: ''
            },
            rules: {
                userName: [
                    { required: true, message: '账号不能为空', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        handleSubmit () {
            let vm = this;
            vm.$refs.loginForm.validate((valid) => {
                if (valid) {
                    userSrv.login(vm.form).then(function(res) {
                        if(res&&res.data&&res.data.id) {
                            Cookies.set('user', vm.form.userName);
                            Cookies.set('password', vm.form.password);
                            if (vm.form.userName === 'iview_admin') {
                                Cookies.set('access', 0);
                            } else {
                                Cookies.set('access', 1);
                            }
                            vm.$router.push({
                                name: 'home_index'
                            });
                        }else {
                            let msg = "登录失败";
                            if(res&&res.data&res.data.err) {
                                msg += res.data.err;
                            }
                            vm.$Message.error(msg);
                        }
                    }).catch(function(res) {
                        vm.$Message.error("登录异常！");
                    })

                }
            });
        }
    }
};
</script>

<style>

</style>
