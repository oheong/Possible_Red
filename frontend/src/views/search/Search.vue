<template>
  <div class="wrapC p-0 search-pg">
    <v-app>
      <SearchBar class="search-bar" @search-items="searching" :isSearching="isSearching" />
      <div style="margin-bottom: 3rem;">
        <SearchPost v-show="isPost" v-for="list in postList" :key="list" :list="list" />

        <SearchPost v-show="isHashtag" v-for="list in hashtagList" :key="list" :list="list" />

        <SearchUser
          v-show="isUser"
          v-for="memberList in memberList"
          :key="memberList.memberNo"
          :value="memberList.memberNo"
          :memberList="memberList"
          @gotoOtherProfile="gotoOtherProfile"
        />
      </div>

      <v-bottom-navigation black shift>
        <v-btn @click="post">
          <span>POST</span>
          <v-icon>mdi-text</v-icon>
        </v-btn>

        <v-btn @click="mission">
          <span>Mission</span>
          <v-icon>mdi-clipboard</v-icon>
        </v-btn>

        <v-btn @click="writing">
          <span>Writing</span>
          <v-icon>mdi-pencil</v-icon>
        </v-btn>

        <v-btn @click="search">
          <span>Search</span>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn @click="profile">
          <span>Profile</span>
          <v-icon>mdi-account</v-icon>
        </v-btn>
      </v-bottom-navigation>
    </v-app>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import "../../components/css/style.css";
import SearchBar from "../../components/search/Searchbar.vue";
import SearchPost from "../../components/search/SearchPost.vue";
import SearchUser from "../../components/search/SearchUser.vue";
import SearchApi from "../../api/SearchApi";
import UserApi from "../../api/UserApi";

export default {
  name: "Search",
  data() {
    return {
      isPost: false,
      isUser: false,
      isHashtag: false,
      isSearching: false,
      searchItem: {
        type: null,
        keyword: null,
      },
      postList: [],
      memberList: [],
      hashtagList: [],
      user: {
        email: "",
        memberNo: 0,
        memberPhoto: "",
        nickname: "",
        point: 0,
        pwd: "",
        stateMent: "",
      },
    };
  },
  components: {
    SearchBar,
    SearchPost,
    SearchUser,
  },
  methods: {
    gotoOtherProfile(otherMemberNo) {
      UserApi.requestMemberByNo(
        otherMemberNo,
        (res) => {
          this.user = res.data;
          this.$router.push({
            name: "OtherProfile",
            params: {
              other: this.user,
            },
          });
        },
        (error) => {}
      );
    },
    post() {
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
    searching(data) {
      this.searchItem = data;
      if (this.searchItem.type == "post") {
        this.isPost = true;
        this.isUser = false;
        this.isHashtag = false;
        this.isSearching = true;
        SearchApi.requestPostBySearch(
          this.searchItem.keyword,
          (res) => {
            this.postList = res.data;
          },
          (error) => {}
        );
      } else if (this.searchItem.type == "user") {
        this.isPost = false;
        this.isUser = true;
        this.isHashtag = false;
        this.isSearching = true;
        SearchApi.requestMemberBySearch(
          this.searchItem.keyword,
          (res) => {
            this.memberList = res.data;
          },
          (error) => {}
        );
      } else if (this.searchItem.type == "hashtag") {
        this.isPost = false;
        this.isUser = false;
        this.isHashtag = true;
        this.isSearching = true;
        SearchApi.requestHashtagBySearch(
          this.searchItem.keyword,
          (res) => {
            this.hashtagList = res.data;
          },
          (error) => {}
        );
      } else if (this.searchItem.type == "none") {
        this.isPost = false;
        this.isUser = false;
        this.isHashtag = false;
        this.isSearching = false;
      } else {
        this.isPost = false;
        this.isUser = false;
        this.isHashtag = false;
        this.isSearching = false;
      }
    },
  },
};
</script>

<style scoped>
.search-bar {
  margin-bottom: 1%;
}

.v-bottom-navigation {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 3rem;
}

.v-item-group.v-bottom-navigation {
  max-width: 580px;
  width: 100%;
  margin: 0 auto;
}
</style>