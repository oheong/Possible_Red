<template>
  <div class="user" id="changeUser">
    <div class="wrapC">
      <div class="wrap components-page p-1">
        <HeaderComponent headerTitle="Account Settings" :isBack="true" />
      </div>
      <div class="input-with-label jua">
        <input disabled id="email" v-model="user.email" placeholder="이메일을 입력하세요." type="text" />
        <label for="email" class="jua">이메일</label>
      </div>

      <div class="input-with-label jua">
        <input id="nickname" v-model="user.nickname" placeholder="닉네임" type="text" />
        <label for="nickname" class="jua">닉네임</label>
        <!-- 닉네임 중복체크 -->
        <!-- <div class="error-text" v-if="error.email">{{error.email}}</div> -->
      </div>

      <div class="input-with-label jua">
        <input
          disabled
          id="pwd"
          v-model="user.pwd"
          v-bind:class="{error : error.password, complete:!error.password&&user.pwd.length!==0}"
          placeholder="비밀번호"
          type="password"
        />
        <label for="password" class="jua">비밀번호</label>

        <button @click="goFindPW()">비밀번호 변경하기</button>

        <!-- 패스워드 유효성체크 -->
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </div>

      <div class="input-with-label jua">
        <input id="stateMent" v-model="user.stateMent" placeholder="상태메세지" type="text" />
        <label for="stateMent" class="jua">상태메세지</label>
      </div>

      <div class="input-with-label jua">
        <input v-on:change="fileSelect()" type="file" ref="memberImg"  style="width:580px" />
        <div v-if="memberImg" >
          <img :src="preView" />
        </div>
      </div>

      <div class="wrap components-page">
        <LargeButton
          text="수정하기"
          :isBackground="isSubmit"
          @click.native="updateUser"
          :disabled="!isSubmit"
          :class="{disabled : !isSubmit}"
        />
      </div>
    </div>
  </div>
</template>

<script>
import "../../components/css/user.scss";
import * as EmailValidator from "email-validator";
import PV from "password-validator";
import UserApi from "../../api/UserApi";
import HeaderComponent from "../../components/common/Header.vue";
import LargeButton from "../../components/common/ButtonLarge";

export default {
  name: "ChangeUser",
  components: {
    HeaderComponent,
    LargeButton,
  },
  created() {
    this.user = this.$session.get("user");
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },

  watch: {
    "user.pwd": function (v) {
      this.checkForm();
    },
  },

  methods: {
    goFindPW() {
      this.$router.push("/findpwd");
    },
    fileSelect() {
      this.memberImg = this.$refs.memberImg.files[0];
      // 미리보기
      this.preView = URL.createObjectURL(this.$refs.memberImg.files[0]);
    },
    checkForm() {
      if (
        this.user.pwd.length >= 0 &&
        !this.passwordSchema.validate(this.user.pwd)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
      // console.log(isSubmit);
    },
    updateUser() {
      var formData = new FormData();
      if (this.memberImg == "") {
        alert("프로필사진은 꼭 입력하세요!!");
        return;
      }
      formData.append("memberImg", this.memberImg);
      formData.append("memberNo", this.user.memberNo);
      formData.append("email", this.user.email);
      formData.append("nickname", this.user.nickname);
      formData.append("point", this.user.point);
      formData.append("pwd", this.user.pwd);
      formData.append("stateMent", this.user.stateMent);

      UserApi.requestUpdate(
        formData,
        (res) => {
          if (res.data === "success") {
            this.$session.destroy();
            UserApi.requestLogin(
              {
                email: this.user.email,
                pwd: this.user.pwd,
              },
              (res) => {
                if (res.status === 200) {
                  // session에 로그인 회원 정보 저장
                  this.$session.set("user", res.data);
                  // 결과페이지로 이동
                } else {
                  alert("재로그인 하세요");
                }
                this.$router.push("/profile");
              },
              (error) => {}
            ); // 로그인 끝
          }
        },
        (error) => {}
      );
    },
  },

  data() {
    return {
      user: {
        email: "",
        memberNo: 0,
        nickname: "",
        point: 0,
        pwd: "",
        stateMent: "",
        memberPhoto: "",
      },
      passwordSchema: new PV(),
      error: {
        password: false,
      },
      isSubmit: false,
      preView: "",
      memberImg: "",
    };
  },
};
</script>

<style>
.jua {
  font-family: "Jua", sans-serif;
}
</style>