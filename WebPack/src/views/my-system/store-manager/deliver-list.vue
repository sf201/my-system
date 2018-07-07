<template>
    <Card>
        <p slot="title">发货单列表</p>
        <p slot="extra">
            <Button type="primary" @click="getDeliverList">查询</Button>
        </p>
        <Form :label-width="80">
            <Row>
                <Col span="6">
                <FormItem label="房源编号">
                    <Input v-model="query.houseResourceNo" ></Input>
                </FormItem>
                </Col>
                <Col span="6">
                <FormItem label="开始日期">
                    <DatePicker v-model="query.startDate" type="date"></DatePicker>
                </FormItem>
                </Col>
                <Col span="6">
                <FormItem label="结束日期">
                    <DatePicker v-model="query.stopDate" type="date"></DatePicker>
                </FormItem>
                </Col>
            </Row>
        </Form>
        <Table
                :columns="columns"
                :data="deliverList"
                height="500"
        ></Table>
    </Card>
</template>
<script>
    import invokeSrv from "../../../libs/invoke.service";
    import storeSrv from "../../../libs/storeManager.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";

    let storeFilter = window.Vue.filter("storeDict");
    let dateFilter = window.Vue.filter("date");
    export default {
        name: 'deliver-list',
        data() {
            return {
                columns: [
                    {
                        title: '操作',
                        render: (h, params) => {
                            return h("div", [
                                h("Button", {
                                    props: {
                                        size: 'small',
                                        type: 'primary'
                                    },
                                    on: {
                                        click: () => {
                                            this.view(params.row)
                                        }
                                    }
                                }, ["查看"])
                            ]);
                        }
                    },
                    {
                        title: '发货单号',
                        key: 'documentNo'
                    },
                    {
                        title: '发货日期',
                        key: 'deliverDateTime',
                        render: (h, params) => {
                            return h("div", dateFilter(params.row.deliverDateTime));
                        }
                    },
                    {
                        title: '房源编号',
                        key: 'houseResourceNo',
                    },
                ],
                deliverList: [],
                storeDict: {},
                query: {}
            }
        },
        methods: {
            view(document) {
                this.$router.push({name: 'deliver', params: {documentNo: document.documentNo}});
            },
            getDeliverList() {
                let vm = this;
                invokeSrv.getFnData(storeSrv.getDeliverMasterList, vm.query, function (data) {
                    vm.deliverList = data;
                })
            },
            getStoreDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getStoreDict, null, function (data) {
                    vm.storeDict = data;
                })
            }
        },
        mounted: function () {
            this.getStoreDict();
        }
    }
</script>