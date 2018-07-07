<template>
    <Card>
        <p slot="title">
            <Icon type="filing"></Icon>
            <span>房源名录</span>
        </p>
        <p slot="extra">
            <Button type="primary" @click="getHouseResource">查询</Button>
        </p>
        <div>
            <Button type="primary" size="small" @click="add">添加</Button>
        </div>
        <Table
                :columns="columns"
                :data="houseResourceList"
        >
        </Table>
        <Modal
                v-model="isShow"
                title="添加/编辑房源名录"
                @on-ok="ok"
                @on-cancel="cancel"
        >
            <Form ref="formMaster" :model="query" :rules="rules" :label-width="80">
                <Row>
                    <Col span="8">
                    <FormItem label="区域名称" prop="areaCode">
                        <Select v-model="query.areaCode">
                            <Option v-for="item in areaDict"
                            :label="item.areaName"
                            :value="item.serialNo"
                            :key="item.serialNo"></Option>
                        </Select>
                    </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="16">
                    <FormItem label="小区名称" prop="communityName">
                        <Input v-model="query.communityName"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="后缀名">
                        <Input v-model="query.suffix"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                    <FormItem label="栋号" prop="building">
                        <Input v-model="query.building"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="单元" prop="unit">
                        <Input v-model="query.unit"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="房号" prop="roomNo">
                        <Input v-model="query.roomNo"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <FormItem label="房源编号" prop="houseResourceNo">
                        <Input v-model="query.houseResourceNo"></Input>
                    </FormItem>
                    </Col>
                    <Col span="12">
                    <FormItem label="收房合同号" prop="houseContractNo">
                        <Input v-model="query.houseContractNo"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                    <FormItem label="专员姓名" prop="specialistName">
                        <Input v-model="query.specialistName"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="联系方式" prop="specialistPhone">
                        <Input v-model="query.specialistPhone"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="联系方式1">
                        <Input v-model="query.specialistPhone1"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="8">
                    <FormItem label="管家姓名" prop="managerName">
                        <Input v-model="query.managerName"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="联系方式" prop="managerPhone">
                        <Input v-model="query.managerPhone"></Input>
                    </FormItem>
                    </Col>
                    <Col span="8">
                    <FormItem label="联系方式1">
                        <Input v-model="query.managerPhone1"></Input>
                    </FormItem>
                    </Col>
                </Row>
            </Form>
            <div slot="footer">
                <Button type="primary" @click="ok">确定</Button>
                <Button type="primary" @click="cancel">取消</Button>
            </div>
        </Modal>
    </Card>
</template>

<script>
    import invokeSrv from "../../../libs/invoke.service";
    import sysCfgSrv from "../../../libs/systemConfig.service";

    let areaDict = [];

    let dictFilter = window.Vue.filter("dict");

    export default {
        data() {
            return {
                isShow: false,
                isAdd: false,
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
                        key: 'areaCode',
                        title: '区域名称',
                        render:(h,params)=> {
                            return h("div", dictFilter(params.row.areaCode, areaDict));
                        }
                    },
                    {
                        key: 'communityName',
                        title: '小区名称'
                    },
                    {
                        key: 'suffix',
                        title: '后缀名'
                    },
                    {
                        key: 'building',
                        title: '栋号'
                    },
                    {
                        key: 'unit',
                        title: '单元'
                    },
                    {
                        key: 'roomNo',
                        title: '房号'
                    },
                    {
                        key: 'houseResourceNo',
                        title: '房源编号'
                    },
                    {
                        key: 'houseContractNo',
                        title: '收房合同号'
                    },
                    {
                        key: 'specialistName',
                        title: '专员姓名'
                    },
                    {
                        key: 'specialistPhone',
                        title: '联系方式'
                    },
                    {
                        key: 'specialistPhone1',
                        title: '联系方式1'
                    },
                    {
                        key: 'managerName',
                        title: '管家姓名'
                    },
                    {
                        key: 'managerPhone',
                        title: '联系方式'
                    },
                    {
                        key: 'managerPhone1',
                        title: '联系方式1'
                    }
                ],
                houseResourceList: [],
                areaDict:[],
                rules:{
                    areaCode:[{required:true,message:'区域代码不能为空！',trigger:'change'}],
                    communityName:[{required:true,message:'小区名称不能为空！',trigger:'blur'}],
                    building:[{required:true,message:'栋号不能为空！',trigger:'blur'}],
                    unit:[{required:true,message:'单元不能为空！',trigger:'blur'}],
                    roomNo:[{required:true,message:'房号不能为空！',trigger:'blur'}],
                    houseResourceNo:[{required:true,message:'房源编号不能为空！',trigger:'blur'}],
                    houseContractNo:[{required:true,message:'收房合同号不能为空！',trigger:'blur'}],
                    specialistName:[{required:true,message:'专员姓名不能为空！',trigger:'blur'}],
                    specialistPhone:[{required:true,message:'专员号码不能为空！',trigger:'blur'}],
                    managerName:[{required:true,message:'主管姓名不能为空！',trigger:'blur'}],
                    managerPhone:[{required:true,message:'主管号码不能为空！',trigger:'blur'}],
                }
            };
        },
        methods: {
            getHouseResource() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getHouseResource, null, function (data) {
                    vm.houseResourceList = data;
                })
            },
            getAreaDict() {
                let vm = this;
                invokeSrv.getFnData(sysCfgSrv.getAreaDict,null,function(data) {
                    vm.areaDict = data;
                    areaDict = vm.areaDict.map((item) => {
                        return {code: item.serialNo, name: item.areaName};
                    });
                })
            },
            init() {
                let vm = this;
                vm.getAreaDict();
            },
            add() {
                this.query = {};
                this.isShow = true;
                this.isAdd = true;
            },
            edit(dict) {
                this.query = dict;
                this.isAdd = false;
                this.isShow = true;
            },
            delete(dict) {
                let vm = this;
                console.log(dict);
                sysCfgSrv.saveHouseResource(dict, "delete").then(function (result) {
                    if (result && result.data && result.data.status == '0') {
                        vm.houseResourceList.splice(dict._index, 1);
                        vm.$Message.success("删除成功!");
                    } else {
                        vm.$Message.error("删除失败!");
                    }
                }).catch(function (result) {
                    vm.$Message.error("删除异常!" + result);
                })
            },
            ok() {
                let vm = this;
                vm.$refs.formMaster.validate((valid)=> {
                    if(valid) {
                        sysCfgSrv.saveHouseResource(vm.query).then(function (result) {
                            if (result && result.data && result.data.status == '0') {
                                vm.query = Object.assign({}, vm.query, result.data.data);
                                if (vm.isAdd) {
                                    vm.houseResourceList.push(vm.query);
                                } else {
                                    vm.houseResourceList.splice(vm.query._index, 1, vm.query);
                                }
                                vm.isShow = false;
                                vm.$Message.success("保存成功!");
                            } else {
                                vm.$Message.error("保存失败!" + result.data.msg);
                            }
                        }).catch(function (result) {
                            vm.$Message.error("保存失败!" + result.data.msg);
                        })
                    }
                })

            },
            cancel() {
                this.isShow = false;
            }
        },
        mounted: function () {
            this.init();
        },
    }
</script>
