<template>
    <Modal
            title="添加/编辑用户"
            v-model="myValue"
            @on-visible-change="visibleChanged"
    >
        <Form :label-width="80" ref="formUser" :model="myUser" :rules="userRules">
            <Row>
                <FormItem label="用户名" prop="userName">
                    <Input v-model="myUser.userName"></Input>
                </FormItem>
            </Row>
            <Row>
                <FormItem label="姓名" prop="name">
                    <Input v-model="myUser.name"></Input>
                </FormItem>
            </Row>
            <Row>
                <FormItem label="密码" >
                    <Input v-model="myUser.password"></Input>
                </FormItem>
            </Row>
        </Form>
        <Alert type="error" v-show="error" show-icon>{{msg}}</Alert>
        <div slot="footer">
            <Button type="text" @click="myValue=false">取消</Button>
            <Button type="primary" @click="ok">确定</Button>
        </div>
    </Modal>
</template>
<script>
    import userSrv from "../../../libs/user.service";
    export default {
        name: 'addUser',
        computed: {
            myValue: {
                get() {
                    return this.value;
                },
                set(v) {
                    this.$emit("input", v);
                }
            }
        },
        props: ["user", "value"],
        watch: {
            user: function () {
                this.myUser = Object.assign({}, this.user,{password:''});
            },
        },
        data() {
            return {
                error: false,
                msg: '',
                myUser: {},
                userList: [],
                userRules: {
                    userName: [{required: true, message: '用户名不能为空!', trigger: 'blur'}],
                    name: [{required: true, message: '姓名不能为空!', trigger: 'blur'}],
                },
            }
        },
        methods: {
            visibleChanged(visible) {
                if (visible) {
                    this.$refs.formUser.resetFields();
                    this.init();
                }
            },
            handleSelect: function (value) {
                this.myUser.deptCode = value;
            },
            init() {
                let vm = this;
                vm.myUser = Object.assign({}, vm.user,{password:''});
            },
            ok() {
                let vm = this;
                vm.$refs.formUser.validate((valid) => {
                    if (valid) {
                        userSrv.add(vm.myUser).then(function(res) {
                            if(res&&res.data&&res.data.id) {
                                vm.$emit("ok", Object.assign({}, vm.myUser));
                                vm.myValue=false;
                            }else {
                                vm.error = true;
                                vm.msg = "新增用户失败！";
                                if(res.data.err) {
                                    vm.msg += res.data.err;
                                }
                                setTimeout(() => {
                                    vm.error = false;
                                }, 2000);
                            }
                        }).catch(function(res) {
                            vm.error = true;
                            vm.msg = "新增用户失败！";
                            setTimeout(() => {
                                vm.error = false;
                            }, 2000);
                        })
                    }
                })
            }
        },
        mounted: function () {
            this.init();
        },
    };
</script>