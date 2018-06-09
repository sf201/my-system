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
            <span>不良品修复管理</span>
        </p>
        <p slot="extra">
            <Button type="primary" @click="newRepair">新建</Button>
            <Button type="primary" @click="save">保存</Button>
            <Button type="primary" @click="ackRepair">确认修复</Button>
        </p>
        <Form ref="formMaster" :model="master" :rules="rulesValidate" :label-width="80">
            <Row>
                <Col span="12">
                <FormItem label="修复单号">
                    <Input :readonly="true" :value="master.documentNo" placeholder="保存后自动生成报损单号"></Input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="修复日期" prop="repairDateTime">
                    <DatePicker type="date" v-model="master.repairDateTime"></DatePicker>
                </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="12">
                <FormItem label="不良品仓库" prop="defectiveStoreCode">
                    <Select v-model="master.defectiveStoreCode">
                        <Option v-for="item in defectiveStoreDictList"
                                :label="item.name"
                                :value="item.serialNo"
                                :key="item.serialNo"
                        ></Option>
                    </Select>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="修复后入仓" prop="storeCode">
                    <Select v-model="master.storeCode">
                        <Option v-for="item in storeDictList"
                                :label="item.name"
                                :value="item.serialNo"
                                :key="item.serialNo"
                        ></Option>
                    </Select>
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
            <Row>

                <Col span="12">
                <FormItem label="备注" prop="memo">
                    <Input v-model="master.memo"></Input>
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

        <selectDefectiveRepairItem
                v-model="isShow"
                :item="query"
                :storeCode="master.defectiveStoreCode"
                @item-selected="ok"
                @cancel="cancel"
        >

        </selectDefectiveRepairItem>
    </Card>
</template>

<script>
    import invokeSrv from "../../../libs/invoke.service";
    import storeMgrSrv from "../../../libs/storeManager.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";

    import selectDefectiveRepairItem from "../template/select-defective-repair-item.vue";

    let itemDictList = [];
    let itemDictFilter = Vue.filter("itemDict");
    let dictFilter = window.Vue.filter("dict");

    export default {
        name:'export',
        components: {
            selectDefectiveRepairItem
        },
        data() {
            return {
                isShow: false,
                isAdd: false,
                master: {
                    listDetail: [],
                    listDetailDelete: [],
                    acked: false,
                    repairDateTime: '',
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
                repairList: [],
                itemDictList: [],
                defectiveStoreDictList: [],
                storeDictList: [],
                rulesValidate: {
                    repairDateTime: [
                        {required: true, type: 'date', message: '修复日期不能为空！', trigger: 'change'}
                    ],
                    storeCode: [
                        {required: true, type: 'string', message: '修复后入仓不能为空！', trigger: 'change'}
                    ],
                    defectiveStoreCode: [
                        {required: true, type: 'string', message: '不良品仓库不能为空！', trigger: 'change'}
                    ],
                },
            };
        },
        methods: {
            newRepair() {
                this.clearValue();
                this.$refs["formMaster"].resetFields();
            },
            ackRepair() {
                let vm = this;
                storeMgrSrv.ackDefectiveRepair({documentNo: vm.master.documentNo}).then(function (result) {
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
                    console.log(vm.master);
                    if (valid) {
                        storeMgrSrv.saveDefectiveRepair(vm.master).then(function (result) {
                            if (result && result.data && result.data.id) {
                                vm.master = Object.assign({}, vm.master, result.data,{repairDateTime:new Date(result.data.deliverDateTime)});
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
            getDefectiveStoreDict() {
                let vm = this;
                let query = {storeProperty: '1'};
                invokeSrv.getFnData(sysCfgSrv.getStoreDict, query, function (data) {
                    vm.defectiveStoreDictList = data;

                })
            },
            init() {
                let vm = this;
                vm.getStoreDict();
                vm.getDefectiveStoreDict();
                vm.getItemDict();
                vm.updateInfo();
            },
            add() {
                this.query = {};
                this.isShow = true;
                this.isAdd = true;
            },
            edit(dict) {
                let vm = this;
                vm.query = dict;
                this.isAdd = false;
                this.isShow = true;
            },
            delete(detail) {
                let vm = this;
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
                    repairDateTime: ''
                };
                this.query = {};
            },
            updateInfo() {
                let vm = this;
                vm.clearValue();
                let query = {documentNo: this.$route.params.documentNo};
                if (query.documentNo != null && query.documentNo != undefined) {

                    invokeSrv.getFnData(storeMgrSrv.getDefectiveRepairMasterList, query, function (data) {
                        console.log(data);
                        if (data != null && data.length == 1) {
                            vm.master = Object.assign(vm.master, data[0]);
                            invokeSrv.getFnData(storeMgrSrv.getDefectiveRepairDetailList, query, function (data) {
                                let details = data == null ? [] : data;
                                vm.master = Object.assign({}, vm.master, {listDetail: details});
                            })
                        }
                    })
                } else {
                    console.log("no query params");
                }

            }
        },
        mounted: function () {
            this.init();
        },
        watch:{
            '$route'() {
                this.updateInfo();
            }
        }
    }
</script>
