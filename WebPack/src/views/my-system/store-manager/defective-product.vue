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
            <span>不良品登记</span>
        </p>
        <p slot="extra">
            <Button type="primary" @click="newDefectiveProduct">新建</Button>
            <Button type="primary" @click="save">保存</Button>
            <Button type="primary" @click="ackDefectiveProduct">确认登记</Button>
        </p>
        <Form ref="formMaster" :model="master" :rules="rulesValidate" :label-width="80">
            <Row>
                <Col span="12">
                <FormItem label="不良品登记单号">
                    <Input :readonly="true" :value="master.documentNo" placeholder="保存后自动生成不良品单号"></Input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="登记日期" prop="damageDateTime">
                    <DatePicker type="date" v-model="master.damageDateTime"></DatePicker>
                </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="10">
                <FormItem label="发货单号">
                    <Input v-model="master.deliverDocumentNo"></Input>
                </FormItem>
                </Col>
                <Col span="2">
                <Button type="primary" @click="isShowDocument = true">...</Button>
                </Col>
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
            <Button type="primary" size="small" @click="add">选择</Button>
        </div>
        <Table
                :columns="columns"
                :data="master.listDetail"
        >
        </Table>
        <selectDefectiveItem
                v-model="isShow"
                :documentNo="master.deliverDocumentNo"
                @item-selected="ok"
                @cancel="cancel"
        >

        </selectDefectiveItem>
        <selectDefectiveDocument
                v-model="isShowDocument"
                @ok="okDocument"
                @cancel="cancelDocument"
        ></selectDefectiveDocument>
    </Card>
</template>

<script>
    import invokeSrv from "../../../libs/invoke.service";
    import storeMgrSrv from "../../../libs/storeManager.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";
    import selectDefectiveItem from "../template/select-defective-item.vue";
    import selectDefectiveDocument from "../template/select-defective-document.vue";

    let itemDictList = [];
    let itemDictFilter = Vue.filter("itemDict");

    export default {
        name: 'defective-product',
        components:{
            selectDefectiveItem,
            selectDefectiveDocument
        },
        computed:{
          readOnly:function() {
            return this.master.acked;
          }
        },
        data() {
            return {
                isShowDocument:false,
                isShow: false,
                master: {
                    listDetail: [],
                    listDetailDelete: [],
                    acked: false,
                    damageDateTime: '',
                },
                query: {},
                columns: [
                    {
                        key: 'action',
                        title: '操作',
                        render: (h, params) => {
                            return h("div", [
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
                        title: '不良数量',
                        render:(h,params)=> {
                            if(this.readOnly) {
                                return h("div", params.row.amount);
                            }else {
                                return h("div",[
                                    h("Input",{
                                        props:{
                                            size:'small',
                                            value:params.row.amount
                                        },
                                        on:{
                                            'input':(value)=> {
                                                this.master.listDetail.splice(params.row._index,1, Object.assign({}, params.row, {amount: value}));
                                            }
                                        }
                                    })
                                ])
                            }
                        }
                    },
                    {
                        key: 'memo',
                        title: '备注'
                    }
                ],
                defectiveProductList: [],
                itemDictList: [],
                storeDictList: [],
                rulesValidate: {
                    damageDateTime: [
                        {required: true, type: 'date', message: '登记日期不能为空！', trigger: 'change'}
                    ],
                    storeCode: [
                        {required: true, message: '仓库不能为空！', trigger: 'change'}
                    ],
                }
            };
        },
        methods: {
            newDefectiveProduct() {
                this.clearValue();
                this.$refs["formMaster"].resetFields();
            },

            ackDefectiveProduct() {
                let vm = this;
                storeMgrSrv.ackDefectiveProduct({documentNo: vm.master.documentNo}).then(function (result) {
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
                    if (valid) {
                        storeMgrSrv.saveDefectiveProduct(vm.master).then(function (result) {
                            if (result && result.data && result.data.id) {
                                vm.master = Object.assign({}, vm.master, result.data,{damageDateTime:new Date(result.data.damageDateTime)});
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
                let query = {storeProperty: '1'};
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
                this.query = {};
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
            ok(selectedList) {
                this.master.listDetail = selectedList;
            },
            cancel() {
                this.isShow = false;
            },
            okDocument(documentNo) {
                this.$set(this.master, "deliverDocumentNo", documentNo);
            },
            cancelDocument() {
              this.isShowDocument = false;
            },
            clearValue() {
                this.master = {
                    listDetail: [],
                    listDetailDelete: [],
                    acked: false,
                    damageDateTime: ''
                };
                this.query = {};
            },
            updateInfo() {
                let vm = this;
                vm.clearValue();
                let query = {documentNo: this.$route.params.documentNo};
                if(query.documentNo!=null&&query.documentNo!=undefined) {

                    invokeSrv.getFnData(storeMgrSrv.getDefectiveProductMasterList, query, function (data) {
                        console.log(data);
                        if (data != null && data.length == 1) {
                            vm.master = Object.assign( vm.master, data[0]);
                            invokeSrv.getFnData(storeMgrSrv.getDefectiveProductDetailList, query, function (data) {
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
