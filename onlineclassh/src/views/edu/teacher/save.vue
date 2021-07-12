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
          @click="saveTeacher()"
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
    if (this.$router.params && this.$router.params.id) {
      const id = this.$router.params.id;
      this.getTeacherInfo(id);
    } else{
      this.getTeacherInfo(1);
    }
    //如果有id值就修改

    //如果没有id值就增加
  },

  methods: {
    getTeacherInfo(id) {
      teacherApi.getTeacherInfo(id).then((response) => {
        this.teacher = response.data.teacher;
      });
    },

    saveOrUpdate() {
      this.saveTeacher;
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

    updateTeacher(teacher) {
      teacherApi.updateTeacherInfo(this.teacher).then((response) => {
        //提示信息
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        //回到页面，路由跳转
        this.$router.push({ path: "/teacher/table" });
      });
    },
  },
};
</script>