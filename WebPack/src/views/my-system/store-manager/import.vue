<style>
    .unAcked {
        color: red;
    }

    .acked {
        color: green;
    }
</style>
<template>
    <Card>
        <p slot="title">
            <Icon type="android-globe"></Icon>
            <span>入库单管理</span>
        </p>
        <p slot="extra">
            <Button type="primary" @click="newImport">新建</Button>
            <Button type="primary" @click="save">保存</Button>
            <Button type="primary" @click="ackImport">确认入库</Button>
        </p>
        <Form ref="formMaster" :model="master" :rules="rulesValidate" :label-width="80">
            <Row>
                <Col span="12">
                <FormItem label="入库单号">
                    <Input :readonly="true" :value="master.documentNo" placeholder="保存后自动生成入库单号"></Input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="入库日期" prop="importDateTime">
                    <DatePicker type="date" v-model="master.importDateTime"></DatePicker>
                </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="12">
                <FormItem label="仓库" prop="storeCode">
                    <Select v-model="master.storeCode">
                        <Option v-for="item in storeDictList"
                                :label="item.name"
                                :value="item.serialNo"
                                :key="item.serialNo"
                        ></Option>
                    </Select>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="来源">
                    <Input v-model="master.importFrom"></Input>
                </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="12">
                <FormItem label="操作员">
                    <Input v-model="master.operator"></Input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="确认状态">
                    <div :class="[master.acked?'acked':'unAcked']">{{master.acked==true?'已确认':'未确认'}}</div>
                </FormItem>
                </Col>
            </Row>
        </Form>
        <div>
            <Button type="primary" size="small" @click="add">添加</Button>
        </div>
        <Table
                :columns="columns"
                :data="master.listDetail"
        >
        </Table>
        <selectImportItem
                v-model="isShow"
                :item="query"
                @item-selected="ok"
                @cancel="cancel"
        >

        </selectImportItem>
    </Card>
</template>

<script>
    import invokeSrv from "../../../libs/invoke.service";
    import storeMgrSrv from "../../../libs/storeManager.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";
    import selectImportItem from "../template/select-import-item.vue";

    let itemDictList = [];
    let itemDictFilter = Vue.filter("itemDict");

    export default {
        name: 'import',
        components:{
            selectImportItem,
        },
        data() {
            return {
                isShow: false,
                isAdd: false,
                master: {
                    listDetail: [],
                    listDetailDelete: [],
                    acked: false,
                    importDateTime: '',
                },
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
                                h("Poptip", {
                                    props: {
                                        confirm: true,
                                        title: '您确定要删除该条记录吗?'
                                    },
                                    on: {
                                        "on-ok": () => {
                                            this.delete(params.row);
                                        },
                                        "on-cancel": () => {

                                        }
                                    }
                                }, [h("Button", {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    }
                                }, "删除")]),

                            ])
                        }
                    },
                    {
                        key: 'itemCode',
                        title: '物品编码'
                    },
                    {
                        key: 'itemName',
                        title: '物品名称',
                        render: (h, params) => {
                            return h("div", itemDictFilter(params.row.itemCode, itemDictList));
                        }
                    },
                    {
                        key: 'itemSpec',
                        title: '型号'
                    },
                    {
                        key: 'amount',
                        title: '数量'
                    },
                    {
                        key: 'memo',
                        title: '备注'
                    }
                ],
                importList: [],
                itemDictList: [],
                storeDictList: [],
                rulesValidate: {
                    importDateTime: [
                        {required: true, type: 'date', message: '入库日期不能为空！', trigger: 'change'}
                    ],
                    storeCode: [
                        {required: true, message: '仓库不能为空！', trigger: 'change'}
                    ],
                }
            };
        },
        methods: {
            newImport() {
                this.clearValue();
                this.$refs["formMaster"].resetFields();
            },

            ackImport() {
                let vm = this;
                storeMgrSrv.ackImport({documentNo: vm.master.documentNo}).then(function (result) {
                    if (result && result.data && result.data == '1') {
                        vm.$set(vm.master, "acked", true);
                        vm.$Message.success("确认成功！");
                        console.log(vm.master);
                    } else {
                        if (result && result.data && result.data.err) {
                            vm.$Message.error("确认失败！原因：" + result.data.err);
                        } else {
                            vm.$Message.err("确认失败！原因未知")
                        }
                    }
                }).catch(function (result) {
                    vm.$Message.error("确认异常！");
                })
            },
            save() {
                let vm = this;
                vm.$refs.formMaster.validate((valid) => {
                    console.log(valid);
                    if (valid) {
                        storeMgrSrv.saveItemImport(vm.master).then(function (result) {
                            if (result && result.data && result.data.id) {
                                vm.master = Object.assign({}, vm.master, result.data,{importDateTime:new Date(result.data.importDateTime)});
                                vm.$Message.success("保存成功！");
                            } else {
                                if (result && result.data && result.data.err) {
                                    vm.$Message.error("保存失败！原因：" + result.data.err);
                                } else {
                                    vm.$Message.error("保存失败，原因未知！");
                                }
                            }
                        }).catch(function (result) {
                            vm.$Message.error("保存异常！" + result);
                        })
                    }
                })
            },
            getItemDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getItemDict, null, function (data) {
                    vm.itemDictList = data;
                    itemDictList = data;
                })
            },
            getStoreDict() {
                let vm = this;
                let query = {storeProperty: '0'};
                invokeSrv.getFnData(sysCfgSrv.getStoreDict, query, function (data) {
                    vm.storeDictList = data;

                })
            },
            init() {
                let vm = this;
                vm.getStoreDict();
                vm.getItemDict();
                vm.updateInfo();
            },
            add() {
                this.query = {itemCode:'',itemSpec:''};
                this.isShow = true;
                this.isAdd = true;
            },
            edit(dict) {
                let vm = this;
                vm.query = dict;
//                vm.$refs["dict"].setQuery(dict.itemName);
                this.isAdd = false;
                this.isShow = true;
            },
            delete(detail) {
                let vm = this;
                console.log(detail);
                if (detail.id) {
                    vm.master.listDetailDelete.push(detail.id);
                }
                vm.master.listDetail.splice(detail._index, 1);
            },
            ok(query) {
                let vm = this;
                if (vm.isAdd) {
                    vm.master.listDetail.push(query);
                } else {
                    vm.master.listDetail.splice(query._index, 1, query);
                }
            },
            cancel() {
                this.isShow = false;
            },
            clearValue() {
                this.master = {
                    listDetail: [],
                    listDetailDelete: [],
                    acked: false,
                    importDateTime: ''
                };
                this.query = {};
            },
            updateInfo() {
                let vm = this;
                vm.clearValue();
                let query = {documentNo: this.$route.params.documentNo};
                if(query.documentNo!=null&&query.documentNo!=undefined) {

                    invokeSrv.getFnData(storeMgrSrv.getItemImportMasterList, query, function (data) {
                        console.log(data);
                        if (data != null && data.length == 1) {
                            vm.master = Object.assign( vm.master, data[0]);
                            invokeSrv.getFnData(storeMgrSrv.getItemImportDetailList, query, function (data) {
                                let details = data == null ? [] : data;
                                vm.master = Object.assign({},vm.master, {listDetail: details});
                            })
                        }
                    })
                }else {
                    console.log("no query params");
                }

            }
        },
        mounted: function () {
            this.init();
        },
        watch: {
            '$route'() {
                this.updateInfo();
            }
        }
    }
</script>
