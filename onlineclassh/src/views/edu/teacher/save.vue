<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number
          v-model="teacher.sort"
          controls-position="right"
          min="0"
        />
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career" />
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea" />
      </el-form-item>
      <!-- 讲师头像：TODO -->
      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate()"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from "@/api/edu/teacher";
export default {
  data() {
    return {
      teacher: {
        name: "",
        sort: 0,
        level: 1,
        career: "",
        intro: "",
        avatar: "",
      },
      //保存按钮禁用
      saveBtnDisabled: false,
    };
  },

  created() {
    //区分添加和修改
    //     if (this.$route.params && this.$route.params.id) {
    // const id = this.$route.params.id
    // this.fetchDataById(id)
    //  }
      this.init()
 
    //如果有id值就修改

    //如果没有id值就增加
  },
  watch: {
    $route(to, from) {
      console.log("watch $route");
      this.init()
    },
  },

  methods: {
      init(){
             if (this.$route.params.id != null) {
      const id = this.$route.params.id;
      console.log(id);
      this.getTeacherInfo(id);
    } else {
      //清空表单
      this.teacher = {};
    }
      },

    getTeacherInfo(id) {
      teacherApi.getTeacherInfo(id).then((response) => {
        this.teacher = response.data.teacher;
      });
    },

    saveOrUpdate() {
      console.log("方法运行");
      //判断是修改还是添加
      console.log(this.teacher.id);

      // this.saveTeacher()
      if (!this.teacher.id) {
        this.saveTeacher();
      } else {
        console.log("进入修改方法");
        this.updateTeacher();
      }
      //修改

      //添加
    },

    //添加导师方法
    saveTeacher() {
      teacherApi.addTeacherId(this.teacher).then((response) => {
        //添加成功
        //查看成功数据
        console.log(response);
        //提示信息
        this.$message({
          type: "success",
          message: "添加成功!",
        });
        //返回列表，路由跳转
        this.$router.push({ path: "/teacher/table" });
      });
    },

    updateTeacher() {
      console.log("进入修改方法1");
      teacherApi.updateTeacherInfo(this.teacher).then((response) => {
        console.log("进入修改方法2");
        //提示信息
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        console.log(response);
        //回到页面，路由跳转
        this.$router.push({ path: "/teacher/table" });
      });
    },
  },
};
</script>