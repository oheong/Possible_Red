<template>
  <div class="wrapC p-0">
    <v-card style="box-shadow: none; padding-top:3%">
      <v-tabs
        background-color="white"
        color="deep-purple accent-4"
        centered
      >
        <v-tab style="font-family: 'Jua', sans-serif;">팔로워</v-tab>
        <v-tab style="font-family: 'Jua', sans-serif;">팔로잉</v-tab>

        <v-tab-item>
          <v-list>
            <v-list-item
              v-for="item in followers"
              :key="item.nickname"
              @click="toProfile(item)"
            >
              <v-list-item-avatar>
                <v-img :src="item.memberPhoto"></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title v-text="item.nickname" style="font-family: 'Jua', sans-serif;"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-tab-item>

        <v-tab-item>
          <v-list>
            <v-list-item
              v-for="item in followings"
              :key="item.nickname"
              @click="toProfile(item)"
            >
              <v-list-item-avatar>
                <v-img :src="item.memberPhoto"></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title v-text="item.nickname" style="font-family: 'Jua', sans-serif;"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-tab-item>
      </v-tabs>
    </v-card>
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
  </div>
</template>

<script>
import { mdiAccount } from '@mdi/js';
import FollowApi from "../../api/FollowApi";
import UserApi from "../../api/UserApi";

export default {
  name: "ProfileFollow",
  created() {
    this.user = this.$route.params.user;
    this.userMe = this.$session.get("user");
    this.getFollowing(this.user.memberNo);
    this.getFollower(this.user.memberNo);
  },
  methods: {
    getFollower(num) {
      FollowApi.requestAllFollowerByNo(
        num,
        (res) => {
          for ( let i in res.data ){ 
            UserApi.requestMemberByNo(
              res.data[i].me,
              (res) => { 
                this.followers.push(res.data)
                this.imageSplit = this.followers[i].memberPhoto.split("/");
                this.index = this.imageSplit.length - 1;
                this.followers[i].memberPhoto = this.imagePath + this.imageSplit[this.index];
                },
              (error) => { console.log(error) }
            )
          }
        },
        (error) =>{console.log(error)}
      )
    },
    getFollowing(num) {
      FollowApi.requestAllFollowingByNo(
        num,
        (res) => { 
          for ( let i in res.data ){
            UserApi.requestMemberByNo(
              res.data[i].you,
              (res) => { 
                this.followings.push(res.data)
                this.imageSplit = this.followings[i].memberPhoto.split("/");
                this.index = this.imageSplit.length - 1;
                this.followings[i].memberPhoto = this.imagePath + this.imageSplit[this.index];
              },
              (error) => { console.log(error) }
            )
          }
        },
        (error) =>{ console.log(error) }

      )
    },
    toProfile(other){
      if(other.memberNo == this.userMe.memberNo){
        this.$router.push({
        name: "UserProfile",
      });
      } else {
      this.$router.push({
        name: "OtherProfile",
        params: { 
          other: other, 
        },
      });
      }
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
  },
  data() {
    return {
      active_tab : "",
      user: {},
      userMe: {},
      icons: {
        mdiAccount,
      },
      followers: [],
      followings: [],
      imagePath: "http://i3d201.p.ssafy.io:8080/profile/",
      index: 0,
      imageSplit: [],
    }  
  },
}
</script>

<style>
.v-tab.v-tab--active {
  color: blueviolet;
}
</style>