<template>
  <div>
    <v-list>
      <v-list-item :member="memberList" @click="toProfile(memberList.memberNo)">
        <v-list-item-avatar>
          <v-img :src="imagePath" />
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title style="font-family: 'Jua', sans-serif;">{{memberList.nickname}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import HeaderComponent from "../common/Header";
// import { mdiAccount } from '@mdi/js';
import UserApi from "../../api/UserApi";
export default {
  name: "SearchUser",
  props: {
    memberList: Array,
  },
  created() {
    this.user = this.$route.params.user;
    if (this.memberList.pwd != "") {
      // 카톡 로그인 아닐때 (경로 그대로 쓰면 안됨)
      this.imageSplit = this.memberList.memberPhoto.split("/");
      this.index = this.imageSplit.length - 1;
      this.imagePath += this.imageSplit[this.index];
    } else {
      // 카톡 로그인일때 (경로 그대로 쓰면됨)
      this.imagePath = this.memberList.memberPhoto;
    }
  },
  components: {},
  methods: {
    toProfile(otherMemberNo) {
      this.$emit("gotoOtherProfile", otherMemberNo);
    },
  },
  data() {
    return {
      imagePath: "http://i3d201.p.ssafy.io:8080/profile/",
      index: 0,
      imageSplit: [],
    };
  },
};
</script>

<style>
</style>