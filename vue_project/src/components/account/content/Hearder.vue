<template>
<div>
    <div class="hearder_left">
      <div class="hearder_left_i">
       <i class="el-icon-mobile-phone"></i>
       </div>
        <p>非网关设备数<span>{{data.devNum_notGateWay}}</span>台</p>
        <p>网关设备数<span>{{data.devNum_gateWay}}</span>台</p>
    </div>
    <div class="hearder_center">
      <div class="hearder_center_i">
      <i class="iconfont icon-qunzu"></i>
      </div>
        <p>用户数<span>{{data.userNum}}</span>人</p>
        <p>活动告警数<span>{{data.activeAlarmNum}}</span>个</p>
    </div>
    <div class="hearder_right" v-if="!isAdmin">
      <div class="hearder_right_i">
      <i class="iconfont icon-qunzu"></i>
      </div>
        <p>用户数<span>18</span>人</p>
        <p>在线用户数<span>1</span>人</p>
    </div>
    <div id="myChart" :style="{width:'1000px',height:'500px',top:'50px'}"></div>
</div>
</template>
<script>
import moment from "moment";
// 引入基本模板
let echarts = require("echarts/lib/echarts");
// 引入柱状图组件
require("echarts/lib/chart/bar");
require("echarts/lib/chart/line");
// 引入提示框和title组件
require("echarts/lib/component/tooltip");
require("echarts/lib/component/title");
//引入图例
require("echarts/lib/component/legend");

export default {
   name:'Hearder',
  data() {
    return {
       seriesData:[],
       isAdmin:'',
        access_token:'',
        data:{
          activeAlarmNum:'',//活动告警数
          devNum_gateWay:'',//网关设备数
          devNum_notGateWay:'',//非网关设备数
          userNum:'',//用户统计数
        },
        startDate:'',
        endDate:'',
        dataName:'',
        xAxisData:''
    };
  },
  mounted() {
    this.drawLine();
  },
  methods: {
     //图表生成
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById("myChart"));
      const _this = this;
      // 绘制图表
      myChart.setOption({
        //标题设置
        title: { text: _this.dataName },
        //大小设置
        legend:{
          orient:'horizontal',//图例方向(纵向)
          data:[{name:_this.dataName}],
        },
        //提示框设置
        tooltip: {
          //显示垂直辅助线
          trigger:'axis',
        },
        //x轴数据设置
        xAxis: {
          type:'category',
          data: _this.xAxisData,
          //是否显示垂直网格
          splitLine:{
            show:true
          },
          //x轴字体设置
          axisLabel:{
	    		    	interval:0,
	    			    rotate:25,//倾斜度 -90 至 90 默认为0
	    			    margin:2,
	    			    textStyle:{
                  fontWeight:"bolder",
                  fontSize:'10',
	    			        color:"#000000"
	    			    }
	    			},
        },
        //y轴数据设置
        yAxis: {
          type:'value',
           data: ['0', '20', '40','60', '80', '100']
        },
        //展示数据设置
        series: [
          {
            name: _this.dataName, 
            type: "line",
            symbolSize:8,
            //修改线条颜色
            itemStyle:{
              normal:{
                lineStyle:{
                  color:'#ff0000'
                }
              }
            },  
            //定义的数据
            data: _this.seriesData,
            //线性圆滑
            smooth:true,
          },
        ]
      });
  },
  //统计数据
    homeAdmin(){
      let page = {
            Authorization: this.access_token,
        }
        this.$store
        .dispatch("homeAdmin", page)
        .then(res => {
          if (res) {
            // console.log(res)
            if(res.code==0){this.data = res.data;}else{
              this.data = res
            }
          }else{
            this.$message({
            type: "warning",
            message: "系统错误"
          });
          }
        })
        .catch(e => {
          this.$message({
            type: "warning",
            message: "系统错误"
          });
        });
    },
    //图标统计数据
    telemetryAttributesStatisByDay(){
      let page = {
            Authorization: this.access_token,
            startTime:this.startDate,
            endTime:this.endDate,
        }
         this.$store
        .dispatch("telemetryAttributesStatisByDay", page)
        .then(res => {
          if (res.code == 0) {
            //console.log(res.data.legends[0])
            this.dataName = res.data.legends[0];
            this.seriesData = res.data.datas[0];
            this.xAxisData = res.data.xaxis;
            this.drawLine();
          }else{
            this.$message({
            type: "warning",
            message: "系统错误"
          });
          }
        })
        .catch(e => {
          // this.$message({
          //   type: "warning",
          //   message: "系统错误12"
          // });
        });
    }
  },
  created () {
    this.access_token = localStorage.getItem("access_token");
    this.endDate = moment(Date.now()).format("YYYY-MM-DD HH:mm:ss");
    this.startDate = moment(Date.now())
      .subtract(7, "days")
      .format("YYYY-MM-DD HH:mm:ss");
    let usname = localStorage.getItem('uname')
    //判断是否是管理员
    if(usname=='admin'){
        this.isAdmin = true
      }else{
        this.isAdmin = false
      }
      //  this.homeAdmin();
      //  this.telemetryAttributesStatisByDay();
    }
    
};
</script>
<style scoped lang='less'>
@import './css/Hearder.css';
</style>
