<template>
    <Modal
            v-model="myValue"
            title="添加/编辑入库明细"
            @on-ok="ok"
            @on-cancel="cancel"
            @on-visible-change="visibleChanged"
    >
        <Form ref="formItem" :label-width="80" :model="query" :rules="rulesValidate">
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
            <FormItem label="物品名称" prop="itemCode">
                <Input :value="query.itemCode|itemDict(itemDictList)" :readonly="true"></Input>
            </FormItem>
            <FormItem label="型号">
                {{query.itemSpec}}
            </FormItem>
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
        name:'select-import-item',
        watch: {
            item: function () {
                this.clearValue();
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
                itemDictList: [],
                rulesValidate: {
                    itemCode: [{required: true, type: 'string', message: '物品名称不能为空！', trigger: 'change'}]
                },
                query: {}
            }
        },
        props: ["value", "item"],
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
            getItemDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getItemDict, null, function (data) {
                    vm.itemDictList = data;
                    itemDictList = data;
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
                if (visible) {
                    this.$refs.dict.clearSingleSelect();
                    this.$refs.formItem.resetFields();
                    this.clearValue();
                }
            },
            init() {
                this.clearValue();
                this.getItemDict();
            },
            clearValue() {
                this.query = Object.assign({}, this.item);
            }
        },
        mounted: function () {
            this.init();
        }
    }
</script>