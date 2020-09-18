<template>
  <div class="wrapC p-0">
    <v-app>
      <v-card class="mx-auto">
        <v-list-item>
          <v-list-item-avatar>
            <v-img :src="profileImagePath"></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title class="headline">by {{nickname}}</v-list-item-title>
            <!--v-list-item-subtitle>by {{user.nickname}}</v-list-item-subtitle-->
          </v-list-item-content>
        </v-list-item>

        <v-img :src="imagePath" height="auto"></v-img>

        <div class="input-with-label jua">
          <textarea
            v-model="post.postContent"
            id="postContent"
            placeholder="내용을 입력하세요"
            type="text" 
            cols="35%"
            rows="7"
          />
        </div>
        <div class="jua" style="text-align:center;">
          <button text style="font-size:medium; color:navy;" @click="gotomodify(post.postNo)">저장하기</button>
        </div>
      </v-card>

      
    </v-app>
  </div>
</template>

<script>
import PostApi from "../../api/PostApi";
export default {
  created() {
    this.user = this.$session.get("user");

    this.profileImageSplit = this.user.memberPhoto.split("/");
    this.profileIndex = this.profileImageSplit.length - 1;
    this.profileImagePath += this.profileImageSplit[this.profileIndex];

    this.num = this.$route.params.num;
    this.nickname = this.$route.params.nickname;
    PostApi.requestSelectPostByNo(
      this.num,
      (res) => {
        this.post = res.data;
        
        this.imageSplit = this.post.postPhoto.split("/");
        this.index = this.imageSplit.length - 1;
        this.imagePath += this.imageSplit[this.index]; // 포스트 이미지

      },
      (error) => {}
    );
  },
  data() {
    return {
      post: {
        postNo: 0,
        memberNo: 0,
        postPhoto: "",
        postContent: "",
        missionNo: 0,
        postImg: "",
      },
      num: 0,
      postImg: "",
      nickname: "",
    profileImagePath: "http://i3d201.p.ssafy.io:8080/profile/",
    
    imagePath: "http://i3d201.p.ssafy.io:8080/",
    index: 0,
    imageSplit: [],
    };
  },
  methods: {
    gotomodify(postNo) {
      PostApi.requestPostUpdate(
        this.post,
        (res) => {
          this.$router.push({
            name: "PostDetail",
            params: {
              num: this.post.postNo,
            },
          });
        },
        (error) => {}
      );
    },
    posts() {
      this.$router.push("/posts");
    },
    mission() {
      this.$router.push("/missionmain");
    },
    writing() {
      this.$router.push("/add");
    },
    search() {
      this.$router.push("/search");
    },
    profile() {
      this.$router.push("/profile");
    },
  },
};
</script>

<style>
.submit_button {
  margin-bottom: 3rem;
}
.post-adds {
  margin-bottom: 7.5rem;
}
.jua {
  font-family: "Jua", sans-serif;
}
.jua_small {
  font-size: medium;
  font-family: "Jua", sans-serif;
}
#postContent {
  text-align: center;
  float: left;
  margin: 0 15%;
}
</style>
