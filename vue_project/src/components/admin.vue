<template>
    <el-container style="height:100%">
      <!-- 公共左侧 -->
      <el-header style="width:100%;padding:0px;height:60px">
           <app-header style="width:100%"></app-header>
      </el-header>
      <!-- 公共头部 -->
      <el-container style="height:100%">
          <el-aside style="height:100%;width:200px" v-if="isShow">
            <app-left></app-left>
          </el-aside>
         <!-- <two-menu v-if="isShow" style="margin-left:8px;"></two-menu> -->
         <!-- 变化后的子页面 -->
          <el-main style="padding:10px;">
            <!-- <app-swiper :listImg="listImg"></app-swiper> -->
            <router-view></router-view>
         </el-main>
          </el-container>
    </el-container>
</template>
<script>
import appLeft from "./account/admin/AppLeft";
import appHeader from "./account/admin/AppHeader";

export default {
  data() {
    return {
      
    };
  },
  //注册子组件
  components: {
    appLeft,
    appHeader
  },
  methods: {
    
  },
  
  computed:{
    isShow(){
      return this.$store.state.user.isShow
    }
  }
};
</script>
<style scoped lang='less'>
</style>