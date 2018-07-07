<template>
    <Card>
        <p slot="title">入库单列表</p>
        <p slot="extra">
            <Button type="primary" @click="getImportList">查询</Button>
        </p>
        <Form :label-width="80">
            <Row>
                <Col span="6">
                <FormItem label="仓库">
                    <Select
                            clearable
                            v-model="query.storeCode">
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
                :data="importList"
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
        name: 'import-list',
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
                        title: '入库单号',
                        key: 'documentNo'
                    },
                    {
                        title: '入库日期',
                        key: 'importDateTime',
                        render: (h, params) => {
                            return h("div", dateFilter(params.row.importDateTime));
                        }
                    },
                    {
                        title: '仓库',
                        key: 'storeCode',
                        render: (h, params) => {
                            return h("div", storeFilter(params.row.storeCode, this.storeDict));
                        }
                    }
                ],
                importList: [],
                storeDict: {},
                query: {}
            }
        },
        methods: {
            view(document) {
                this.$router.push({name: 'import', params: {documentNo: document.documentNo}});
            },
            getImportList() {
                let vm = this;
                invokeSrv.getFnData(storeSrv.getItemImportMasterList, vm.query, function (data) {
                    vm.importList = data;
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