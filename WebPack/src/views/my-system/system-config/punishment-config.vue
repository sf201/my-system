<template>
    <Card>
        <p slot="title">
            <Icon type="cash"></Icon>
            <span>扣罚名录</span>
        </p>
        <div>
            <Button type="primary" size="small" @click="add">添加</Button>
        </div>
        <Table
                :columns="columns"
                :data="punbishmentDictList"
        >
        </Table>
        <Modal
                v-model="isShow"
                title="添加/编辑扣罚名录"
                @on-ok="ok"
                @on-cancel="cancel"
        >
            <Form :label-width="80">
                <FormItem label="序号">
                    <Input v-model="query.serialNo"></Input>
                </FormItem>
                <FormItem label="扣罚类别">
                    <Input v-model="query.name"></Input>
                </FormItem>
                <FormItem label="金额">
                    <Input v-model="query.costs"></Input>
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
                        key: 'name',
                        title: '扣罚类别'
                    },
                    {
                        key: 'costs',
                        title: '金额'
                    }
                ],
                punbishmentDictList: [],
            };
        },
        methods: {
            getPunishmentDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getPunishmentDict, null, function (data) {
                    vm.punbishmentDictList = data;
                })
            },
            init() {
                let vm = this;
                vm.getPunishmentDict();
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
                sysCfgSrv.savePunishmentDict(dict,"delete").then(function (result) {
                    if (result && result.data && result.data.status == '0') {
                        vm.punbishmentDictList.splice(dict._index, 1);
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
                sysCfgSrv.savePunishmentDict(vm.query).then(function (result) {
                    if (result && result.data && result.data.status == '0') {
                        vm.query = Object.assign({}, vm.query, result.data.data);
                        if (vm.isAdd) {
                            vm.punbishmentDictList.push(vm.query);
                        } else {
                            vm.punbishmentDictList.splice(vm.query._index, 1,vm.query);
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
