<template>
  <div>
    <div v-if="isPost">
      <div class="wrapC">
        <v-card class="mx-auto">
          <div>
            <div class="thumbnail">
              <div class="centered">
                <v-img :src="list.postPhoto" class="min_size"></v-img>
              </div>
            </div>
          </div>
          <v-card-text @click="goPostDetail(list.postNo)">
            <span class="jua">{{list.postContent}}</span>
            <br />
            <span v-for="h in hashtagList" :key="h.hashtagNo">{{h.hashtagContent}}</span>
            <!--해시태그-->
          </v-card-text>
        </v-card>
      </div>
    </div>

    <div v-else-if="isHashtag">
      <div class="wrapC">
        <v-card class="mx-auto">
          <div>
            <div class="thumbnail">
              <div class="centered">
                <v-img :src="postList.postPhoto" class="min_size"></v-img>
              </div>
            </div>
          </div>

          <v-card-text @click="goPostDetail(postList.postNo)">
            <span class="jua">{{postList.postContent}}</span>
            <br />
            <span>{{list.hashtagContent}}</span>
          </v-card-text>
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
import PostApi from "../../api/PostApi";
import HashtagApi from "../../api/HashtagApi";

export default {
  name: 'SearchPost',
  props:{
    list:Object
  },
  data (){
    return {
      isPost: false,
      isHashtag: false,
      postList: [],
      hashtagList: [],
      index: 0,
      imagePath: "http://i3d201.p.ssafy.io:8080/",
      imageSplit: [],
    }
  },
  created(){
    //해시태그 기준 
    if (this.list.memberNo == null){
      this.isHashtag = true;
      this.isPost = false;
      PostApi.requestSelectPostByNo(
        this.list.postNo,
        (res) => {
          this.postList = res.data

          this.imageSplit = this.postList.postPhoto.split("/");
          this.index = this.imageSplit.length - 1;
          this.postList.postPhoto =
            this.imagePath + this.imageSplit[this.index];
        },
        (error) => {
          console.log("error")
        }
      )
    }
    // 포스트콘텐트 기준
    if (this.list.hashtagNo == null){
      this.isHashtag = false;
      this.isPost = true;
      HashtagApi.requestFindAllHastags(
        this.list.postNo,
        (res) => {
          this.hashtagList = res.data;

        },
        (error) => {
          console.log("error")
        }
      )
      this.imageSplit = this.list.postPhoto.split("/");
        this.index = this.imageSplit.length - 1;
        this.list.postPhoto =
          this.imagePath + this.imageSplit[this.index];
    }
  },
  methods: {
    goPostDetail(num){
      this.$router.push({
        name: "PostDetail",
        params: {
          num: num,
        },
      });
    },
  },
}
</script>

<style>
.theme--dark.v-app-bar.v-toolbar.v-sheet {
  background: linear-gradient(to left, #f48fb1, #3949ab);
}
.v-bottom-navigation {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 3rem;
}
.v-application--wrap {
  height: auto;
}
.jua {
  font-family: "Jua", sans-serif;
}
.v-icon.notranslate.mdi.mdi-menu.theme--dark {
  color: white;
}
.min_size {
  min-width: 328px;
}
</style>