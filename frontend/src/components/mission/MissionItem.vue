<template>
  <!-- 미션리스트에 하나하나 불러오기 위한 컴포넌트 -->
  <div class="wrapC">
    <v-card class="mx-auto" v-for="mission in missions" :key="mission.etag" :item="mission">
      <i
        v-if="getCookie(mission.startDate,mission.endDate,$moment().format('YYYY-MM-DD'))"
        class="fas fa-cookie"
        style="color:#795548"
      ></i>
      <i v-else-if="mission.start" class="fas fa-cookie-bite" style="color:#827717"></i>

      <v-img class="white--text align-end" :src="mission.missionPhoto"></v-img>

      <v-card-text class="text--primary">
        <div class="nanum" style="font-size:xx-large; font-weight:500">{{ mission.missionTitle }}</div>
        <br />
        <div class="jua">Max : {{ mission.joinMem }} 명</div>
        <div
          class="jua"
        >{{ $moment(mission.startDate).format('YYYY-MM-DD') }} ~ {{ $moment(mission.endDate).format('YYYY-MM-DD') }}</div>
      </v-card-text>

      <v-card-actions>
        <button
          class="jua"
          style="font-size:medium; color:#1A237E; margin:0 3% 0 2%;"
          @click="gotomissiondetail(mission.missionNo)"
        >자세히</button>
        <button
          class="jua"
          style="font-size:medium; color:purple; margin:0 3% 0 0"
          @click="gotopeople(mission.missionNo)"
        >참여자 목록보기</button>

        <button
          v-if="getCookie(mission.startDate,mission.endDate,$moment().format('YYYY-MM-DD'))"
          style="font-size:medium; color:hotpink; margin:0 3% 0 0"
          class="jua"
          @click="entryJoin(mission.memberNo, user.memberNo, mission.missionNo,mission.joinMem)"
        >신청하기</button>
      </v-card-actions>
    </v-card>
  </div>
</template>
<script>
import { now } from "moment";
import EntryApi from "../../api/EntryApi.js";
export default {
  name: "MissionItem",
  props: {
    missions: Array, // 전체 미션 리스트
    followins: Array,
  },
  created() {
    this.user = this.$session.get("user");
  },
  data() {
    return {
      user: {
        // 현재 로그인한 유저 정보
        email: "",
        memberNo: 0,
        nickname: "",
        point: 0,
        pwd: "",
        stateMent: "",
      },
      start: "",
      end: "",
      entryNum: 0,
    };
  },
  methods: {
    entryJoin(mNo, uNo, mm, lastmem) {
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
      EntryApi.requestEntryCountByMissionNo(
        mm, // 미션넘버로 카운팅해봤을때
        (res) => {
          this.entryNum = res.data;
          console.log(this.entryNum);
          if (this.entryNum == lastmem) {
            alert("자리가 꽉 찼습니다!");
            return;
          } else {
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
                  entry.nonCnt = 0;
                  EntryApi.requestEntryRegister(
                    entry,
                    (resentry) => {
                      // console.log("엔트리 등록 완료");
                      alert("미션에 참여합니다");
                    },
                    (error) => {
                      console.log("엔트리 등록 안됐음");
                    }
                  );
                  this.$router.push("/mymission");
                } else {
                  alert("이미 참여하고있는 미션입니다.");
                }
              },
              (error) => {}
            );
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
        // console.log("쿠키있음");
        return true;
      } else if (eDate < today) {
        // console.log("쿠키없음");
        return false;
      }
    },
    gotomissiondetail(num) {
      this.$router.push({
        name: "MissionDetail",
        params: {
          // 미션 넘버 넘기기
          num: num,
        },
      });
    },
    gotopeople(num) {
      this.$router.push({
        name: "MissionPeople",
        params: {
          num: num,
          followings: this.followings,
        }
      });
    },
  },
};
</script>
<style>
.mx-auto.v-card.v-sheet.theme--light {
  margin-bottom: 30px;
}
.jua {
  font-family: "Jua", sans-serif;
}
.v-btn__content {
  font-family: "Jua", sans-serif;
}
.v-responsive__content {
  background: transparent !important;
}
.wrapC {
  max-width: 580px;
}
.nanum {
  font-family: "Nanum Pen Script", cursive;
}
</style>