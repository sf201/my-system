<template>
    <Modal
            v-model="myValue"
            title="选择发货单号"
            @on-ok="ok"
            @on-cancel="cancel"
            width="75%"
    >
        <Form :label-width="80">
            <Row>
                <Col span="6">
                <FormItem label="开始日期">

                    <DatePicker type="date" v-model="query.startDate"></DatePicker>
                </FormItem>
                </Col>
                <Col span="6">
                <FormItem label="结束日期">
                    <DatePicker type="date" v-model="query.stopDate"></DatePicker>
                </FormItem>
                </Col>
                <Col span="12">
                <Button type="primary" @click="search">查询</Button>
                </Col>
            </Row>
        </Form>
        <Table
                :columns="columns"
                :data="deliverList"
                @on-row-click="documentClicked"
                @on-row-dblclick="documentSelected"
                :height="200"
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
        name:'select-defective-document',
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
                        title:'发货单号',
                        key:'documentNo'
                    },
                    {
                        title:'发货日期',
                        key:'itemSpec'
                    },
                    {
                        title:'操作员',
                        key:'operator'
                    },
                    {
                        title:'地址',
                        key:'address'
                    },
                ],
                deliverList: [],
                rulesValidate: {
                    itemCode: [{required: true, type: 'string', message: '物品名称不能为空！', trigger: 'change'}]
                },
                query: {},
                documentNo:''
            }
        },
        props: ["value"],
        methods: {
            documentClicked(deliver) {
                this.documentNo = deliver.documentNo;
            },
            documentSelected(deliver) {
                this.documentNo = deliver.documentNo;
                this.ok();
            },
            search() {
                let vm = this;
                invokeSrv.getFnData(storeMgrSrv.getDeliverMasterList,vm.query,function(data) {
                    vm.deliverList = data;
                })
            },
            ok() {
                this.$emit("ok", this.documentNo);
                this.myValue = false;
            },
            cancel() {
                this.$emit("cancel");
                this.myValue = false;
            },
        },
    }
</script>