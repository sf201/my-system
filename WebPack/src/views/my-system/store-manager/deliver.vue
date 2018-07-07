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
            <span>发货管理</span>
        </p>
        <p slot="extra">
            <Button type="primary" @click="newDeliver">新建</Button>
            <Button type="primary" @click="save">保存</Button>
            <Button type="primary" @click="ackDeliver">确认发货</Button>
        </p>
        <Form ref="formMaster" :model="master" :rules="rulesValidate" :label-width="80">
            <Row>
                <Col span="12">
                <FormItem label="发货单号">
                    <Input :readonly="true" :value="master.documentNo" placeholder="保存后自动生成入库单号"></Input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="发货日期" prop="deliverDateTime">
                    <DatePicker type="date" v-model="master.deliverDateTime"></DatePicker>
                </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="10">
                <FormItem label="房源编号" prop="houseResourceNo">
                    <Input :readonly="true" placeholder="点击右侧按键先把房源编号" v-model="master.houseResourceNo"></Input>
                </FormItem>
                </Col>
                <Col span="2">
                <Button type="primary" @click="showSelectHouse = true">...</Button>
                </Col>
                <Col span="12">
                <FormItem label="地址">
                    <Input v-model="master.address"></Input>
                </FormItem>
                </Col>
            </Row>
            <Row>
                <Col span="12">
                <FormItem label="钥匙或密码" prop="keyCode">
                    <Input v-model="master.keyCode"></Input>
                </FormItem>
                </Col>
                <Col span="12">
                <FormItem label="联系电话" prop="phoneNo">
                    <Input v-model="master.phoneNo"></Input>
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
        <Modal
            v-model="showSelectHouse"
            title="选择房源"
            @on-ok="houseSelected"
            @cancel="showSelectHouse = false"
            width="75%"
        >
            <Form :label-width="80">
                <Row>
                    <Col span="20">

                    <FormItem label="小区名称">
                        <Input v-model="houseQuery.communityName" clearable></Input>
                    </FormItem>
                    </Col>
                    <Col span="4">
                    <Button type="primary" @click="getHouseList">查询</Button>
                    </Col>
                </Row>

            </Form>
            <Table
                    :columns="houseColumns"
                    :data="houseList"
                    highlight-row
                    @on-current-change="houseSelectChange"
                    @on-row-dblclick="houseDblClicked"
                    :height="300"
            ></Table>
        </Modal>

        <selectDeliverItem
                v-model="isShow"
                :item="query"
                @item-selected="ok"
                @cancel="cancel"
        >

        </selectDeliverItem>
    </Card>
</template>

<script>
    import invokeSrv from "../../../libs/invoke.service";
    import storeMgrSrv from "../../../libs/storeManager.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";

    import selectDeliverItem from "../template/select-deliver-item.vue";

    let itemDictList = [];
    let itemDictFilter = Vue.filter("itemDict");
    let dictFilter = window.Vue.filter("dict");

    export default {
        name:'deliver',
        components: {
            selectDeliverItem
        },
        data() {
            return {
                isShow: false,
                isAdd: false,
                master: {
                    listDetail: [],
                    listDetailDelete: [],
                    acked: false,
                    deliverDateTime: '',
                },
                query: {},
                showSelectHouse: false,
                houseQuery: {},
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
                houseColumns: [
                    {
                        title: '房源编号',
                        key: 'houseResourceNo'
                    },
                    {
                        title: '小区',
                        key: 'communityName'
                    },
                    {
                        title: '栋号',
                        key: 'building',
                    },
                    {
                        title: '单元',
                        key: 'unit'
                    },
                    {
                        title: '房号',
                        key: 'roomNo'
                    }
                ],
                houseList: [],
                deliverList: [],
                itemDictList: [],
                storeDictList: [],
                areaDict: [],
                rulesValidate: {
                    deliverDateTime: [
                        {required: true, type: 'date', message: '发货日期不能为空！', trigger: 'change'}
                    ],
                    houseResourceNo: [
                        {required: true, type: 'string', message: '房源编号不能为空！', trigger: 'change'}
                    ],
                    keyCode: [
                        {required: true, type: 'string', message: '钥匙或密码不能为空！', trigger: 'blur'}
                    ],
                    phoneNo: [
                        {required: true, type: 'string', message: '联系电话不能为空！', trigger: 'blur'}
                    ],
                },
                rulesValidateItem: {
                    itemCode: [
                        {required: true, type: 'string', message: '发货物品不能为空！', trigger: 'change'}
                    ]
                }
            };
        },
        methods: {
            houseSelectChange(newValue, oldValue) {
                console.log(newValue);
                this.houseQuery.house = newValue;
            },
            houseDblClicked(house, index) {
                this.master.houseResourceNo = house.houseResourceNo;
                this.master.address = dictFilter(house.areaCode, this.areaDict) + house.communityName + house.building + house.unit + house.roomNo;
                this.showSelectHouse = false;
            },
            houseSelected() {
                if (this.houseQuery.house) {
                    let house = this.houseQuery.house;
                    this.master.memo = house.memo;
                    this.master.address = dictFilter(house.areaCode, this.areaDict) + house.communityName + house.building + house.unit + house.roomNo;
                }
            },
            getHouseList() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getHouseResource, vm.houseQuery, function (data) {
                    vm.houseList = data;
                });
            },
            newDeliver() {
                this.clearValue();
                this.$refs["formMaster"].resetFields();
            },
            ackDeliver() {
                let vm = this;
                storeMgrSrv.ackDeliver({documentNo: vm.master.documentNo}).then(function (result) {
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
                        storeMgrSrv.saveDeliver(vm.master).then(function (result) {
                            if (result && result.data && result.data.id) {
                                vm.master = Object.assign({}, vm.master, result.data,{deliverDateTime:new Date(result.data.deliverDateTime)});
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
                invokeSrv.getFnData(sysCfgSrv.getStoreDict, null, function (data) {
                    vm.storeDictList = data;

                })
            },
            getAreaDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getAreaDict, null, function (data) {
                    vm.areaDict = data == null ? [] : data.map((item) => {
                        return {code: item.serialNo, name: item.areaName};
                    });
                })
            },
            init() {
                let vm = this;
                vm.getStoreDict();
                vm.getAreaDict();
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
                    deliverDateTime: ''
                };
                this.query = {};
            },
            updateInfo() {
                let vm = this;
                vm.clearValue();
                let query = {documentNo: this.$route.params.documentNo};
                if (query.documentNo != null && query.documentNo != undefined) {

                    invokeSrv.getFnData(storeMgrSrv.getDeliverMasterList, query, function (data) {
                        console.log(data);
                        if (data != null && data.length == 1) {
                            vm.master = Object.assign(vm.master, data[0]);
                            invokeSrv.getFnData(storeMgrSrv.getDeliverDetailList, query, function (data) {
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
