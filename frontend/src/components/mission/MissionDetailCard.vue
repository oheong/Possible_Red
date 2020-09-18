<template>
  <div class="wrapC">
    <v-app>
      <v-card class="mx-auto">
        <v-img class="white--text align-end" :src="mission.missionPhoto"></v-img>

        <v-card-subtitle
          class="pb-0 jua"
        >{{$moment(mission.startDate).format('YYYY-MM-DD')}} ~ {{$moment(mission.endDate).format('YYYY-MM-DD')}}</v-card-subtitle>
        <v-card-text class="jua">{{entryNum}}명 / {{mission.joinMem}}명</v-card-text>

        <v-card-text class="text--primary">
          <div class="jua">👩‍⚖️방장 : {{mission.master}}</div>
          <br />
          <div class="jua">커트라인 : {{mission.cutCnt}}</div>
          <div class="jua">배당 포인트 : {{mission.point}}</div>
          <div class="jua">차감 포인트 : {{mission.minusPoint}}</div>
        </v-card-text>

        <v-card-actions
          v-if="getCookie(mission.startDate,mission.endDate,$moment().format('YYYY-MM-DD'))"
        >
          <button
            class="jua"
            text
            style="margin:0 10px 0 10px; color:navy;"
            @click="entryJoin(mission.memberNo, user.memberNo, mission.missionNo)"
          >신청하기</button>

          <button
            v-if="isSame(user.memberNo, mission.memberNo)"
            class="jua"
            text
            style="margin:0 10px 0 0; color:hotpink"
            @click="gotomodify(mission.missionNo)"
          >수정하기</button>
        </v-card-actions>

        <v-card-actions
          v-if="!getCookie(mission.startDate,mission.endDate,$moment().format('YYYY-MM-DD'))&&isSame(user.memberNo, mission.memberNo)"
        >
          <v-btn
            text
            style="font-size:medium"
            @click="cuttingPoint($moment().format('YYYY-MM-DD'))"
          >미션 마감하기 (하루에 한번만 누르세요!)</v-btn>
        </v-card-actions>
      </v-card>
      <!-- 미션에 해당하는 post 불러오기 -->
      <div style="z-index:1;" class="jua">
        <p
          class="jua"
          style="text-align:center; font-size:x-large;"
        >📌{{mission.missionTitle}}'s POST</p>
        <v-expansion-panels>
          <v-expansion-panel v-for="post in posts" :key="post.etag" class="jua">
            <v-expansion-panel-header @click="getImage(post.postPhoto)">
              📅{{$moment(post.postDate).format("YYYY-MM-DD")}}
              <div>📝{{ post.postContent }}</div>
            </v-expansion-panel-header>

            <v-expansion-panel-content>
              <div style="align:center width:60%" @click="goPostDetail(post.postNo)">
                <v-img :src="imagePath" />
              </div>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </div>
    </v-app>
  </div>
</template>

<script>
import EntryApi from "../../api/EntryApi.js";
import PostApi from "../../api/PostApi";

export default {
  name: "MissionDetailCard",
  props: {
    mission: Object,
    num: Number,
    entryNum: Number,
    entryList: Array,
  },
  created() {
    this.user = this.$session.get("user");

    PostApi.requestPostByMission(
      this.num,
      (res) => {
        this.posts = res.data; // 현재 미션넘버로 찾아온 포스트
      },
      (error) => {}
    );
  },
  data() {
    return {
      posts: [],
      user: {
        // 현재 로그인한 유저 정보
        email: "",
        memberNo: 0,
        nickname: "",
        point: 0,
        pwd: "",
        stateMent: "",
      },
      imagePath: "",
      index: 0,
      imageSplit: [],
    };
  },
  methods: {
    getImage(postPhoto) {
      this.imagePath = "http://i3d201.p.ssafy.io:8080/";
      this.imageSplit = postPhoto.split("/");
      this.index = this.imageSplit.length - 1;
      this.imagePath += this.imageSplit[this.index]; // 포스트 이미지
    },
    cuttingPoint(today) {
      var tempPost = new Array();
      for (var i = 0; i < this.posts.length; i++) {
        if (this.posts[i].postDate.substr(0, 10) == today) {
          tempPost.push(this.posts[i].memberNo); // 오늘 포스트 쓴 사람
        }
      }
      for (var p = 0; p < tempPost.length; p++) {
        for (var e = 0; e < this.entryList.length; e++) {
          if (tempPost[p] == this.entryList[e].memberNo) {
            if (this.entryList[e].memberNo != -1) {
              this.entryList[e].memberNo = -1;
              break;
            }
          }
        }
      }
      // 여기서 entryList에 -1 아닌 사람들은 글을 안쓴거임
      for (var j = 0; j < this.entryList.length; j++) {
        if (this.entryList[j].memberNo == -1) continue;
        if (this.entryList[j].nonCnt == 0) {
          // 딜리트 해주기
          EntryApi.requestEntryDelete(
            this.entryList[j].memberNo,
            (res) => {},
            (error) => {}
          );
        } else {
          // 업데이트해주기
          this.entryList[j].nonCnt -= 1;
          EntryApi.requestEntryUpdate(
            this.entryList[j],
            (res) => {},
            (error) => {}
          );
        }
      }
      alert("마감이 완료되었습니다");
    },
    gotomodify(num) {
      this.$router.push({
        name: "MissionModify",
        params: {
          // 미션 넘버 넘기기
          num: num,
        },
      });
    },
    isSame(itsMe, writer) {
      if (itsMe == writer) {
        // console.log("내가 쓴 글입니다.");
        return true;
      } else {
        // console.log("내가 쓴 글이 아닙니다");
        return false;
      }
    },
    entryJoin(mNo, uNo, mm) {
      // 미션만든사람 멤버넘버, 지금 로그인한 사람 멤버넘버
      // 만약에 내가 만든 미션일경우 신청x
      if (mNo === uNo) {
        alert("내가 만든 미션은 자동으로 참가되었습니다.");
        return;
      }
      let data = {
        memberNo: uNo,
        missionNo: mm,
      };
      EntryApi.reqeustCanIJoinThisMission(
        data,
        (res) => {
          if (res.data === "success") {
            let entry = {
              memberNo: 0,
              missionNo: 0,
              reward: 0,
              nonCnt: 0,
            };
            entry.memberNo = uNo;
            entry.missionNo = mm;
            entry.reward = 0;
            entry.nonCnt = this.mission.cutCnt;
            EntryApi.requestEntryRegister(
              entry,
              (resentry) => {
                // console.log("엔트리 등록 완료");
                alert("미션에 참여합니다");
              },
              (error) => {
                // console.log("엔트리 등록 안됐음");
              }
            );
            this.$router.push("/mymission");
          } else {
            alert("이미 참여하고있는 미션입니다.");
          }
        },
        (error) => {}
      );
    },
    getCookie(sDate, eDate, today) {
      /*
        1. 시작날짜가 오늘날짜보다 이후이다 => 쿠키 있음 true
        2. 끝나는 날짜가 오늘날짜보다 이전이다 => 쿠키 없음 false
      */
      if (today < sDate) {
        //console.log("쿠키있음");
        return true;
      } else if (eDate < today) {
        //console.log("쿠키없음");
        return false;
      }
    },
    goPostDetail(num) {
      this.$router.push({
        name: "PostDetail",
        params: {
          num: num,
        },
      });
    },
  },
};
</script>
<style scoped>
.jua {
  font-family: "Jua", sans-serif;
  font-size: medium;
}
.v-expansion-panel {
  margin-bottom: 10px;
}
.v-expansion-panel-content__wrap {
  font-family: "Jua", sans-serif;
  color: rebeccapurple;
}
.theme--light.v-application {
  background: whitesmoke;
}
.v-expansion-panel-content__wrap {
  font-family: "Jua", sans-serif;
}
</style>
