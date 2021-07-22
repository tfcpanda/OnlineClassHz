<template>
  <div class="app-container">
    课程列表
    <!--  条件查询  -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="courseQuery.level" clearable placeholder="课程状态">
          <el-option :value="Normal" label="已发布"/>
          <el-option :value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>


      <el-button type="primary" icon="el-icon-search" @click="getList()">查 询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row>
      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="名称" width="80"/>
      <el-table-column label="发布状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 'Normal' ? '已发布' : '未发布' }}
        </template>
      </el-table-column>

      <el-table-column prop="lessonNum" label="课时数"/>

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="viewCount" label="浏览数量" width="60"/>

      <el-table-column label="操作" width="200" align="center">

        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程基本信息</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     @click="updateById()">修改
          </el-button>
          <el-button type="danger" size="mini" icon="el-icon-delete"
                     @click="removeDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页  -->
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />


  </div>
</template>


<script>
//引入调用文件
import courseAPI from '@/api/edu/course'

export default {
  //定义数据
  data() {
    return {
      list: null,
      page: 1,
      limit: 5,
      total: 0,
      courseQuery: {}
    }
  },

  created() {
    this.getList()
  },

  methods: {
    getList() {
    
      courseAPI.getCourse()
        .then(response => {
          //接口返回的数据
          this.list = response.data.list
        
        })
        .catch(error => {
          console.log(error)
        })
    },
    resetData() {
      //清空表单
      this.courseQuery = {}

      //查询数据
      this.getList()


    },
    removeDataById(id){
      courseAPI.delCourse(id)
      .then(request=>{
        console.log("删除成功")
        //重新加载数据
        this.getList();
      })
    }
 

  },


}
</script>
