<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="2"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <ul>
      <li v-for="chapter in chapterVideoList" key="chapter.id">
        {{ chapter.title }}

        <ul>
          <li v-for="video in chapter.videoList" key="video.id">
            {{ video.title }}
          </li>
        </ul>
      </li>
    </ul>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">上一步</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
          >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template> 
<script>
import chapterApi from "@/api/edu/chapter";
export default {
  data() {
    return {
      saveBtnDisabled: false,
      chapterVideoList: [],
      courseId: "",
    };
  },
  created() {
    this.courseId = this.$route.params.id;
    this.getAllChapter();
  },
  methods: {
    getAllChapter() {
      chapterApi.getAllChapter(this.courseId).then((response) => {
        this.chapterChapterVideo = response.data.AllChapter;
      });
    },
    next() {
      console.log("转发后" + this.courseId);
      this.$router.push({ path: "/course/publish/" + this.courseId });
      console.log("转发后" + this.courseId);
    },
    previous() {
      console.log("转发前" + this.courseId);
  
      this.$router.push({ path: '/course/info/' + this.courseId});
      console.log("转发后" + this.courseId);
    },
  },
};
</script>

<style scoped>
.chanpterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li {
  position: relative;
}
.chanpterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}
.videoList {
  padding-left: 50px;
}
.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #ddd;
}
</style>
