<template>
    <Modal
            v-model="myValue"
            title="添加/编辑不良品明细"
            @on-ok="ok"
            @on-cancel="cancel"
            @on-visible-change="visibleChanged"
    >
        <Table
                :columns="columns"
                :data="itemList"
                @on-selection-change="itemSelected"
        >

        </Table>
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
        name:'select-defective-item',
        watch: {
            documentNo: function () {
                this.getDeliverDetailList(this.documentNo);
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
                columns:[
                    {
                        title:'选择',
                        type:'selection'
                    },
                    {
                        title:'药品名称',
                        render:(h,params)=> {
                            return h("div", itemDictFilter(params.row.itemCode, itemDictList));
                        }
                    },
                    {
                        title:'规格',
                        key:'itemSpec'
                    },
                    {
                        title:'数量',
                        key:'amount',
                    },
                    {
                        title:'备注',
                        key:'memo'
                    }
                ],
                itemList: [],
                rulesValidate: {
                    itemCode: [{required: true, type: 'string', message: '物品名称不能为空！', trigger: 'change'}]
                },
                query: {},
                itemDictList:[],
                selectedItem:[]
            }
        },
        props: ["value", "documentNo"],
        methods: {

            getItemDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getItemDict, null, function (data) {
                    vm.itemDictList = data;
                    itemDictList = data;
                })
            },
            getDeliverDetailList(documentNo) {
                let vm = this;
                if(documentNo==undefined||documentNo==null||documentNo=="") {
                    return;
                }
                let query = {documentNo};
                invokeSrv.getFnData(storeMgrSrv.getDeliverDetailList,query,function(data) {
                    vm.itemList = data;
                })
            },
            itemSelected(selection) {
                this.selectedItem = selection;
            },
            ok() {
                let vm = this;
                if(vm.selectedItem.length>0) {
                    vm.$emit("item-selected", vm.selectedItem);
                    vm.myValue = false;
                }
            },
            cancel() {
                this.$emit("cancel");
                this.myValue = false;
            },
            visibleChanged(visible) {
                if (visible) {
                    this.getDeliverDetailList(this.documentNo)
                }
            },
            init() {
                this.getItemDict();
            },
        },
        mounted: function () {
            this.init();
        }
    }
</script>