<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视"
        />
      </el-form-item>
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>
      <!-- 所属分类 TODO -->
      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="课程类别">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="一级分类"
          @change="subjectLevelOneChanged"
        >
          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>

        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option
            v-for="subject in SubjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>
      <!-- 课程讲师 TODO -->
      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
        "
      </el-form-item>
      <!-- 课程简介 TODO -->
      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>

      <!-- 课程封面 TODO -->
      <!-- 课程封面-->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/eduoss/fileoss'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover" />
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate"
          >保存并下一步
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import courseApi from "@/api/edu/course";
import Tinymce from "@/components/Tinymce";
import subjectApi from "@/api/edu/subject";
export default {
  components: { Tinymce },
  data() {
    return {
      saveBtnDisabled: false,
      courseInfo: {
        title: "",
        subjectId: "",
        teacherId: "",
        lessonNum: 0,
        description: "",
        cover: "/static/weini.jpg",
        price: 0,
      },
      courseId: "",
      teacherList: [],
      subjectOneList: [],
      SubjectTwoList: [],
      BASE_API: process.env.BASE_API,
    };
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      console.log("传回id值，修改方法" + this.$route.params.id);
      this.courseId = this.$route.params.id;
           this.findAllTeacher();
      this.getCourseInfoById();
    } else {
      console.log("没有id值，增加方法");
      this.findAllTeacher();
      this.findSubjectOne();
    }

    this.subjectLevelOneChanged();
  },
  methods: {
    getCourseInfoById() {
      console.log("方法运行中" + this.courseId);
      courseApi.getCourseInfoById(this.courseId).then((response) => {
        console.log("方法运行后" + this.courseId);
        //一级分类中有值，二级分类中没有值

        //查询所有分类，包括一级和二级
        subjectApi.getAllSubject().then((response) => {
          //获取所有一级分类
          this.subjectOneList = response.data.list;
          //把所有一级分类数组进行便利
          for (var i = 0; i < this.subjectOneList.length; i++) {
            //获取每一个分类
            var oneSubject = this.subjectOneList[i];
            //比较每CourseId一级分类和所有一级分类的Id
            if (this.courseInfo.subjectParentId == oneSubject.id) {
              //获取一级分类和所有的二级分类
              this.SubjectTwoList = oneSubject.children;
            }
          }
        });
        this.findSubjectOne();
        //初始化所有讲师
        this.courseInfo = response.data.courseInfoVo;
          this.findSubjectOne();
      });
    },

    updateCourseinfo() {
      courseApi.updateCourseInfo(this.courseInfo).then((response) => {
        //修改课程信息成功
        this.$message({
          type: "success",
          message: "修改成功",
        });
        this.$router.push({
          path: "/course/chapter/" + this.courseId,
        });
      });
    },

    addCourseInfo() {
      courseApi.addCourseInfo(this.courseInfo).then((response) => {
        //添加课程信息成功
        this.$message({
          type: "success",
          message: "添加成功",
        });
        this.$router.push({
          path: "/course/chapter/" + response.data.courseId,
        });
      });
    },
    saveOrUpdate() {
      if (!this.courseInfo.id) {
        this.addCourseInfo();
      } else {
        this.updateCourseinfo();
      }
    },

    findAllTeacher() {
      courseApi.findAllTeacher().then((response) => {
        this.teacherList = response.data.items;
      });
    },

    findSubjectOne() {
      courseApi.getAllSubject().then((response) => {
        this.subjectOneList = response.data.list;
      });
    },

    subjectLevelOneChanged(value) {
      for (let i = 0; i < this.subjectOneList.length; i++) {
        var oneSubject = this.subjectOneList[i];

        if (oneSubject.id === value) {
          this.SubjectTwoList = oneSubject.children;
          this.courseInfo.subjectId = "";
        }
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    handleAvatarSuccess(res, file) {
      console.log(res); // 上传响应
      console.log(URL.createObjectURL(file.raw)); // base64编码
      this.courseInfo.cover = res.data.url;
    },
  },
};
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
