<template>
    <Card>
        <p slot="title">出库单列表</p>
        <p slot="extra">
            <Button type="primary" @click="getExportList">查询</Button>
        </p>
        <Form :label-width="80">
            <Row>
                <Col span="6">
                <FormItem label="仓库">
                    <Select v-model="query.storeCode"
                            clearable
                    >
                        <Option v-for="item in storeDict"
                                :value="item.serialNo"
                                :label="item.name"
                                :key="item.id"
                        >
                            {{item.name}}
                        </Option>
                    </Select>
                </FormItem>
                </Col>
                <Col span="6">
                <FormItem label="接收方">
                    <Input v-model="query.receiver" ></Input>
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
                :data="exportList"
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
        name: 'export-list',
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
                        title: '出库单号',
                        key: 'documentNo'
                    },
                    {
                        title: '出库日期',
                        key: 'exportDateTime',
                        render: (h, params) => {
                            return h("div", dateFilter(params.row.exportDateTime));
                        }
                    },
                    {
                        title: '出库仓库',
                        key: 'storeCode',
                        render: (h, params) => {
                            return h("div", storeFilter(params.row.storeCode, this.storeDict));
                        }
                    },
                    {
                        title: '接收方',
                        key: 'receiver',
                    },
                ],
                exportList: [],
                storeDict: {},
                query: {}
            }
        },
        methods: {
            view(document) {
                this.$router.push({name: 'export', params: {documentNo: document.documentNo}});
            },
            getExportList() {
                let vm = this;
                invokeSrv.getFnData(storeSrv.getExportMasterList, vm.query, function (data) {
                    vm.exportList = data;
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