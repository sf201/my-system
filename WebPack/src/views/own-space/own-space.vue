<style lang="less">
    @import './own-space.less';
</style>

<template>
    <div>
        <Card>
            <p slot="title">
                <Icon type="person"></Icon>
                个人信息
            </p>
            <div>
                <Form
                        ref="userForm"
                        :model="userForm"
                        :label-width="100"
                        label-position="right"
                >
                    <FormItem label="用户姓名：" prop="name">
                        <div style="display:inline-block;width:300px;">
                            <Input readonly v-model="userForm.name"></Input>
                        </div>
                    </FormItem>
                    <FormItem label="用户手机：" prop="phone">
                        <div style="display:inline-block;width:204px;">
                            <Input readonly v-model="userForm.phone" ></Input>
                        </div>
                    </FormItem>
                    <FormItem label="登录密码：">
                        <Button type="text" size="small" @click="showEditPassword">修改密码</Button>
                    </FormItem>
                </Form>
            </div>
        </Card>
        <Modal v-model="editPasswordModal" :closable='false' :mask-closable=false :width="500">
            <h3 slot="header" style="color:#2D8CF0">修改密码</h3>
            <Form ref="editPasswordForm" :model="editPasswordForm" :label-width="100" label-position="right"
                  :rules="passwordValidate">
                <FormItem label="原密码" prop="password" :error="oldPassError">
                    <Input v-model="editPasswordForm.password" placeholder="请输入现在使用的密码"></Input>
                </FormItem>
                <FormItem label="新密码" prop="newPassword">
                    <Input type="password" v-model="editPasswordForm.newPassword" placeholder="请输入新密码，至少6位字符"></Input>
                </FormItem>
                <FormItem label="确认新密码" prop="rePassword">
                    <Input type="password" v-model="editPasswordForm.rePassword" placeholder="请再次输入新密码"></Input>
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="text" @click="cancelEditPass">取消</Button>
                <Button type="primary" :loading="savePassLoading" @click="saveEditPass">保存</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    import invokeSrv from "../../libs/invoke.service";
    import userSrv from "../../libs/user.service";
    export default {
        name: 'ownspace_index',
        data() {

            const valideRePassword = (rule, value, callback) => {
                if (value !== this.editPasswordForm.newPassword) {
                    callback(new Error('两次输入密码不一致'));
                } else {
                    callback();
                }
            };
            return {
                userForm: {
                    name: '',
                    phone: '',
                    userName: ''
                },
                save_loading: false,
                editPasswordModal: false, // 修改密码模态框显示
                savePassLoading: false,
                oldPassError: '',
                editPasswordForm: {
                    userName:'',
                    password: '',
                    newPassword: '',
                    rePassword: ''
                },
                passwordValidate: {
                    password: [
                        {required: true, message: '请输入原密码', trigger: 'blur'}
                    ],
                    newPassword: [
                        {required: true, message: '请输入新密码', trigger: 'blur'},
                        {min: 6, message: '请至少输入6个字符', trigger: 'blur'},
                        {max: 32, message: '最多输入32个字符', trigger: 'blur'}
                    ],
                    rePassword: [
                        {required: true, message: '请再次输入新密码', trigger: 'blur'},
                        {validator: valideRePassword, trigger: 'blur'}
                    ]
                },
            };
        },
        methods: {
            showEditPassword() {
                this.editPasswordModal = true;
            },
            cancelEditPass() {
                this.editPasswordModal = false;
            },
            saveEditPass() {
                let vm = this;
                vm.$refs['editPasswordForm'].validate((valid) => {
                    if (valid) {
                        userSrv.changePassword(vm.editPasswordForm).then(function(res) {
                            if(res&&res.data&&res.data==1) {
                                vm.$Message.success("密码修改成功，请重新登录！");
                                userSrv.logout().then(function (data) {
                                    // 退出登录
                                    vm.$store.commit('logout', vm);
                                    vm.$store.commit('clearOpenedSubmenu');
                                    vm.$router.push({
                                        name: 'login'
                                    });
                                });
                            }else{
                                if(res&&res.data&&res.data.err) {
                                    vm.$Message.error(res.data.err);
                                }else {
                                    vm.$Message.error("密码修改失败，请重试！");
                                }
                            }
                        }).catch(function(res) {
                            vm.$Message.error("密码修改异常！");
                        })
                    }
                });
            },
            init() {
                let vm = this;
                invokeSrv.getFnData(userSrv.getCurrentUser,null,function(data) {
                    vm.userForm = Object.assign({},data) ;
                    vm.editPasswordForm.userName = data.userName;
                })
            },
        },
        mounted() {
            this.init();
        }
    };
</script>
