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
      <el-step title="最终发布" />
    </el-steps>
    <!-- 添加章节 -->
    <el-button type="text" @click="openDialogChapter()">添加章节</el-button>

    <!-- 修改章节按钮 -->

    <ul class="chanpterList">
      <li v-for="chapter in chapterVideoList">
        <p>
          {{ chapter.title }}

          <span class="acts">
            <el-button style="" type="text" @click="getChapter(chapter.id)"
              >编辑</el-button
            >
            <el-button type="text" @click="removeChapter(chapter.id)"
              >删除</el-button
            >
          </span>
        </p>
        <ul class="chanpterList videoList">
          <li v-for="video in chapter.videoList">
            {{ video.title }}
          </li>
        </ul>
      </li>
    </ul>

    <el-button @click="previous">上一步</el-button>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
      >下一步</el-button
    >

    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title" />
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number
            v-model="chapter.sort"
            :min="0"
            controls-position="right"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate()">确 定</el-button>
      </div>
    </el-dialog>
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
      chapter: {
 
      },
      dialogChapterFormVisible: false,
    };
  },
  created() {
    this.courseId = this.$route.params.id;
    this.getAllChapter();
  },
  methods: {
    getChapter(chapterId){
      this.dialogChapterFormVisible = true
        chapterApi.getChapterById(chapterId)
        .then(response =>{
          this.chapter = response.data.chapter
        })
    },
    removeChapter(chpaterId) {
      console.log("进入方法");
      this.$confirm("此操作将永久删除记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        //删除方法
        console.log("进入方法1");
        chapterApi.delChapterById(chpaterId).then((response) => {
          //提示信息
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          //回到页面
          this.getAllChapter();
        });
      });
    },

    openDialogChapter() {
      this.dialogChapterFormVisible = true;
      //表单的数据清空
      (this.chapter.title = ""), (this.chapter.sort = 0);
    },


    saveChapter(){
      console.log(this.chapter)
        chapterApi.updateChapter(this.chapter)

        .then(response=>{
          //1关闭弹框
        this.dialogChapterFormVisible = false;
        //2弹框
        this.$message({
          type: "success",
          message: "修改成功",
        });
        //3添加信息
        this.getAllChapter();

        })
    },
    addChapter() {
      //配置他的课程id
      this.chapter.courseId = this.courseId;
      chapterApi.addChapter(this.chapter).then((response) => {
        //1关闭弹框
        this.dialogChapterFormVisible = false;
        //2弹框
        this.$message({
          type: "success",
          message: "添加成功",
        });
        //3添加信息
        this.getAllChapter();
      });
    },
    saveOrUpdate() {
      if(this.chapter.id){
          this.saveChapter()
      }else{
          this.addChapter()
      }
    },

    getAllChapter() {
      chapterApi.getAllChapter(this.courseId).then((response) => {
        this.chapterVideoList = response.data.AllChapter;
      });
    },
    next() {
      this.$router.push({ path: "/course/publish/" + this.courseId });
    },
    previous() {
      this.$router.push({ path: "/course/info/" + this.courseId });
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
