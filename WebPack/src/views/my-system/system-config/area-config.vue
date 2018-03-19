<template>
    <Card>
        <p slot="title">
            <Icon type="android-globe"></Icon>
            <span>区域名录</span>
        </p>
        <div>
            <Button type="primary" size="small" @click="add">添加</Button>
        </div>
        <Table
                :columns="columns"
                :data="areaDictList"
        >
        </Table>
        <Modal
                v-model="isShow"
                title="添加/编辑区域名录"
                @on-ok="ok"
                @on-cancel="cancel"
        >
            <Form :label-width="80">
                <FormItem label="序号">
                    <Input v-model="query.serialNo"></Input>
                </FormItem>
                <FormItem label="区域名称">
                    <Input v-model="query.areaName"></Input>
                </FormItem>
            </Form>
        </Modal>
    </Card>
</template>

<script>
    import invokeSrv from "../../../libs/invoke.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";

    export default {
        data() {
            return {
                isShow: false,
                isAdd: false,
                query: {},
                columns: [
                    {
                        key: 'action',
                        title: '操作',
                        render: (h, params) => {
                            return h("div", [
                                h("Button", {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.edit(params.row);
                                        }
                                    }
                                }, "编辑"),
                                h("Poptip",{
                                    props:{
                                        confirm:true,
                                        title:'您确定要删除该条记录吗?'
                                    },
                                    on:{
                                        "on-ok":()=> {
                                            this.delete(params.row);
                                        },
                                        "on-cancel":()=> {

                                        }
                                    }
                                },[h("Button", {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    }
                                }, "删除")]),

                            ])
                        }
                    },
                    {
                        key: 'serialNo',
                        title: '序号'
                    },
                    {
                        key: 'areaName',
                        title: '区域名称'
                    }
                ],
                areaDictList: [],
            };
        },
        methods: {
            getAreaDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getAreaDict, null, function (data) {
                    vm.areaDictList = data;
                })
            },
            init() {
                let vm = this;
                vm.getAreaDict();
            },
            add() {
                this.query = {};
                this.isShow = true;
                this.isAdd = true;
            },
            edit(dict) {
                this.query = dict;
                this.isAdd = false;
                this.isShow = true;
            },
            delete(dict) {
                let vm = this;
                console.log(dict);
                sysCfgSrv.saveAreaDict(dict,"delete").then(function (result) {
                    if (result && result.data && result.data.status == '0') {
                        vm.areaDictList.splice(dict._index, 1);
                        vm.$Message.success("删除成功!");
                    } else {
                        vm.$Message.error("删除失败!");
                    }
                }).catch(function (result) {
                    vm.$Message.error("删除异常!" + result);
                })
            },
            ok() {
                let vm = this;
                sysCfgSrv.saveAreaDict(vm.query).then(function (result) {
                    if (result && result.data && result.data.status == '0') {
                        vm.query = Object.assign({}, vm.query, result.data.data);
                        if (vm.isAdd) {
                            vm.areaDictList.push(vm.query);
                        } else {
                            vm.areaDictList.splice(vm.query._index, 1,vm.query);
                        }
                        vm.isShow = false;
                        vm.$Message.success("保存成功!");
                    } else {
                        vm.$Message.error("保存失败!" + result.data.msg);
                    }
                }).catch(function (result) {
                    vm.$Message.error("保存失败!" + result.data.msg);
                })
            },
            cancel() {
                this.isShow = false;
            }
        },
        mounted: function () {
            this.init();
        },
    }
</script>
