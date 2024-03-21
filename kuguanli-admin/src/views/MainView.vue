<template>
    <div class="container">
        <el-container>
            <el-aside width="180px" v-if="!isCollapse">
                <div class="border">
                    <el-menu :router="true" :default-active="$route.path">
                        <el-menu-item index="">首页</el-menu-item>
                        <el-sub-menu index="1">
                            <template #title>
                                <span>基础设置</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/init/product">新增产品</el-menu-item>
                                <el-menu-item index="/init/supplier">新增供应商</el-menu-item>
                                <el-menu-item index="/init/warehouse">新增仓库</el-menu-item>
                                <el-menu-item index="/init/catalog">新增产品类别</el-menu-item>
                                <el-menu-item index="/init/customer">新增客户</el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="2">
                            <template #title>
                                <span>查询管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/inventory">查看库存量</el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="3">
                            <template #title>
                                <span>进货管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="/rms/purchase-order">录入采购订单</el-menu-item>
                                <el-menu-item index="/rms/inspection">验货管理</el-menu-item>
                                <el-menu-item index="/rms/receiving">入货管理</el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>
                        <el-sub-menu index="4">
                            <template #title>
                                <span>提货管理</span>
                            </template>
                            <el-menu-item-group>
                                <el-menu-item index="">录入销售订单</el-menu-item>
                                <el-menu-item index="">验货管理</el-menu-item>
                                <el-menu-item index="">出货管理</el-menu-item>
                            </el-menu-item-group>
                        </el-sub-menu>
                        <el-menu-item index="">盘存管理</el-menu-item>

                        <el-menu-item index="">统计分析</el-menu-item>
                        <el-menu-item index="">个人安全设置</el-menu-item>

                    </el-menu>
                </div>
            </el-aside>
            <el-container>
                <el-header height="50px" class="el-header">
                    <el-row type="flex" justify>
                        <el-col class="flex-container flex-center">
                            <div>
                                <div class="float-left">
                                    <el-button class="collape-btn" ref="btn" @click="handleCollapse"
                                        :icon="isCollapse ? 'ArrowRight' : 'ArrowLeft'" text>

                                    </el-button>
                                </div>
                            </div>
                            <el-breadcrumb separator="/">
                                <el-breadcrumb-item>首页</el-breadcrumb-item>
                            </el-breadcrumb>
                        </el-col>

                    </el-row>
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
export default {

    data() {
        return {
            isCollapse: false
        };
    },
    methods: {
        handleCollapse(event) {
            event.target.blur()
            this.isCollapse = !this.isCollapse;
        }
    },
    mounted() {

    }
}

</script>
<style scoped>
.el-main {
    background-color: #f5f6f8;
}

.collape-btn:active {
    box-shadow: none;
}

.border {
    height: 100%;
    border-right: solid 1px #e6e6e6;
}

.container {
    height: 100vh;
}

.el-menu {
    border-right: none;
}

.el-header {
    text-align: center;
    border-bottom: 1px solid #d8d8d8;
    line-height: 50px;
    height: 10%;
}

.el-aside {
    height: 100vh;
}

.flex-container {
    display: flex;
}

.flex-container.flex-center {
    align-items: center;
}

.el-container {
    height: 100%;
}

.float-right {
    float: right;
}
</style>
