<template>
    <Modal
            v-model="myValue"
            title="添加/编辑出库明细"
            @on-ok="ok"
            @on-cancel="cancel"
            @on-visible-change="visibleChanged"
    >
        <Form ref="formItem" :model="query" :label-width="80" :rules="rulesValidate">
            <FormItem label="选择名称">
                <Select ref="dict"
                        :label-in-value="true"
                        @on-change="itemSelected"
                        filterable
                        clearable
                        :value="query.itemName">
                    <Option v-for="item in itemDictList" :key="item.id" :value="item.itemCode+'||'+item.itemSpec"
                            :label="item.itemName">
                        <span>{{item.itemName}}</span>
                        <span style="float:right;color:#ccc">{{item.inputCode}}</span>
                    </Option>
                </Select>
            </FormItem>
            <Row>
                <Col span="8">
                <FormItem label="物品名称" prop="itemCode">
                    <Input :readonly="true" :value="query.itemCode|itemDict(itemDictList)"></Input>
                </FormItem>
                </Col>
                <Col span="8">
                <FormItem label="型号">
                    {{query.itemSpec}}
                </FormItem>
                </Col>
                <Col span="8">
                <FormItem label="库存">
                    <span style="color: red">{{query.storeAmount}}</span>
                </FormItem>
                </Col>
            </Row>
            <FormItem label="数量">
                <Input v-model="query.amount"></Input>
            </FormItem>
            <FormItem label="备注">
                <Input v-model="query.memo"></Input>
            </FormItem>
        </Form>
        <div slot="footer">
            <Button type="text" @click="cancel">取消</Button>
            <Button type="primary" @click="ok">确定</Button>
        </div>
    </Modal>
</template>
<script>
    import invokeSrv from "../../../libs/invoke.service";
    import storeMgrSrv from "../../../libs/storeManager.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";

    let itemDictList = [];
    let itemDictFilter = Vue.filter("itemDict");
    export default {
        name:'select-export-item',
        watch: {
            item:function() {
                this.query = Object.assign({}, this.item);
            },
            "storeCode": function () {
                let query = {storeCode: this.storeCode}
                this.query = Object.assign({}, this.query, query);
                this.getItemDict(query);
            },
            "query.itemCode":function() {
                this.refreshStore();
            }
        },
        computed: {
            myValue: {
                get() {
                    return this.value;
                },
                set(v) {
                    this.$emit("input", v);
                }
            },
        },
        data() {
            return {
                query:{},
                storeDictList: [],
                itemDictList: [],
                rulesValidate: {
                    itemCode: [
                        {required: true, type: 'string', message: '出库物品不能为空！', trigger: 'change'}
                    ]
                }
            }
        },
        props: ["value", "item","storeCode"],
        methods: {
            itemSelected(data) {
                let vm = this;
                if (data.value == undefined || data.value == null || data.value == "") {
                    return;
                }
                vm.$set(vm.query, "itemCode", data.value.substr(0, data.value.indexOf("||")));
//                vm.query.itemCode = data.value.substr(0,data.value.indexOf("||"));
                vm.$set(vm.query, "itemSpec", data.value.substr(data.value.indexOf("||") + 2));
//                vm.query.itemSpec = data.value.substr(data.value.indexOf("||")+2);

            },
            getItemDict(query) {
                let vm = this;
                if(query.storeCode==undefined||query.storeCode==null||query.storeCode=="") {
                    return;
                }
                invokeSrv.getFnData(storeMgrSrv.getItemDictBySoreCode, query, function (data) {
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
            refreshStore() {
                let vm = this;
                if (vm.query.itemCode == undefined || vm.query.itemCode == null || vm.query.itemCode == "") {
                    vm.$set(vm.query, "storeAmount", "");
                    return;
                }
                invokeSrv.getFnData(storeMgrSrv.getItemStockList, vm.query, function (data) {
                    if (data != null && data.length == 1) {
                        vm.$set(vm.query, "storeAmount", data[0].amount);
                    }
                })
            },
            ok() {
                let vm = this;
                vm.$refs.formItem.validate((valid) => {
                    if (valid) {
                        let query = Object.assign({}, vm.query);
                        vm.$emit("item-selected", query);
                        vm.myValue = false;
                    }
                })
            },
            cancel() {
                this.$emit("cancel");
                this.myValue = false;
            },
            visibleChanged(visible) {
                if(visible) {
                    this.init();
                    this.$refs.dict.clearSingleSelect();
                    this.$refs.formItem.resetFields();
                }
            },
            init() {
                this.query = Object.assign({}, this.item,{storeCode:this.storeCode});
                this.getStoreDict();
            }
        },
        mounted: function () {
            this.init();
        }
    }
</script>